
PR = "r1"

do_install_append () {
	echo "src snapshots https://data.madein.it/sftp/uploads/Release_0.0.1" >>${D}${sysconfdir}/opkg/opkg.conf
}
