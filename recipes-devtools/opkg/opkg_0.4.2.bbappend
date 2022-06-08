
PR = "r2"

do_install_append-target () {
	VERSION=$(awk -F "=" '/DKC_DISTRO_VERSION/ {print $2}' /etc/build)
	echo "src snapshots https://data.madein.it/sftp/uploads/Release_$VERSION" >>${D}${sysconfdir}/opkg/opkg.conf
}
