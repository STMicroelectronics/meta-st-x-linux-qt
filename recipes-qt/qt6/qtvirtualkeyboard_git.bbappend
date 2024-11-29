# Add examples
inherit qt6-examples

# This package depends on the selected machine
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Remove dependency with dictionaries
PACKAGES:remove = "${PN}-dictionaries"
RRECOMMENDS:${PN}:remove = "${PN}-dictionaries"
