
do_install_append () {
	echo "src snapshots https://data.madein.it/sftp/uploads/Release_1.0.0" >>${D}${sysconfdir}/opkg/opkg.conf
}
