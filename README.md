# Retail Test Automation Framework

## Getting Started

These instructions will help to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

We would need following to setup in Windows or MAC machine

1. Java 1.8

* Download and install Java 1.8 JDK
* Configure environment variables and define Java_Home variable
* Launch command prompt and run “Java -version” command
* Java version information should be displayed

2. Maven

* Download Apache Maven zip file
* Setup environment variables for Maven
* Launch command prompt and run “mvn -version” command
* Maven version information should be displayed

3. Browsers
* Latest versions of Chrome must be installed (used Desktop chrome version: 69.0.3497.100 and mobile chrome version:68.0.3440.91 )

4. Appium 

* Download and install Node.js
* Open Node.js command prompt as an administrator and run command "npm install -g appium"
* After installation, run "appium -v" command
* Appium version information should be displayed

5. Enable USB Debugging on Real Android Device/Android Emulator

### Test Automation Framework Execution

Following are the steps to run tests in Retail Test Automation Framework

1. Test script execution in eclipse through testng.xml

* Connect real android device or power on the android emulator
(Command to start emulator - "%ANDROID_HOME%/emulator/emulator -avd Pixel_2"  where Pixel_2 - name of the emulator)

* Start appium using the command “appium -a 127.0.0.1” 
* Download source code from Repository
( https://github.com/optimusinfo/ecomportaltestautomation.git   Branch : Retail-Demo-Scripts)

* Import source code in Eclipse IDE as Maven project
* Right click on respective testng-xml file under the folder testsuites
* Execute "FullDemoTests.xml" file as testNG Suite to run the test suite 
* Surefire reports will be generated in target-output folder in root folder

2. Test script execution using command line interface

* Connect real android device or power on the android emulator
* Start appium using the command “appium -a 127.0.0.1” 
* Download source code from Repository
* Launch command prompt in Windows or Terminal in Mac OSX
* Navigate to root directory of Test Automation Framework
* Run command "mvn clean test -DsuiteFile=testsuites/FullDemoTests.xml" to run the test suite
* Execute allure_report.bat file in root folder
* Allure report will be generated in root directory under folder allure-report (index.html)

3. Test script execution in eclipse through Maven

* Connect real android device or power on the android emulator
* Start appium using the command “appium -a 127.0.0.1”
* Download source code from Repository
* Import source code in Eclipse IDE as Maven project
* Right click on pom.xml file
* Execute pom.xml file as maven clean (Run As -> Maven clean)
* Execute pom.xml file as maven install/ maven test
* Execute allure_report.bat file in root folder
* Allure report will be generated in root directory under folder allure-report (index.html)
* Open index.html to view Test Report