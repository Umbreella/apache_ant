# Build script

![apacheant](https://img.shields.io/badge/apacheant-A81C7D?style=for-the-badge&logo=apacheant&logoColor=white)


## Dependencies

* Installed [Apache Ant 1.10.13](https://ant.apache.org/bindownload.cgi)
* **./src/main/java/*/**
* **./src/test/java/*/**

## Description

* Adds paths for all dependencies
* Adds path on *antcontrib* from **lib/*.jar**


* Removes old **./target**


* Gets dependencies from network *(get-deps)*


* Starts compile code and tests *(compile)*


* Creates **.jar** and **MANIFEST.MF** *(build)*


* Executes tests with junit from **lib** (test)


* Сhecks diff classes and commit (diff)


## Content

* [build.xml](build.xml) - apache net config file

* [build.properties](build.properties) - properties for build.xml

* [lib](lib) - required libraries

* [sounds](sounds) - sounds for build result

* [target](target) - script result