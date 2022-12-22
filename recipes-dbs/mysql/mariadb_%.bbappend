# Extend the base recipe search path to $HERE/files
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR="r2"

do_install_append() {
	install -d ${D}/var/log
	ln -s -r ${D}/var/log/mysqld.log ${D}/var/log/mysqld.err
}

