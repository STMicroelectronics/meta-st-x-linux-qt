# Add examples
PACKAGECONFIG:append = " examples"

QT_INSTALL_EXAMPLES_SOURCES := "ON"
QT_INSTALL_EXAMPLES_SOURCES_BY_DEFAULT := "ON"
EXTRA_OECMAKE:append:class-target = "\
    -DQT_INSTALL_EXAMPLES_SOURCES=${QT_INSTALL_EXAMPLES_SOURCES} \
    -DQT_INSTALL_EXAMPLES_SOURCES_BY_DEFAULT=${QT_INSTALL_EXAMPLES_SOURCES_BY_DEFAULT} \
"

do_install:append() {
    if [ -e ${D}${QT6_INSTALL_EXAMPLESDIR}/corelib/serialization/cbordump/cbortag.py ]; then
        sed -i ${D}${QT6_INSTALL_EXAMPLESDIR}/corelib/serialization/cbordump/cbortag.py \
            -e 's|/bin/env|/usr/bin/env|'
    fi
}
