require conf/machine/include/st-machine-flashlayout-stm32mp.inc

do_create_sd_raw_file() {

	# Add sgdisk to path for create_sdcard_from_flashlayout.sh script
	export PATH="$PATH:/usr/sbin/"

	# Run raw generation script for an individual board given as argument
	set_and_check_tsv_file() {
		local board_name="$1"

		# Set the TSV_and RAW FILE variable
		TSV_FILE="${DEPLOY_DIR_IMAGE}/flashlayout_st-image-qt/optee/FlashLayout_sdcard_${board_name}-optee.tsv"
		RAW_FILE="${DEPLOY_DIR_IMAGE}/FlashLayout_sdcard_${board_name}-optee.raw"

		# Log the TSV_FILE variable
		echo "TSV_FILE set to: ${TSV_FILE}"

		# Check if the TSV_FILE exists and execute the script
		if [ -f "${TSV_FILE}" ]; then
			#bbwarn "Create SD raw command: ${DEPLOY_DIR_IMAGE}/scripts/create_sdcard_from_flashlayout.sh ${TSV_FILE}"
			#bbwarn "Raw file is ${RAW_FILE}"
			if [ -f "${RAW_FILE}" ]; then
				\rm "${RAW_FILE}"
			fi
			# Bug fix for bc path
			sed -i "s/ bc/ \/usr\/bin\/bc/g" ${DEPLOY_DIR_IMAGE}/scripts/create_sdcard_from_flashlayout.sh
			output=$(${DEPLOY_DIR_IMAGE}/scripts/create_sdcard_from_flashlayout.sh "${TSV_FILE}")
			#bbwarn "Script create_sdcard_from_flashlayout.sh output: ${output}"
		else
			bbwarn "Board configuration file ${TSV_FILE} not found"
		fi
	}

    # Determine the board name based on the MACHINE variable
    case "${MACHINE}" in
        stm32mp25-disco)
            set_and_check_tsv_file "stm32mp257f-dk"
            ;;
        stm32mp23-disco)
            set_and_check_tsv_file "stm32mp235f-dk"
            ;;
        stm32mp21-disco)
            set_and_check_tsv_file "stm32mp215f-dk"
            ;;
        stm32mp25-eval)
            set_and_check_tsv_file "stm32mp257f-ev1"
            ;;
        stm32mp15-disco)
            set_and_check_tsv_file "stm32mp157f-dk2"
            ;;
        stm32mp13-disco)
            set_and_check_tsv_file "stm32mp135f-dk"
            ;;
        stm32mp15-eval)
            set_and_check_tsv_file "stm32mp157f-ev1"
            ;;
        stm32mp2)
            set_and_check_tsv_file "stm32mp257f-dk"
            set_and_check_tsv_file "stm32mp235f-dk"
            set_and_check_tsv_file "stm32mp215f-dk"
            set_and_check_tsv_file "stm32mp257f-ev1"
            ;;
        stm32mp1)
            set_and_check_tsv_file "stm32mp157f-dk2"
            set_and_check_tsv_file "stm32mp135f-dk"
            set_and_check_tsv_file "stm32mp157f-ev1"
            ;;            
    esac

}

addtask do_create_sd_raw_file after do_image_complete do_create_flashlayout_config
