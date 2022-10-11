SECTION = "kernel"
DESCRIPTION = "Linux kernel for Microchip ARM SoCs (aka AT91)"
SUMMARY = "Linux kernel for Microchip ARM SoCs (aka AT91)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""
FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"

#SRCREV = "3dba8c9991d2466eb5e9398de22fa7f28accee83"
#SRCREV = "8e4df35434d241f6e3a0a232754df5cc5fa5f601"
#SRCREV = "cb388a9aa5cc5a1145fd0a1449c9e0cce87c4c17"
# rgm1 => SRCREV = "87643a7a0f45005265b921f91fce5fc7ee3b63b9"
# SRCREV = "512fbc7cb24608745ac8d469e9afecd5f2159473"
SRCREV = "2bf54eaf0be84f87cdd385f8886e011da8a00c20"
SRCREV_sama7g5ek = "d67f0979dcc377863060e803a2280b7a7e1a22c0"

PV = "5.4+git${SRCPV}"

S = "${WORKDIR}/git"

KBRANCH = "linux-5.4-at91-rgm2"
KBRANCH_sama7g5ek-sd = "sama7g5_early"
KBRANCH_sama7g5ek-emmc = "sama7g5_early"
# SRC_URI = "git://github.com/linux4sam/linux-at91.git;protocol=git;branch=${KBRANCH}"
SRC_URI = "git://github.com/gmarescotti/linux-at91.git;protocol=git;branch=${KBRANCH}"
SRC_URI += "file://defconfig"
SRC_URI_remove_sama7g5ek = "file://defconfig"

python __anonymous () {
    if d.getVar('UBOOT_FIT_IMAGE', True) == 'xyes':
        d.appendVar('DEPENDS', ' u-boot-mkimage-native dtc-native')
}

do_configure_prepend() {
	if [ ! -f "${WORKDIR}/defconfig" ] && [ -n "${KBUILD_DEFCONFIG}" ]; then
		if [ -f "${S}/arch/${ARCH}/configs/${KBUILD_DEFCONFIG}" ]; then
			cp -f ${S}/arch/${ARCH}/configs/${KBUILD_DEFCONFIG} ${WORKDIR}/defconfig
                else
                        bbfatal "A KBUILD_DEFCONFIG '${KBUILD_DEFCONFIG}' was specified, but not present in the source tree"
                fi
        fi
}

do_configure_append() {
	frags=""
	for fragment in ${WORKDIR}/*.cfg
	do
		if [ -f ${fragment} ]; then
			cp -v ${fragment} ${B}
			frags=$frags" `basename ${fragment}`"
		fi
	done

	if [ ! -z "${frags}" ]; then
		echo "Fragments are: ${frags}"
		PATH=${S}/scripts/kconfig:${PATH}
		CFLAGS="${CFLAGS} ${TOOLCHAIN_OPTIONS}" HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}" HOSTCPP="${BUILD_CPP}" CC="${KERNEL_CC}" ARCH=${ARCH} merge_config.sh -n .config ${frags}  2>&1
		if [ $? -ne 0 ]; then
			bbfatal_log "Could not configure kernel fragments: ${frags}"
		fi
	fi
}

do_deploy_append() {
	if [ "${UBOOT_FIT_IMAGE}" = "xyes" ]; then
		DTB_PATH="${B}/arch/${ARCH}/boot/dts/"
		if [ ! -e "${DTB_PATH}" ]; then
			DTB_PATH="${B}/arch/${ARCH}/boot/"
		fi

		if [ -e ${S}/arch/${ARCH}/boot/dts/${MACHINE}.its ]; then
			cp ${S}/arch/${ARCH}/boot/dts/${MACHINE}*.its ${DTB_PATH}
			cd ${DTB_PATH}
			mkimage -f ${MACHINE}.its ${MACHINE}.itb
			install -m 0644 ${MACHINE}.itb ${DEPLOYDIR}/${MACHINE}.itb
			cd -
		fi
	fi
}

KERNEL_MODULE_AUTOLOAD += "atmel_usba_udc g_serial"

COMPATIBLE_MACHINE = "(sama5d2-xplained|sama5d2-xplained-sd|sama5d2-xplained-emmc|sama5d2-ptc-ek|sama5d2-ptc-ek-sd|sama5d27-som1-ek|sama5d27-som1-ek-sd|sama5d4-xplained|sama5d4-xplained-sd|sama5d4ek|sama5d3-xplained|sama5d3-xplained-sd|sama5d3xek|at91sam9x5ek|at91sam9m10g45ek|at91sam9rlek|sama5d2-icp-sd|sam9x60ek|sam9x60ek-sd|sama5d27-wlsom1-ek-sd|sama7g5ek)"
