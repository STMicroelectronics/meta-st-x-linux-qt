DESCRIPTION = "Meta task for QBSP creation"
LICENSE = "MIT"

inherit qbsp

S = "${WORKDIR}"

PV = "${QT_VERSION}"

# Name used in the online installer
QBSP_NAME = "OpenSTLinux for Qt ${QT_VERSION}"

# Pretty names for the devices
DEPLOY_CONF_NAME:stm32mp13-disco = "STM32MP135F-DK"
DEPLOY_CONF_NAME:stm32mp15-disco = "STM32MP157F-DK2"
DEPLOY_CONF_NAME:stm32mp15-eval = "STM32MP157F-EV1"
DEPLOY_CONF_NAME:stm32mp21-disco = "STM32MP21 disco boards (DK)"
DEPLOY_CONF_NAME:stm32mp23-disco = "STM32MP23 disco boards (DK)"
DEPLOY_CONF_NAME:stm32mp25-disco = "STM32MP25 disco boards (DK)"
DEPLOY_CONF_NAME:stm32mp25-eval = "STM32MP25 eval boards (EV)"
DEPLOY_CONF_TYPE = "OpenSTLinux"

QBSP_DEVICE_IP = "192.168.7.1"

# define QBSP PRODUCT component for the online installer tree
QBSP_PRODUCT = "ostl"

# path where qbsp content is installed under the SDK installation root
QBSP_INSTALL_PATH = "/${QT_VERSION}/OSTL-QT/${MACHINE}"

# recipes that are used for image and the SDK
QBSP_SDK_TASK = "st-image-qt"
QBSP_IMAGE_TASK = "st-image-qt"

# add dependency to raw file
QBSP_IMAGE_DEPENDS += "\
    st-image-qt:do_create_sd_raw_file \
    st-image-qbsp-conf:do_deploy\
"

# license agreement is shown in the installer
QBSP_LICENSE_NAME = "ST Software License Agreement"
QBSP_LICENSE_FILE = "${STM32MP_BASE}/conf/eula/ST_EULA_SLA"

# outout files from image build (DEPLOY_DIR_IMAGE)
# and sdk build (TOOLCHAIN_OUTPUTNAME)
QBSP_IMAGE_CONTENT = "\
*.conf \
*.raw \
${IMAGE_LINK_NAME}.spdx.tar.zst \
${IMAGE_LINK_NAME}.manifest\
"
QBSP_SDK = "${SDK_NAME}-toolchain-${SDK_VERSION}"
