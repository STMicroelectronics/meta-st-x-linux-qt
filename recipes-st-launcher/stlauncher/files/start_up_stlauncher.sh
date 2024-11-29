#!/bin/sh
source /etc/profile.d/weston_profile.sh
source /etc/profile.d/qt_profile.sh

# Default platform is wayland
[[ -z "${QT_QPA_PLATFORM}" ]] && QT_QPA_PLATFORM=wayland

my_self=stlauncher
my_pid=$(pidof -s ${my_self})

if [[ -n "${my_pid}" ]] ; then
  echo "Process \"${my_self}\" is already running"
  exit 1
else
  /usr/share/qt/@stlauncher@/stlauncher -platform ${QT_QPA_PLATFORM}
  exit ${?}
fi
