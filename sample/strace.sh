#!/bin/bash

cmd=${1-strace}
if [ $cmd = 'clean' ]; then
    rm -rf strace
fi

mkdir -p strace
./gradlew clean shadowJar
strace -ff -o strace/thread java -jar build/libs/sample-1.0.0-SNAPSHOT-all.jar
