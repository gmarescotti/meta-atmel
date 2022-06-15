SUMMARY = "RootFs files needed for WILC connectivity recipies"
DESCRIPTION = "The recipie installs following 3 different types of files    \
	to the rootfs							    \
	1) Scripts to demonstrate WiFi/BLE applications on WILC		    \
		a. Start_AP.sh	-> Starts WILC as AP and Hosts Web server   \
		b. Start_STA.sh	-> Connects to a AP in STA mode	 	    \
		c. Start_BT.sh	-> Start BLE Applications		    \
				(Heartrate/Transparent/wifiProv service)    \
		d. Start_Provision.sh -> Places WILC back to Provision Mode \
									    \
	2) HTML files which enables web based provisioning to provide WiFi  \
	credentials. These HTML files are used by the NGINX webserver.	    \
									    \
	3) Hostapd configuration files needed to bring up the WILC in AP    \
	mode, the configuration file contains SSID and Beaconing information"

AUTHOR = "Microchip Technology Incorporated"
SECTION = "net"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

DEPENDS = "mariadb"
RDEPENDS_${PN} = "mariadb-server"

PR="r15"

SRC_URI = "git://github.com/gmarescotti/linux4sam-wilc-demo-fs-overlay.git;protocol=https"
# PV = "1.0+git${SRCPV}" USE DEFAULT VERSION FROM FILENAME
SRCREV = "28b681a1f70647ca78466bb5d5e681769e7f3271"
S = "${WORKDIR}/git"

do_install () {
    install -D -m 0644 --target-directory=${D}/etc/sudoers.d/ ${S}/sama5d27_wlsom1_ek/etc/sudoers.d/*
    install -D -m 0755 --target-directory=${D}${ROOT_HOME} ${S}/sama5d27_wlsom1_ek/root/Start_*
    install -d ${D}${datadir}/nginx/html
    install -d ${D}${datadir}/nginx/html/js
    install -d ${D}${datadir}/nginx/html/img
    install -d ${D}${datadir}/nginx/html/css
    install -D -m 0644 --target-directory=${D}${datadir}/nginx/html/ ${S}/sama5d27_wlsom1_ek/usr/html/*.*
    install -D -m 0755 --target-directory=${D}${datadir}/nginx/html/cgi-bin/ ${S}/sama5d27_wlsom1_ek/usr/html/cgi-bin/*.*
    install -D -m 0644 --target-directory=${D}${datadir}/nginx/html/inc/ ${S}/sama5d27_wlsom1_ek/usr/html/inc/*
    install -D -m 0644 --target-directory=${D}${datadir}/nginx/html/js/ ${S}/sama5d27_wlsom1_ek/usr/html/js/*
    install -D -m 0644 --target-directory=${D}${datadir}/nginx/html/img/ ${S}/sama5d27_wlsom1_ek/usr/html/img/*
    install -D -m 0644 --target-directory=${D}${datadir}/nginx/html/css/ ${S}/sama5d27_wlsom1_ek/usr/html/css/*
    install -D -m 0644 --target-directory=${D}${sysconfdir}/ ${S}/sama5d27_wlsom1_ek/etc/wilc*
}

FILES_${PN} += "/home/root/*"
FILES_${PN} += "${datadir}/nginx/html/*"
FILES_${PN} += "${datadir}/nginx/html/inc/*"
FILES_${PN} += "${datadir}/nginx/html/js/*"
FILES_${PN} += "${datadir}/nginx/html/img/*"
FILES_${PN} += "${datadir}/nginx/html/css/*"
FILES_${PN} += "${sysconfdir}/wilc*"

inherit allarch
do_compile[noexec] = "1"
INHIBIT_DEFAULT_DEPS="1"

#COMPATIBLE_MACHINE = "(at91sam9|sama5)"
COMPATIBLE_MACHINE = "(at91sam9|sama5|qemux86-64)"

