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

javac -d bin\ -cp %classpath% src\pg13\app\*.java src\pg13\business\*.java src\pg13\models\*.java src\pg13\org\eclipse\wb\swt\*.java src\pg13\persistence\*.java src\pg13\presentation\*.java 
javac -d bin\ -cp %classpath% src\tests\*.java src\tests\business\*.java src\tests\models\*.java src\tests\persistence\*.java src\tests\integration\*.java src\tests\atrStubs\*.java
