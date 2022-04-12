SUMMARY = "a SocketCAN over Ethernet tunnel"
HOMEPAGE = "https://github.com/mguentner/cannelloni"
LICENSE = "GPLv2"

SRC_URI = "git://github.com/mguentner/cannelloni.git;protocol=https \
           file://0001-Use-Remote-Ip.patch \
          "
SRCREV = "958d1a4eadcd1791d8ef3423d33620bb8fbe185a"

PV = "20160414+${SRCPV}"

LIC_FILES_CHKSUM = "file://gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit cmake

S = "${WORKDIR}/git"

PACKAGECONFIG ??= "lksctp-tools"
PACKAGECONFIG[lksctp-tools] = "-DSCTP_SUPPORT=true, -DSCTP_SUPPORT=false, lksctp-tools"
