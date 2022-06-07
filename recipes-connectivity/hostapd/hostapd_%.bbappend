# Extend the base recipe search path to $HERE/files
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR="r1"

SRC_URI += "file://hostapd.conf"

do_install_append() {
        install -m 0644 ${WORKDIR}/hostapd.conf ${D}${sysconfdir}/
}
