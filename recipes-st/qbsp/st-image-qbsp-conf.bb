DESCRIPTION = "Create QBSP conf file"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://ostl-qt-embedded-qt6-image-template.conf"

DEPENDS = "st-image-qt"
do_compile[depends] += "st-image-qt:do_create_sd_raw_file"


do_compile() {

	generic_replace() {
	    mkdir -p ${WORKDIR}/processed
		cp ${WORKDIR}/ostl-qt-embedded-qt6-image-template.conf ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-$1.conf
    
		sed -i "s/_PLATFORM_/$1/g" ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-$1.conf
		sed -i "s/_NAME_/$2/g" ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-$1.conf
		sed -i "s/_BOOT2QTVERSION_/$3/g" ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-$1.conf
		sed -i "s/_BOARD_/$4/g" ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-$1.conf
		export rawfilesize=$(stat --format="%s" ${DEPLOY_DIR_IMAGE}/FlashLayout_sdcard_$4-optee.raw)
		sed -i "s/_SIZE_/${rawfilesize}/g" ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-$1.conf
	}

    # Determine the board name based on the MACHINE variable
    case "${MACHINE}" in
        stm32mp25-disco)
            generic_replace "stm32mp25-disco" "STM32MP25 Discovery" ${QT_VERSION} "stm32mp257f-dk"
            ;;
        stm32mp23-disco)
			generic_replace "stm32mp23-disco" "STM32MP23 Discovery" ${QT_VERSION} "stm32mp235f-dk"
            ;;
        stm32mp21-disco)
			generic_replace "stm32mp21-disco" "STM32MP21 Discovery" ${QT_VERSION} "stm32mp215f-dk"
            ;;
        stm32mp25-eval)
			generic_replace "stm32mp25-eval" "STM32MP25 Eval" ${QT_VERSION} "stm32mp257f-ev1"
            ;;
        stm32mp15-disco)
			generic_replace "stm32mp15-disco" "STM32MP15 Discovery" ${QT_VERSION} "stm32mp157f-dk2"
            ;;
        stm32mp13-disco)
			generic_replace "stm32mp13-disco" "STM32MP13 Discovery" ${QT_VERSION} "stm32mp135f-dk"
            ;;
        stm32mp15-eval)
			generic_replace "stm32mp15-eval" "STM32MP15 Eval" ${QT_VERSION} "stm32mp157f-ev1"
            ;;
        stm32mp2)
            generic_replace "stm32mp25-disco" "STM32MP25 Discovery" ${QT_VERSION} "stm32mp257f-dk"
			generic_replace "stm32mp23-disco" "STM32MP23 Discovery" ${QT_VERSION} "stm32mp235f-dk"
			generic_replace "stm32mp21-disco" "STM32MP21 Discovery" ${QT_VERSION} "stm32mp215f-dk"
			generic_replace "stm32mp25-eval" "STM32MP25 Eval" ${QT_VERSION} "stm32mp257f-ev1"
            ;;
        stm32mp1)
			generic_replace "stm32mp15-disco" "STM32MP15 Discovery" ${QT_VERSION} "stm32mp157f-dk2"
			generic_replace "stm32mp13-disco" "STM32MP13 Discovery" ${QT_VERSION} "stm32mp135f-dk"
			generic_replace "stm32mp15-eval" "STM32MP15 Eval" ${QT_VERSION} "stm32mp157f-ev1"
            ;;            
    esac

}

do_deploy() {
    case "${MACHINE}" in
        stm32mp25-disco)
			cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp25-disco.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp25-disco.conf
            ;;
        stm32mp23-disco)
			cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp23-disco.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp23-disco.conf
            ;;
        stm32mp21-disco)
			cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp21-disco.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp21-disco.conf
            ;;
        stm32mp25-eval)
			cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp25-eval.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp25-eval.conf
            ;;
        stm32mp15-disco)
			cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp15-disco.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp15-disco.conf
            ;;
        stm32mp13-disco)
			cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp13-disco.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp13-disco.conf
            ;;
        stm32mp15-eval)
			cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp15-eval.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp15-eval.conf
            ;;
        stm32mp2)
            cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp25-disco.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp25-disco.conf
			cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp23-disco.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp23-disco.conf
			cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp21-disco.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp21-disco.conf
			cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp25-eval.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp25-eval.conf
            ;;
        stm32mp1)
			cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp15-disco.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp15-disco.conf
			cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp13-disco.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp13-disco.conf
			cp ${WORKDIR}/processed/ostl-qt-embedded-qt6-image-stm32mp15-eval.conf ${DEPLOY_DIR_IMAGE}/ostl-qt-embedded-qt6-image-stm32mp15-eval.conf
            ;;            
    esac    
}

addtask deploy after do_compile

