DESCRIPTION = "Intel® RealSense™ SDK 2.0 is a cross-platform library for Intel® RealSense™ depth cameras."
SECTION = "libs"

require librealsense2.inc


RDEPENDS:${PN} = "\
    bash \
"
RDEPENDS:${PN}-examples += "\
    ${PN} \
"
SRC_URI += "\
	file://0002-Avoid-installing-viewer-presets.patch \
	file://0003-Remove-libusb-from-targets-list.patch \
	file://0004-Remove-R200-fix-from-udev-rules.patch \
"

PR = "r0"

EXTRA_OECMAKE += " \
    -DBUILD_SHARED_LIBS:BOOL=ON \
    -DBUILD_EXAMPLES:BOOL=ON \
    -DBUILD_GRAPHICAL_EXAMPLES:BOOL=OFF \
    -DBUILD_GLSL_EXTENSIONS:BOOL=OFF \
"

PACKAGES += "\
    ${PN}-examples \
    ${PN}-tools \
    ${PN}-debug-tools \
"

PACKAGES += "${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', '${PN}-graphical-examples', '', d)}"

do_install:append() {
    install -d "${D}${sysconfdir}/udev/rules.d"
    install -m 0644 ${S}/config/99-realsense-libusb.rules ${D}${sysconfdir}/udev/rules.d/99-${BPN}-libusb.rules
}

FILES:${PN} = "\
    ${libdir}/${PN}*.so.* \
    ${sysconfdir}/udev/rules.d/* \
"

FILES:${PN}-examples = "\
    ${bindir}/rs-color \
    ${bindir}/rs-depth \
    ${bindir}/rs-distance \
    ${bindir}/rs-hello-realsense \
    ${bindir}/rs-pose \
    ${bindir}/rs-pose-and-image \
    ${bindir}/rs-pose-predict \
    ${bindir}/rs-save-to-disk \
"

FILES:${PN}-tools = "\
    ${bindir}/realsense-viewer \
    ${bindir}/rs-convert \
    ${bindir}/rs-depth-quality \
    ${bindir}/rs-embed \
    ${bindir}/rs-fw-update \
"

FILES:${PN}-debug-tools = "\
    ${bindir}/rs-benchmark \
    ${bindir}/rs-data-collect \
    ${bindir}/rs-enumerate-devices \
    ${bindir}/rs-fw-logger \
    ${bindir}/rs-record \
    ${bindir}/rs-rosbag-inspector \
    ${bindir}/rs-terminal \
"
PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"
