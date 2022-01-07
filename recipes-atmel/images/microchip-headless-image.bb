DESCRIPTION = "An image for network and communication."
LICENSE = "MIT"
PR = "r1"

require atmel-demo-image.inc

hostname_pn-base-files = "dkc"

IMAGE_INSTALL += "\
    mpg123 \
    mpio \
    "

IMAGE_INSTALL_append_sama5d2 = " nodejs nodejs-npm greengrass"
IMAGE_INSTALL_append_sama5d3 = " nodejs nodejs-npm"
IMAGE_INSTALL_append_sama5d4 = " nodejs nodejs-npm"

IMAGE_INSTALL_append_sama5d2-ptc-ek = " ptc-examples"
IMAGE_INSTALL_append_sama5d2-ptc-ek-sd = " ptc-examples"
IMAGE_INSTALL_append_sama5d27-wlsom1-ek-sd = " ptc-examples \
				nginx \
				serial2mqtt \
				dkc-colonnina-image \
				wilc-demo-fs-overlay \
				wilc-ble-demo \
				wilc-websocket-demo "

IMAGE_INSTALL_append_sama7g5ek-sd = " bonnie++ iozone3 gstreamer1.0 \
				gstreamer1.0-plugins-bad \
				gstreamer1.0-plugins-good \
				gstreamer1.0-plugins-ugly fswebcam ffmpeg "

# Peppo 20211112
IMAGE_INSTALL_append_qemux86-64 = " nginx \
				serial2mqtt \
				dkc-colonnina-image \
				wilc-demo-fs-overlay \
				wilc-ble-demo \
				wilc-websocket-demo "
