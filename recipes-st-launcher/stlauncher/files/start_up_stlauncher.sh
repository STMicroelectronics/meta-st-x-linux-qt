#!/bin/sh
source /etc/profile.d/weston_profile.sh
source /etc/profile.d/qt_profile.sh

# Default platform is wayland
[[ -z "${QT_QPA_PLATFORM}" ]] && QT_QPA_PLATFORM=wayland

# Test if GPU on platform
if [[ -e /dev/galcore ]]; then
	my_self=qtlauncher
else
	my_self=startupscreen
	# for MPU without GPU, linuxfb backend used instead of wayland
	systemctl stop weston-graphical-session.service
	/usr/bin/psplash-drm-quit
fi

my_pid=$(pidof -s ${my_self})

if [[ -n "${my_pid}" ]] ; then
  echo "Process \"${my_self}\" is already running"
  exit 1
else
  su -l weston -c "/usr/bin/${my_self} -platform ${QT_QPA_PLATFORM} --fullscreen"
  exit ${?}
fi
