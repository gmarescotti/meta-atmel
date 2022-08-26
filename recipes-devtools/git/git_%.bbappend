# Extend the base recipe search path
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR="r1"

SRC_URI_append = "file://.gitconfig"

do_install_append() {
	install -d ${D}/home/root
	install -m 0644 ${WORKDIR}/.gitconfig ${D}/home/root/.gitconfig
}

FILES_${PN} += "/home/root/.gitconfig"

