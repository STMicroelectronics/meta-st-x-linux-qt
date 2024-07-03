DESCRIPTION = "Add support of medical application demo on ST Launcher"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "(stm32mp2common)"

DEMO_APP_NAME := "medical"

# =========================================================================
# Wayland/Weston QT Backend for STM32 MP1 & MP2 Devices
# =========================================================================
DIST_QT_PLATFORM = "wayland"

# Using source from Git (Codex) repository
SRC_URI = "git://github.com/STMicroelectronics/st-demo-medical.git;protocol=https;branch=qt5 \
           file://${DEMO_APP_NAME}.desktop \
           file://Icon_demo_${DEMO_APP_NAME}.svg \
           file://launch_${DEMO_APP_NAME}.sh \
"
SRCREV = "4ce80040636026461b2920dd7aeb0bf0d686153f"

PV = "1.0"

inherit qmake5 systemd

DEPENDS = "qtbase qtcharts qtdeclarative qtquicktimeline qtsvg"

S = "${WORKDIR}/git"

do_install() {
    # Install the application
    install -d ${D}${prefix}/local/demo/${DEMO_APP_NAME}
    install -m 0755 ${B}/${DEMO_APP_NAME} ${D}${prefix}/local/demo/${DEMO_APP_NAME}

    # install script
    install -d ${D}${prefix}/local/demo/application/${DEMO_APP_NAME}/bin
    install -m 0755 ${WORKDIR}/launch_${DEMO_APP_NAME}.sh ${D}${prefix}/local/demo/application/${DEMO_APP_NAME}/bin

    # install desktop file
    install -d ${D}${datadir}/applications
    install -m 0644 ${WORKDIR}/${DEMO_APP_NAME}.desktop ${D}${datadir}/applications

    # install pictures
    install -d ${D}${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/Icon_demo_${DEMO_APP_NAME}.svg ${D}${datadir}/pixmaps

    # install medias
    install -d ${D}${prefix}/local/demo/${DEMO_APP_NAME}/videos
    install -m 0644 ${S}/assets/Video/*.mp4 ${D}${prefix}/local/demo/${DEMO_APP_NAME}/videos
}

RDEPENDS:${PN} = "\
    qtbase \
    qtcharts \
    qtdeclarative \
    qtsvg \
    qtquicktimeline \
    qtquick3d \
"

FILES:${PN} += "${prefix}/local/demo/${DEMO_APP_NAME} ${prefix}/local/demo/application ${datadir}/applications ${datadir}/pixmaps"
