#!/bin/sh
source /etc/profile.d/qt_profile.sh

if pidof -qx -o $$ $(basename "${0}") ; then
  echo "Process $(basename "${0}") is already running"
  exit 1
else
  /usr/local/demo/medical/medical
  exit ${?}
fi
