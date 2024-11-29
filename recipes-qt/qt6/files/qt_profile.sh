#!/bin/sh

export XDG_RUNTIME_DIR=/run/user/`id -u weston`
export QT_QPA_PLATFORM=@platform@

# Enable back the desktop virtual keyboard
export QT_IM_MODULE=
export QT_VIRTUALKEYBOARD_DESKTOP_DISABLE=0

# Fix issue with vulkan tests
if [[ -f "/usr/lib/libvulkan.so.1" ]] ; then
  export QT_VULKAN_LIB=/usr/lib/libvulkan.so.1
fi
# Use ffmpeg for Qt Media backend
export QT_MEDIA_BACKEND="ffmpeg"

export QT_QPA_EGLFS_KMS_CONFIG=/usr/share/qt6/cursor.json
export QT_QPA_EGLFS_ALWAYS_SET_MODE=1
export FB_MULTI_BUFFER=2
export QT_QPA_EGLFS_FORCE888=1
export QT_QPA_EGLFS_FORCEVSYNC=1
export QT_QPA_EGLFS_KMS_ATOMIC=1
export QSG_NO_DEPTH_BUFFER=1
export QSG_NO_STENCIL_BUFFER=1
export QSG_NO_CLEAR_BUFFERS=1

# LinuxFB settings
if [[ "${QT_QPA_PLATFORM}" == "linuxfb" ]] ; then
  export QT_QPA_FB_DRM=1
  export QT_QUICK_BACKEND=software
  export QSG_RENDER_LOOP=basic
  export QMLSCENE_DEVICE=softwarecontext
fi
