SUMMARY = "GNU nano - an enhanced clone of the Pico text editor"

DESCRIPTION = "GNU nano - an enhanced clone of the Pico text editor"

AUTHOR = "Chris Allegretta <chris@asty.org>, \
David Lawrence Ramsey <pooka109@gmail.com>, \
Jordi Mallach <jordi@gnu.org>, \
Adam Rogoyski <rogoyski@cs.utexas.edu>, \
Robert Siembarski <rjs@andrew.cmu.edu>, \
Rocco Corsi <rocco.corsi@sympatico.ca>, \
David Benbennick <dbenbenn@math.cornell.edu>, \
Mike Frysinger <vapier@gentoo.org>"

HOMEPAGE = "http://www.nano-editor.org"
BUGTRACKER = "https://savannah.gnu.org/bugs/?group=nano"

SECTION = "console/utils"
PRIORITY = "optional"

PROVIDES += "editor/nano"

LICENSE = "Nano GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

DEPENDS = "ncurses"
PR = "r0"

PV_MAJOR = "7.0"

SRC_URI = "https://www.nano-editor.org/dist/v7/nano-7.0.tar.gz"
SRC_URI[md5sum]="13c5bb0a27b71640489fd3569bce987d"
SRC_URI[sha256sum] = "767d595237a4b40b981e2daaeb31de94283d60b1fae03fbd52a67d95c454518a"
inherit autotools gettext

RDEPENDS_${PN} = "ncurses"
