# Extend the base recipe search path to $HERE/files
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR="r2"

SRC_URI += "file://wpa_supplicant.conf"
SRC_URI += "file://wilc_sdio.conf"

do_install_append () {
	install -d ${D}/${sysconfdir}
	install -d ${D}/${sysconfdir}/modules-load.d
	install -m 0644 ${WORKDIR}/wilc_sdio.conf ${D}/${sysconfdir}/modules-load.d/wilc_sdio.conf
	install -m 0600 ${WORKDIR}/wpa_supplicant.conf ${D}/${sysconfdir}
}

