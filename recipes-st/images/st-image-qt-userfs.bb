require recipes-st/images/st-image-userfs.bb

# Define a proper userfs for st-image-qt
STM32MP_USERFS_IMAGE = "st-image-qt-userfs"

# temporary fix
IMAGE_PARTITION_MOUNTPOINT = "/usr/local"

PACKAGE_INSTALL += "\
    packagegroup-x-linux-qt \
"
