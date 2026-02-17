require recipes-st/images/st-image-weston.bb

inherit populate_sdk_qt6 features_check

# need to have wayland feature
REQUIRED_DISTRO_FEATURES = "wayland"

SUMMARY = "OpenSTLinux Qt Framework image based on weston image"

STM32MP_USERFS_IMAGE = "${IMAGE_BASENAME}"

# Define ROOTFS_MAXSIZE to 3GB
IMAGE_ROOTFS_MAXSIZE = "3145728"

# Define the size of userfs
STM32MP_USERFS_SIZE = "307200"

IMAGE_QT_PART = "   \
    packagegroup-x-linux-qt \
"

#
# INSTALL addons
#
CORE_IMAGE_EXTRA_INSTALL += " \
    ${IMAGE_QT_PART}          \
"

IMAGE_FEATURES += "dev-pkgs"
