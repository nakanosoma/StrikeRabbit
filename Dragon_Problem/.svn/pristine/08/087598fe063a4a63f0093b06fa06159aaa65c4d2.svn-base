#!/bin/sh

boolean="none"
for each in `ls -1 *.jnilib 2> /dev/null`
do
    boolean="exists"
done

if [ "$boolean" = "none" ]
then
    jar xvf gluegen-rt-natives-macosx-universal.jar
    jar xvf jogl-all-natives-macosx-universal.jar
    rm -rf META-INF
    rm -rf jogamp
fi

exit 0