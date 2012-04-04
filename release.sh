#!/bin/sh
echo "Cleaning project..."
echo "======================"
mvn clean

echo "Preparing release..."
echo "======================"
# use this below if you want to try it out
# -DdryRun=true 
mvn release:prepare -DautoVersionSubmodules=true

echo "======================"
echo "Hit ENTER if it looks good and you want to perform the release."
echo "Otherwise abort with CTRL-C."
echo "======================"
read

echo "Performing release..."
echo "======================"
mvn release:perform
