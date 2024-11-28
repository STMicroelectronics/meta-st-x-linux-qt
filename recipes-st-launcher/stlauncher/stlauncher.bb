DESCRIPTION = "ST Launcher"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "(stm32mpcommon)"

# Using source from Github repository
SRC_URI = "git://github.com/STMicroelectronics/st-launcher.git;protocol=https;branch=qt6 \
    file://qt-app.svg \
    file://platformdata-mp15xx.html \
    file://platformdata-mp25xx.html \
    file://start_up_stlauncher.sh \
"
SRCREV = "3562b2a1e3f28173238ad6c874b66d67aa4615ec"

PV = "2.0"

RRECOMMENDS:${PN} = " \
    packagegroup-st-demo \
    "

inherit qt6-qmake systemd

ST_LAUNCHER_FULL_PATH := "${prefix}/share/qt/${P}/stlauncher"

DEPENDS = "qtbase qtcharts qtdeclarative qtquicktimeline"

S = "${WORKDIR}/git"

# Install platformdata.html for MP1
do_install:append:stm32mp1common() {
    # platformdata*.txt files
    install -d ${D}${prefix}/share/config
    install -m 0755 ${WORKDIR}/platformdata-mp15xx.html ${D}${prefix}/share/config/platformdata.txt
}

# Install platformdata.html for MP2
do_install:append:stm32mp2common() {
    # platformdata*.txt files
    install -d ${D}${prefix}/share/config
    install -m 0755 ${WORKDIR}/platformdata-mp25xx.html ${D}${prefix}/share/config/platformdata.txt
}

do_install() {
    install -d ${D}${prefix}/share/qt/${P}
    install -m 0755 ${B}/stlauncher ${D}${prefix}/share/qt/${P}
    cp -R --no-dereference --preserve=mode,links ${S}/qml ${D}${prefix}/share/qt/${P}

    install -d ${D}${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/qt-app.svg ${D}${datadir}/pixmaps

    # update the start_up_stlauncher.sh script
    sed -i -e 's,@stlauncher@,${P},g' ${WORKDIR}/start_up_stlauncher.sh

    # Install the startup script
    install -d ${D}${prefix}/local/weston-start-at-startup
    install -m 0755 ${WORKDIR}/start_up_stlauncher.sh ${D}${prefix}/share/qt/${P}

    # Set STLauncher as default demo-launcher
    install -m 0755 -d ${D}${sysconfdir}/default
    echo "DEFAULT_DEMO_APPLICATION=${prefix}/share/qt/${P}/start_up_stlauncher.sh" >  ${D}${sysconfdir}/default/demo-launcher
}

RDEPENDS:${PN} = "\
    qt5compat \
    qtcharts \
    qtquicktimeline \
    qtquicktimeline-qmlplugins \
    qtdeclarative-qmlplugins \
    qtvirtualkeyboard \
    qtvirtualkeyboard-plugins \
    qtvirtualkeyboard-qmlplugins \
"

FILES:${PN} += "${sysconfdir}/default ${sysconfdir}/etc/profile.d ${datadir}/pixmaps ${prefix}/share/qt/${P} ${prefix}/local ${prefix}/share/config"

#inherit useradd
USERADD_PARAM:${PN} = "--home /home/weston --shell /bin/sh --user-group -G video,input,tty,audio,weston-launch,dialout weston"
GROUPADD_PARAM:${PN} = "-r weston-launch; -r wayland"
