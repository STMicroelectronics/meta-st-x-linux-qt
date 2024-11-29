SUMMARY = "Packagegroup for X-LINUX-QT embedded Linux image"
LICENSE = "MIT & Apache-2.0 & BSD-3-Clause"

LIC_FILES_CHKSUM  = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
LIC_FILES_CHKSUM += "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
LIC_FILES_CHKSUM += "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PV = "2.0.1"

inherit packagegroup features_check

# Requires Wayland to work
REQUIRED_DISTRO_FEATURES = "wayland"

ST_APPS ?= "packagegroup-x-linux-qt-apps"
ST_APPS:stm32mp13common = ""
ST_APPS:stm32mp21common = ""

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-x-linux-qt        \
    packagegroup-x-linux-qt-core   \
    packagegroup-x-linux-qt-base   \
    packagegroup-x-linux-qt-extra  \
    packagegroup-x-linux-qt-demos   \
    packagegroup-x-linux-qt-examples \
    ${ST_APPS}                     \
    "

# Manage to provide all Qt Framework core packages with overall one
RDEPENDS:packagegroup-x-linux-qt = "\
    packagegroup-x-linux-qt-core   \
    packagegroup-x-linux-qt-base   \
    packagegroup-x-linux-qt-extra  \
    packagegroup-x-linux-qt-demos   \
    packagegroup-x-linux-qt-examples \
    ${ST_APPS}                     \
    "

SUMMARY:packagegroup-x-linux-qt-core = "X-LINUX-QT Core components"
RDEPENDS:packagegroup-x-linux-qt-core = "\
    alsa-utils-amixer \
    binutils \
    binutils-symlinks \
    connman-client \
    e2fsprogs-resize2fs \
    htop \
    i2c-tools \
    iproute2 \
    ldd \
    mtd-utils \
    openssh-sftp-server \
    parted \
    procps \
    rsync \
    tslib-calibrate \
    ${@bb.utils.contains("DISTRO_FEATURES", "systemd", "systemd-analyze", "", d)} \
    openstlinux-qt \
    "

SUMMARY:packagegroup-x-linux-qt-base = "X-LINUX-QT Base components"
RDEPENDS:packagegroup-x-linux-qt-base = "\
    qtbase                          \
    qtbase-plugins                  \
    qtbase-tools                    \
    qt5compat                       \
    qt5compat-plugins               \
    qt5compat-qmlplugins            \
    liberation-fonts                \
    \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "qtwayland", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "qtwayland-plugins", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "qtwayland-qmlplugins", "", d)} \
    \
    qtdeclarative                   \
    qtdeclarative-qmlplugins        \
    qtdeclarative-tools             \
    \
    qtmultimedia                    \
    qtmultimedia-plugins            \
    qtmultimedia-qmlplugins         \
    \
    qttools                         \
    "

SUMMARY:packagegroup-x-linux-qt-extra = "X-LINUX-QT Extra components"
RDEPENDS:packagegroup-x-linux-qt-extra = "\
    \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d-qmlplugins', '', d)} \
    \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtquick3d', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtquick3d-qmlplugins', '', d)} \
    \
    qtsvg                       \
    qtsvg-plugins               \
    qtsvg-qmlplugins            \
    \
    qtwebsockets                \
    qtwebsockets-plugins        \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtwebsockets-qmlplugins', '', d)} \
    \
    qtsensors                   \
    qtsensors-plugins           \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtsensors-qmlplugins', '', d)} \
    qtserialport                \
    qtserialport-plugins        \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtserialport-qmlplugins', '', d)} \
    \
    qtcharts                    \
    qtcharts-plugins            \
    qtcharts-qmlplugins         \
    \
    qtimageformats              \
    qtimageformats-plugins      \
    qtimageformats-qmlplugins   \
    \
    qtquicktimeline             \
    qtquicktimeline-plugins     \
    qtquicktimeline-qmlplugins  \
    \
    qtvirtualkeyboard           \
    qtvirtualkeyboard-plugins   \
    qtvirtualkeyboard-qmlplugins \
    \
    qtremoteobjects             \
    qtremoteobjects-plugins     \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtremoteobjects-qmlplugins', '', d)} \
    \
    qtquickdesigner-components  \
    "

SUMMARY:packagegroup-x-linux-qt-apps = "X-LINUX-QT Applications"
RDEPENDS:packagegroup-x-linux-qt-apps = "\
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "stlauncher", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "stlauncher-example-opengl-cube", "", d)} \
    "

ST_DEMOS = ""
ST_DEMOS:append:stm32mp2common = "\
    st-demo-medical             \
    st-demo-robotarm3d          \
    "

SUMMARY:packagegroup-x-linux-qt-demos = "X-LINUX-QT Application Demos"
RDEPENDS:packagegroup-x-linux-qt-demos = "${ST_DEMOS}"

SUMMARY:packagegroup-x-linux-qt-examples = "X-LINUX-QT Application Examples"
RDEPENDS:packagegroup-x-linux-qt-examples = "\
    qtbase-examples             \
    qtvirtualkeyboard-examples  \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtquick3d-examples', '', d)} \
    "
