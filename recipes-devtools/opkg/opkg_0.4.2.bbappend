
PR = "r3"

do_install_append () {
	VERSION=${DKC_DISTRO_VERSION}
	echo "# USING RELEASE $VERSION" >>${D}${sysconfdir}/opkg/opkg.conf
	echo "src snapshots https://data.madein.it/sftp/uploads/Release_$VERSION" >>${D}${sysconfdir}/opkg/opkg.conf
}
