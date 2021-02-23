#!/bin/sh

# Either just run this script without any input argument to start the application.
# Or provide the argument DEBUG or TRACE to select one of the two logging levels.

if [ $# -eq 0 ]
  then
    mvn spring-boot:run
  else
    mvn spring-boot:run \
    -Dspring-boot.run.arguments=--logging.level.se.kth.iv1201.recruitmentapplicationgroup5=$1    
fi 
