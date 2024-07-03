DESCRIPTION = "Add support of Qt examples on ST Launcher"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "(stm32mpcommon)"

EXAMPLE_APP_NAME := "opengl-cube"

SRC_URI = "file://${EXAMPLE_APP_NAME}.desktop \
           file://Icon_demo_${EXAMPLE_APP_NAME}.svg \
           file://launch_${EXAMPLE_APP_NAME}.sh \
"

# =========================================================================
# Wayland/Weston QT Backend for STM32 MP1 & MP2 Devices
# =========================================================================
DIST_QT_PLATFORM = "wayland"

PV = "1.0"

inherit systemd

DEPENDS = "qtbase"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    # install script
    install -d ${D}${prefix}/local/demo/application/stlauncher-examples/bin
    install -m 0755 ${WORKDIR}/launch_${EXAMPLE_APP_NAME}.sh ${D}${prefix}/local/demo/application/stlauncher-examples/bin

    # install desktop file
    install -d ${D}${datadir}/applications
    install -m 0644 ${WORKDIR}/${EXAMPLE_APP_NAME}.desktop ${D}${datadir}/applications

    # install icon file
    install -d ${D}${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/Icon_demo_${EXAMPLE_APP_NAME}.svg ${D}${datadir}/pixmaps
}

RDEPENDS:${PN} = "\
    qtbase \
"

FILES:${PN} += "${prefix}/local/demo/application/stlauncher-examples ${datadir}/applications ${datadir}/pixmaps"
