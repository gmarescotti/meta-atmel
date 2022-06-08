# Extend the base recipe search path to $HERE/init-ifupdown
FILESEXTRAPATHS_prepend := "${THISDIR}/init-ifupdown-1.0:"

PR="r21"

SRC_URI += "file://wlan0.conf"
SRC_URI += "file://eth0.conf"

do_install_append() {
	install -d ${D}${sysconfdir}/network/interfaces.d
	install -m 0644 ${WORKDIR}/wlan0.conf ${D}${sysconfdir}/network/interfaces.d/
	install -m 0644 ${WORKDIR}/eth0.conf ${D}${sysconfdir}/network/interfaces.d/
	sed -i '/^nfsroot=.*/a exit \0 # GGG removed for eth0 problems...' ${D}${sysconfdir}/network/if-pre-up.d/nfsroot
}

