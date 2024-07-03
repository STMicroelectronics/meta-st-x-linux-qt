<center>

![X-LINUX-QT Logo](./_htmresc/x-linux-qt-logo.png)

</center>

X-LINUX-QT is an STM32 MPU OpenSTLinux Expansion Package that targets Qt based application and graphical user interface (GUI) development for the STM32MP25xx series microprocessors. It contains Linux® Qt™ Frameworks, as well as an ST Application Launcher based on Qt™ Framework and application examples to get started with Qt application development.

The Qt framework contains a comprehensive set of highly intuitive and modularized C++ library classes and is loaded with APIs to simplify your application development. Qt produces highly readable, easily maintainable and reusable code with high runtime performance and small footprint – and it's cross-platform.

This expansion package is a complete ecosystem that allow developers working with OpenSTLinux to create Qt based application very easily.

- All-in-one Qt solutions for the entire STM32MPU serie supporting Wayland and Weston.
- Pre-integrated into Linux distribution based on ST environment
- Include Qt frameworks to build UIs and Applications
- Include ST Application Launcher based on Qt framework for MPU devices (see supported platforms list)

# meta-st-x-linux-qt
OpenEmbedded meta layer to install Qt frameworks and tools for the STM32MPU.

## Compatibility
* The X-LINUX-QT OpenSTLinux Expansion Package v1.0.0 is compatible with the Yocto Project™ build systems (mickledore and later versions).
* It is validated using the *_Qt5 OpenEmbedded/Yocto Project layer_* [(*_meta-qt5_*)](https://github.com/meta-qt5/meta-qt5) over the OpenSTLinux Distributions v5.1.

## Supported STM32MPU devices
This expansion package is supporting below STM32 MPU boards.
  * STM32MP257F-EV1
  * STM32MP157F-DK2

## Re-generate X-LINUX-QT OpenSTLinux distribution

### Install OpenSTLinux distribution

* Please follow instructions from the wiki article [STM32MP Distribution package](https://wiki.st.com/stm32mpu/wiki/STM32MPU_Distribution_Package)

### Install X-LINUX-QT environment

* Clone the meta-st-x-linux-qt git repository

```
cd <Distribution Package installation directory>/layers/meta-st
git clone -b v1.0.0 https://github.com/STMicroelectronics/meta-st-x-linux-qt.git layers/meta-st/meta-st-x-linux-qt
```

* Clone the meta-qt5 git repository

```
 git clone -b scarthgap https://github.com/meta-qt5/meta-qt5.git layers/meta-qt5
```

### Source Yocto/openembedded build environment

```
DISTRO=openstlinux-weston MACHINE=stm32mp2 BSP_DEPENDENCY="layers/meta-qt5 layers/meta-st/meta-st-x-linux-qt" source layers/meta-st/scripts/envsetup.sh
```

### Build the X-LINUX-QT image

```
bitbake st-image-qt package-index
```

### Build the X-LINUX-QT SDK

```
bitbake st-image-qt -c do_populate_sdk
```

## Further information on [Qt 5.15 All Modules](https://doc.qt.io/qt-5.15/qtmodules.html)

* <https://doc.qt.io/qt-5.15/qtmodules.html>

## Further information on how to install and how to use X-LINUX-QT

* [X-LINUX-QT v1.0.0 expansion package](https://wiki.st.com/stm32mpu/wiki/X-LINUX-QT_expansion_package)

## Further information on Qt 5.15 Framework and Tools

* <https://doc.qt.io/qt-5.15/>

## Further information on Qt Group and STMicroelectronics partnership

* <https://www.st.com/content/st_com/en/partner/partner-program/partnerpage/Qt.html>
* <https://www.qt.io/partners/stmicroelectronics>

