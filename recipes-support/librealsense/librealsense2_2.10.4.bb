SUMMARY = "Intel® RealSense™ SDK 2.0 is a cross-platform library for Intel® RealSense™ depth cameras (D400 series and the SR300)."
LIC_FILES_CHKSUM = "file://LICENSE;md5=a1692f06943fa281fd047a22d7e10800"

SRC_URI = "\
	git://github.com/IntelRealSense/librealsense.git;branch=development;tag=v${PV} \
	file://Remove-R200-fix-from-udev-rules.patch \
"

PR = "r0"

S = "${WORKDIR}/git"

require librealsense-common.inc
require librealsense2.inc
require librealsense2-python.inc