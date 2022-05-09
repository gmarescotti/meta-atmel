#miao
# Extend the base recipe search path
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR="r3"

SRC_URI += "file://issue-ifdown"
SRC_URI += "file://issue-ifup"

do_install_append() {
	install -d ${D}${sysconfdir}/network/if-down.d
	install -d ${D}${sysconfdir}/network/if-up.d
	install -m 0755 ${WORKDIR}/issue-ifdown ${D}${sysconfdir}/network/if-down.d/
	install -m 0755 ${WORKDIR}/issue-ifup ${D}${sysconfdir}/network/if-up.d/

	sed -i 's/eth0/eth0 wlan0/' ${D}${sysconfdir}/ifplugd/ifplugd.conf
}

