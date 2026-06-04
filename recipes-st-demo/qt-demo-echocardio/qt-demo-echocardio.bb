DESCRIPTION = "Add support of EchoCardio application demo on ST Launcher"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${S}/LICENSES/GPL-3.0-only.txt;md5=3c34afdc3adf82d2448f12715a255122"

COMPATIBLE_MACHINE = "(stm32mp2common)"

# This package depends on the selected machine
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEMO_APP_NAME := "echocardio"
QTLAUNCHER_APPS_ROOT := "${prefix}/local/demo/qtlauncher-apps"
ECHOCARDIO_LOCAL_ARCHIVE := "${THISDIR}/files/echocardio.tar.gz"

python __anonymous () {
    import os

    if d.getVar("ST_ECHOCARDIO_DEMO") != "1":
        raise bb.parse.SkipRecipe('qt-demo-echocardio disabled; set ST_ECHOCARDIO_DEMO = "1" to enable it')

    archive = d.expand("${ECHOCARDIO_LOCAL_ARCHIVE}")
    if not os.path.exists(archive):
        bb.fatal('qt-demo-echocardio requires local archive %s when ST_ECHOCARDIO_DEMO = "1"' % archive)
}

SRC_URI = "\
    file://echocardio.tar.gz \
    file://demo.xml \
    file://EchoCardio.png \
"

# Source tree is provided as a tarball (see SRC_URI)
S = "${WORKDIR}/echocardio"

PV = "1.0"

inherit qt6-cmake

DEPENDS = "\
    qtbase \
    qtdeclarative \
    qtdeclarative-native \
"

EXTRA_OECMAKE += "\
    -DLINK_INSIGHT=OFF \
    -DBUILD_QDS_COMPONENTS=OFF \
"

do_install() {
    # Install launcher app directory structure:
    # /usr/local/demo/qtlauncher-apps/echocardio/{demo.xml,EchoCardio.png,bin/EchoCardioApp}
    install -d ${D}${QTLAUNCHER_APPS_ROOT}/${DEMO_APP_NAME}
    install -m 0644 ${WORKDIR}/demo.xml ${D}${QTLAUNCHER_APPS_ROOT}/${DEMO_APP_NAME}/demo.xml
    install -m 0644 ${WORKDIR}/EchoCardio.png ${D}${QTLAUNCHER_APPS_ROOT}/${DEMO_APP_NAME}/EchoCardio.png

    install -d ${D}${QTLAUNCHER_APPS_ROOT}/${DEMO_APP_NAME}/bin

    if [ -f ${B}/EchoCardioApp ]; then
        install -m 0755 ${B}/EchoCardioApp ${D}${QTLAUNCHER_APPS_ROOT}/${DEMO_APP_NAME}/bin/EchoCardioApp
    elif [ -f ${B}/bin/EchoCardioApp ]; then
        install -m 0755 ${B}/bin/EchoCardioApp ${D}${QTLAUNCHER_APPS_ROOT}/${DEMO_APP_NAME}/bin/EchoCardioApp
    else
        bbfatal "EchoCardioApp binary not found in build directory (${B})"
    fi
}

RDEPENDS:${PN} = "\
    qtbase \
    qtdeclarative \
"

FILES:${PN} += "${QTLAUNCHER_APPS_ROOT}"
