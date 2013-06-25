@echo off

set CLASSPATH=.;..\jars\hsqldb.jar;%CLASSPATH%

@echo on

java org.hsqldb.util.DatabaseManagerSwing -url "jdbc:hsqldb:PG13"

pause