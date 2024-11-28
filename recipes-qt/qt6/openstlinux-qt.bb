# Copyright (C) 2018, STMicroelectronics - All Rights Reserved
SUMMARY = "add script and material to help with eglfs qt configuration"
HOMEPAGE = "www.st.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
DEPENDS = ""

COMPATIBLE_MACHINE = "(stm32mpcommon)"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# =========================================================================
# QT Backend for STM32MPU Devices
# =========================================================================
# Default is Wayland/Weston
DIST_QT_PLATFORM ?= "wayland"
# LinuxFB QT Backend for STM32MP13x Devices
DIST_QT_PLATFORM:stm32mp13common = "linuxfb"
# LinuxFB QT Backend for STM32MP21x Devices
DIST_QT_PLATFORM:stm32mp21common = "linuxfb"

SRC_URI = " \
    file://qt-defaults.linuxfb \
    file://qt-defaults.wayland \
    file://qt_profile.sh \
    file://cursor.json \
    "

S = "${WORKDIR}"

PV = "2.0"

do_install() {
    # update the qt-defaults file
    sed -i -e 's,@platform@,${DIST_QT_PLATFORM},g' ${WORKDIR}/qt-defaults.${DIST_QT_PLATFORM}

    # update the qt_profile.sh file
    sed -i -e 's,@platform@,${DIST_QT_PLATFORM},g' ${WORKDIR}/qt_profile.sh

    # cursor.json file
    install -d ${D}${prefix}/share/qt6
    install -m 0755 ${WORKDIR}/cursor.json ${D}${prefix}/share/qt6/cursor.json

    # install the qt-defaults file
    install -m 0755 -d ${D}${sysconfdir}/default
    install -m 0755 ${WORKDIR}/qt-defaults.${DIST_QT_PLATFORM} ${D}${sysconfdir}/default/qt

    # install the qt_profile.sh file
    install -d ${D}${sysconfdir}/profile.d
    install -m 0755 ${WORKDIR}/qt_profile.sh ${D}${sysconfdir}/profile.d/
}

RDEPENDS:${PN} = "qtbase"
FILES:${PN} += "${sysconfdir}/etc/profile.d ${prefix}/share/qt6 ${sysconfdir}/default"

