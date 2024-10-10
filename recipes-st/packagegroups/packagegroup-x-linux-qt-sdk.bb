DESCRIPTION = "X-LINUX-QT embedded Qt6 SDK toolchain"
LICENSE = "MIT & Apache-2.0 & BSD-3-Clause"

LIC_FILES_CHKSUM  = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
LIC_FILES_CHKSUM += "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
LIC_FILES_CHKSUM += "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

inherit packagegroup populate_sdk populate_sdk_qt6
inherit native nativesdk

MACHINE_EXTRA_INSTALL_SDK_HOST ?= ""

SUMMARY:packagegroup-x-linux-qt-embedded-toolchain-target = "X-LINUX-QT embedded Qt SDK toolchain for target"
RDEPENDS:packagegroup-x-linux-qt-embedded-toolchain-target = "\
    packagegroup-x-linux-qt-base \
    packagegroup-x-linux-qt-extra \
    "

SUMMARY:nativesdk-packagegroup-x-linux-qt-embedded-toolchain-host = "X-LINUX-QT native Qt SDK toolchain for HOST"
RDEPENDS:nativesdk-packagegroup-x-linux-qt-embedded-toolchain-host = "\
    nativesdk-gperf \
    nativesdk-cmake \
    nativesdk-make \
    nativesdk-ninja \
    nativesdk-perl-modules \
    ${MACHINE_EXTRA_INSTALL_SDK_HOST} \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "nativesdk-wayland-dev", "", d)} \
    "

SUMMARY:nativesdk-packagegroup-x-linux-qt-sdk = "X-LINUX-QT Native SDK modules"
RDEPENDS:nativesdk-packagegroup-x-linux-qt-sdk = "\
    nativesdk-packagegroup-x-linux-qt-embedded-toolchain-host \
    nativesdk-qt3d-tools \
    nativesdk-qtbase \
    nativesdk-qtbase-staticdev \
    nativesdk-qtbase-tools \
    nativesdk-qtdeclarative \
    nativesdk-qtdeclarative-staticdev \
    nativesdk-qtdeclarative-tools \
    nativesdk-qtquick3d-tools \
    nativesdk-qtremoteobjects-tools \
    nativesdk-qtscxml-tools \
    nativesdk-qttools-tools \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'nativesdk-qtwayland-tools', '', d)} \
    "

TOOLCHAIN_HOST_TASK:append = " nativesdk-packagegroup-x-linux-qt-sdk"
TOOLCHAIN_TARGET_TASK:append = " packagegroup-x-linux-qt-embedded-toolchain-target"
