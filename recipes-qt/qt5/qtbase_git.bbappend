FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://0001-examples-opengl-cube-set-maximized-window.patch \
"

PACKAGECONFIG_GL = "gles2 eglfs"

PACKAGECONFIG += " \
    cups \
    fontconfig \
    getentropy \
    gif \
    glib \
    harfbuzz \
    ico \
    icu \
    libinput \
    sql-sqlite \
    tslib \
    xkbcommon \
    gbm \
    kms \
    examples \
    accessibility \
    "

SRC_URI += "file://0001-add-support-of-QT_QPA_EGLFS_DPI_OVERRIDE.patch"
SRC_URI += "file://0002-RHI-introduce-a-way-to-disable-framebuffer-clears-on.patch"

# Qt Flags for STM32MP25x - GLES3 support available
QT_CONFIG_FLAGS:remove:stm32mp2common = "-no-opengles3"
QT_CONFIG_FLAGS:append:stm32mp2common = " -opengles3"

QT_QPA_DEFAULT_PLATFORM ?= "wayland"
QT_QPA_EGLFS_INTEGRATION ?= ""
