SUMMARY = "WLAN Application to provision WILC via HTTP Web page"
DESCRIPTION = "The example is to demonstrate HTTP Web based provisioning, \
	the recipe contains a TCP socket based application which will let \
	the NGINX hosted webpage to add the router credentials to 	  \
	wpa_supplicant.conf and thereby enables the device to connect to  \
	configured router it in the STA mode."

AUTHOR = "Microchip Technology Inc"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=e24bfdbf6035afa17b105dc86b249d9f"
DEPENDS = "nginx"
SRC_URI = "git://github.com/gmarescotti/websocket-buildroot-external-microchip.git;protocol=https"
PV = "1.0+git${SRCPV}"
SRCREV = "12fe6e4cc211576b9d1fe5a02e930cf022720b46"

S = "${WORKDIR}/git"

do_compile () {
	${TARGET_PREFIX}gcc ${TARGET_CC_ARCH} ${TOOLCHAIN_OPTIONS} -Wall  \
	-std=gnu11 -g -D_REENTRANT -static ${S}/websocket_control.c 	  \
	${WORKDIR}/git/websocket_protocol.c -o websocket
}

do_install () {
    install -D -m 0755 --target-directory=${D}${ROOT_HOME} ${B}/websocket
}

FILES_${PN} += "${ROOT_HOME}"

COMPATIBLE_MACHINE = "(at91sam9|sama5)"
