FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# Patches from https://github.com/meta-qt5/qtwayland/commits/b5.15
SRC_URI += "file://0001-QWaylandShmBackingStore-Preserve-buffer-contents-bet.patch"

PACKAGECONFIG = " \
    wayland-client \
    wayland-server \
    wayland-egl \
"

