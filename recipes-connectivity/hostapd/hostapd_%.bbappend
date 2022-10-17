# Extend the base recipe search path to $HERE/files
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR="r2"

SRC_URI += "file://hostapd.conf"

do_install_append() {
        install -m 0644 ${WORKDIR}/hostapd.conf ${D}${sysconfdir}/
}

pkg_postinst_ontarget_${PN} () {
	LAST3MAC=$(cat /sys/class/net/eth0/address | awk -F: '{print $4$5$6}')
	sed -i "s/ssid=DKC_WALLBOX_.*$/ssid=DKC_WALLBOX_${LAST3MAC^^}/" /etc/hostapd.conf
}

