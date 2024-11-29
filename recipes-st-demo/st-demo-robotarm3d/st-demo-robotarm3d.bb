DESCRIPTION = "Add support of robotarm3D application demo on ST Launcher"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "(stm32mp2common)"

DEMO_APP_NAME := "robotarm3d"

# Using source from Github repository
SRC_URI = "git://github.com/STMicroelectronics/st-demo-robotarm3d.git;protocol=https;branch=qt6 \
           file://${DEMO_APP_NAME}.desktop \
           file://Icon_demo_${DEMO_APP_NAME}.svg \
           file://launch_${DEMO_APP_NAME}.sh \
"
SRCREV = "326cab1d6a4ac69f54f82b537a9ccd92c28086ac"

PV = "2.1"

inherit qt6-qmake systemd

DEPENDS = "qtbase qtcharts qtdeclarative qtquicktimeline"

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
}

RDEPENDS:${PN} = "\
    qtbase \
    qt5compat \
    qtcharts \
    qtdeclarative \
    qtsvg \
    qtquicktimeline \
    qtquick3d \
"

FILES:${PN} += "${prefix}/local/demo/${DEMO_APP_NAME} ${prefix}/local/demo/application ${datadir}/applications ${datadir}/pixmaps"
