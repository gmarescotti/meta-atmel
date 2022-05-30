#miao
# Extend the base recipe search path
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR="r5"

SRC_URI += "file://issue-ifdown"
SRC_URI += "file://issue-ifup"
SRC_URI += "file://speed10-ifpreup"

do_install_append() {
	install -d ${D}${sysconfdir}/network/if-down.d
	install -d ${D}${sysconfdir}/network/if-up.d
	install -d ${D}${sysconfdir}/network/if-pre-up.d
	install -m 0755 ${WORKDIR}/issue-ifdown ${D}${sysconfdir}/network/if-down.d/
	install -m 0755 ${WORKDIR}/issue-ifup ${D}${sysconfdir}/network/if-up.d/
	install -m 0755 ${WORKDIR}/speed10-ifpreup ${D}${sysconfdir}/network/if-pre-up.d/

	sed -i 's/eth0/eth0 wlan0 can0/' ${D}${sysconfdir}/ifplugd/ifplugd.conf
}

