#!/bin/sh

export XDG_RUNTIME_DIR=/run/user/`id -u weston`
export QT_QPA_PLATFORM=wayland

# Enable back the desktop virtual keyboard
export QT_IM_MODULE=
export QT_VIRTUALKEYBOARD_DESKTOP_DISABLE=0

# Fix issue with vulkan tests
if [[ -f "/usr/lib/libvulkan.so.1" ]] ; then
  export QT_VULKAN_LIB=/usr/lib/libvulkan.so.1
fi

# Use ffmpeg for Qt Media backend
export QT_MEDIA_BACKEND="ffmpeg"
