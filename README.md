<center>

</center>

This branch allows user to rebuild Qt QBSP packages based on STM32 MPU OpenSTLinux.
QBSP packages target Qt based application and graphical user interface (GUI) development for the STM32MP25xx series microprocessors. It contains Linux® Qt™ Frameworks, as well as a Qt Application Launcher and application examples to get started with Qt application development.

The Qt framework contains a comprehensive set of highly intuitive and modularized C++ library classes and is loaded with APIs to simplify your application development. Qt produces highly readable, easily maintainable and reusable code with high runtime performance and small footprint – and it's cross-platform.

This is a complete ecosystem that allow developers working with OpenSTLinux to create Qt based application very easily.

- All-in-one Qt solutions for the entire STM32MPU serie supporting Wayland and Weston.
- Pre-integrated into Linux distribution based on ST environment
- Include Qt frameworks to build UIs and Applications
- Include Qt Application Launcher

# meta-st-x-linux-qt
OpenEmbedded meta layer to generate QBSP that will install Qt frameworks and tools for the STM32MPU.

## Supported STM32MPU devices
This expansion package is supporting below STM32 MPU boards.
  * STM32MP257F-EV1
  * STM32MP257F-DK
  * STM32MP215F-DK
  * STM32MP157F-DK2
  * STM32MP135F-DK

## Re-generate QBSP based on OpenSTLinux

### Install OpenSTLinux and Qt components

* For any additional question, refer to the wiki article [STM32MP Distribution package](https://wiki.st.com/stm32mpu/wiki/STM32MPU_Distribution_Package)

```
repo init -u https://github.com/STMicroelectronics/meta-st-x-linux-qt -b qbsp-manifest -m qbsp-ostl-qt-manifest.xml
repo sync
```

### Source Yocto/openembedded build environment

MACHINE can target stm32mp21-disco or other platforms. One example is:

```
DISTRO=openstlinux-weston MACHINE=stm32mp25-disco source layers/meta-st/scripts/envsetup.sh

bitbake-layers add-layer ../layers/meta-st/meta-st-x-linux-qt
bitbake-layers add-layer ../layers/meta-boot2qt/meta-boot2qt
bitbake-layers add-layer ../layers/meta-qt6

```

For commercial Qt modules needed by meta-qt6 layer, following lines must be added to your conf/local.conf file:

```
QT_COMMERCIAL_MODULES = "1"
QT_EDITION = "commercial"
```

### Build the X-LINUX-QT image

```
bitbake meta-qbsp-ostl-qt
```

## Further information on Qt Group and STMicroelectronics partnership

* <https://www.st.com/content/st_com/en/partner/partner-program/partnerpage/Qt.html>
* <https://www.qt.io/partners/stmicroelectronics>

