
do_write_config:append() {
    cat >${WORKDIR}/meson-qt6.cross <<EOF
[binaries]
qmake = '${STAGING_BINDIR_NATIVE}/qmake'
moc = '${STAGING_DIR_NATIVE}/usr/libexec/moc'
rcc = '${STAGING_DIR_NATIVE}/usr/libexec/rcc'
uic = '${STAGING_DIR_NATIVE}/usr/libexec/uic'
lrelease = '${STAGING_BINDIR_NATIVE}/lrelease'
EOF
}

QTWAYLANDDEPENDS = "${@bb.utils.contains("DISTRO_FEATURES", "wayland", "qtwayland", "", d)}"
PACKAGECONFIG[qt6] = "-Dqt6=enabled,-Dqt6=disabled,qtbase qtdeclarative qtbase-native qttools-native ${QTWAYLANDDEPENDS}"

MESON_CROSS_FILE:class-target:append = "${@bb.utils.contains('PACKAGECONFIG', 'qt6', ' --cross ${WORKDIR}/meson-qt6.cross', '', d)}"

PACKAGECONFIG:append = " qt6"
