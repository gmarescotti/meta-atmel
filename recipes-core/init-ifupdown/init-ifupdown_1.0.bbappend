# Extend the base recipe search path to $HERE/init-ifupdown
FILESEXTRAPATHS_prepend := "${THISDIR}/init-ifupdown-1.0:"

PR="r20"

SRC_URI += "file://wlan0.conf"
SRC_URI += "file://eth0.conf"

do_install_append() {
	install -d ${D}${sysconfdir}/network/interfaces.d
	install -m 0644 ${WORKDIR}/wlan0.conf ${D}${sysconfdir}/network/interfaces.d/
	install -m 0644 ${WORKDIR}/eth0.conf ${D}${sysconfdir}/network/interfaces.d/
}

