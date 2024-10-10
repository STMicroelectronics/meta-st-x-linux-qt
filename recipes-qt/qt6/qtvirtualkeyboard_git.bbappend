# Remove dependency with dictionaries
PACKAGES:remove = "${PN}-dictionaries"
RRECOMMENDS:${PN}:remove = "${PN}-dictionaries"

# Add examples
PACKAGECONFIG:append = " examples"
