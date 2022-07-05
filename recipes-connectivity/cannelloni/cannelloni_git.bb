SUMMARY = "a SocketCAN over Ethernet tunnel"
HOMEPAGE = "https://github.com/mguentner/cannelloni"
LICENSE = "GPLv2"

PR="r1"

SRC_URI = "git://github.com/mguentner/cannelloni.git;protocol=https \
           file://cannelloniservice \
           file://0001-Use-Remote-Ip.patch \
          "
SRCREV = "958d1a4eadcd1791d8ef3423d33620bb8fbe185a"

PV = "20160414+${SRCPV}"

LIC_FILES_CHKSUM = "file://gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit cmake

S = "${WORKDIR}/git"

PACKAGECONFIG ??= "lksctp-tools"
PACKAGECONFIG[lksctp-tools] = "-DSCTP_SUPPORT=true, -DSCTP_SUPPORT=false, lksctp-tools"

# Added Init Script
inherit siteinfo update-rc.d systemd

SYSTEMD_SERVICE_${PN} = "cannelloni.service"

# Path delle cartelle e dei file da installare nel pacchetto
# FILES_${PN} += "/opt/serial2mqtt/* /home/root/.wgetrc"

INITSCRIPT_NAME = "cannelloniservice"
INITSCRIPT_PARAMS = "defaults 99 20"

do_install_append () {
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/cannelloniservice ${D}/etc/init.d/cannelloniservice
}

