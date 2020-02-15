## Parking application

This application manages the car parking and ticket collection.

## Technical requirement
JAVA 8 and above
maven : To install third party unit testing jar from maven repository (junit-4.13.jar, hamcrest-all-1.3.jar)
linux system

## File structure
parking_lot contain four directory.
1) bin : contain linux script to compile and run project (setup,parking_lot)
2) src : contain source files written in JAVA

3) lib : This will generate after downloading unit test dependencies and contain third party jar.

4) target : This will generate after application compilation (bin/setup), this directory contain .class files.

## Bootstrap application
parking_lot as present working dir.
compile and run unit test cases by using `bin/setup`
run application by using `bin/parking_lot <physical file path>` (Make sure file path should be physical file path)

## Entry point of application
ParkingLot main() method is entry point for application.

## Sample test
Open terminal with parking_lot as pwd
run `bin/setup`
run `bin/parking_lot file_input.txt`
