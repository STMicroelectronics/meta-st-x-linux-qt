#!/bin/sh
source /etc/profile.d/qt_profile.sh

EXAMPLE_APPLICATION="/usr/share/examples/opengl/cube/cube"

[[ ! -x ${EXAMPLE_APPLICATION} ]] && exit 1

if pidof -qx -o $$ $(basename "${0}") ; then
  echo "Process $(basename "${0}") is already running"
  exit 1
else
  ${EXAMPLE_APPLICATION}
  exit ${?}
fi
