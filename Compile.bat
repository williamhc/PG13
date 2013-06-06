REM @echo off

REM
REM This script requires that the "javac" command be on the system command path.
REM This can be accomplished by modifying the path statement below
REM to include "C:\Program Files\Java\jdk1.6.0_03\bin" (or whatever your path is).
REM Alternatively, you could add the path to "javac" to the PATH Environment variable: 
REM   Settings-->Control Panel-->System-->Advanced-->Environment Variables-->Path
REM


REM clean all .class files out of the bin directory
if not exist %CD%\bin mkdir bin
cd bin
erase /S /Q *.class
cd ..


call SetClasspath


REM @echo on

REM javac -d bin\ -cp %classpath% src\srsys\objects\*.java

REM javac -d bin\ -cp %classpath% src\srsys\persistence\*.java

javac -d bin\ -cp %classpath% pg13\models\*.java pg13\persistence\*.java pg13\app\*.java pg13\business\*.java  pg13\presentation\*.java
javac -d bin\ -cp %classpath% tests\models\*.java tests\business\*.java tests\*.java
