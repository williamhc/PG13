REM @echo off

call SetClasspath

REM @echo on

IF "%1" NEQ "" (SET SLEEP=%1) ELSE (SET SLEEP=1)

cd database
call RestoreDB.bat
cd..

java -cp %CLASSPATH% acceptanceTests.TestRunner %SLEEP%
 


