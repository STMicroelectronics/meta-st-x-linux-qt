FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://0001-examples-opengl-cube-set-maximized-window.patch \
"

PACKAGECONFIG_GL = "gles2 eglfs"

PACKAGECONFIG += " \
    cups \
    fontconfig \
    glib \
    harfbuzz \
    icu \
    libinput \
    sql-sqlite \
    tslib \
    xkbcommon \
    gbm \
    kms \
    examples \
    "

# Qt Flags for STM32MP25x - GLES3 support available
QT_CONFIG_FLAGS:remove:stm32mp2common = "-no-opengles3"
QT_CONFIG_FLAGS:append:stm32mp2common = " -opengles3"

QT_QPA_DEFAULT_PLATFORM ?= "wayland"
QT_QPA_EGLFS_INTEGRATION ?= ""
