FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://0001-examples-opengl-cube-set-maximized-window.patch \
    file://0002-QtBuildInternalsConfig-allow-installing-example-sour.patch \
"

# Add examples
inherit qt6-examples

# This package depends on the selected machine
PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGECONFIG_GL = "gles2 eglfs"

PACKAGECONFIG += " \
    linuxfb \
"

# Default platform plugin for STM32MPU devices
QT_QPA_DEFAULT_PLATFORM ?= "wayland"

# Add LinuxFB for STM32MP13x and make it default
QT_CONFIG_FLAGS:append:stm32mp13common = " -linuxfb"
QT_QPA_DEFAULT_PLATFORM:stm32mp13common = "linuxfb"

# Add LinuxFB for STM32MP21x and make it default
QT_CONFIG_FLAGS:append:stm32mp21common = " -linuxfb"
QT_QPA_DEFAULT_PLATFORM:stm32mp21common = "linuxfb"

# Qt Flags for STM32MP25x - GLES3 support available
QT_CONFIG_FLAGS:remove:stm32mp2common = "-no-opengles3"
QT_CONFIG_FLAGS:append:stm32mp2common = " -opengles3"

QT_QPA_EGLFS_INTEGRATION ?= ""
