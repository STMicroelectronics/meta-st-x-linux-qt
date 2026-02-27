require recipes-st/images/st-image-weston.bb

inherit populate_sdk_qt6 features_check

RM_WORK_EXCLUDE = "${PN}"

# need to have wayland feature
REQUIRED_DISTRO_FEATURES = "wayland"

SUMMARY = "OpenSTLinux Qt Framework image based on weston image"

STM32MP_USERFS_IMAGE = "${IMAGE_BASENAME}"

# Define ROOTFS_MAXSIZE to 3GB
IMAGE_ROOTFS_MAXSIZE = "3145728"

# Define the size of userfs
STM32MP_USERFS_SIZE = "307200"
PARTITIONS_IMAGES[userfs]   = "${STM32MP_USERFS_IMAGE},${STM32MP_USERFS_LABEL},${STM32MP_USERFS_MOUNTPOINT},${STM32MP_USERFS_SIZE},FileSystem"

# For platform without GPU, only use startupscreen
QT_APPS ?= "boot2qt-demolauncher"
QT_APPS:stm32mp13common = "boot2qt-startupscreen"
QT_APPS:stm32mp21common = "boot2qt-startupscreen"

IMAGE_QT_PART = "   \
    packagegroup-x-linux-qt \
    packagegroup-qt6-modules \
    ${QT_APPS} \
"

#
# INSTALL addons
#
CORE_IMAGE_EXTRA_INSTALL += " \
    ${IMAGE_QT_PART}          \
"

IMAGE_FEATURES += "dev-pkgs"
