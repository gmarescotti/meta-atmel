# Extend the base recipe search path to $HERE/files
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR="r3"

SRC_URI += "file://hostapd.conf"

do_install_append() {
        install -m 0644 ${WORKDIR}/hostapd.conf ${D}${sysconfdir}/
}

pkg_postinst_ontarget_${PN} () {
	LAST3MAC=$(cat /sys/class/net/eth0/address | awk -F: '{print $4$5$6}')
	sed -i "s/ssid=DKC_WALLBOX_.*$/ssid=DKC_WALLBOX_${LAST3MAC^^}/" /etc/hostapd.conf

	## Questo script qui sotto non funziona perche':
	##  - wilc_sdio fallisce (qualche volta)
	##  - con lsmod si vede "wilc_sdio -1" e non si riesce a rimuovere
	## => a quel punto si puo' fare solo reboot.
	#
	## /etc/init.d/ifplugd stop
	## /etc/init.d/hostapd stop
	## rmmod wilc_sdio
	## modprobe wilc_sdio
	## /etc/init.d/ifplugd start
	## # /etc/init.d/hostapd start ## restarted by opkg automatically
	#
	
	( sleep 10 ; reboot )&
}

