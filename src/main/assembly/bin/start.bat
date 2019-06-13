@echo off & setlocal enabledelayedexpansion
set JVM_XMX_DEF=64m
set JVM_XMS_DEF=64m
set JVM_PERMSIZE_DEF=8m

cd ..\conf
set MAIN_CLASS=
FOR /F "eol=; tokens=2,2 delims==" %%i IN ('findstr /i "mail.class" config.properties') DO set MAIN_CLASS=%%i
echo "application entrance:%MAIN_CLASS%"

set JVM_XMX=64m
FOR /F "eol=; tokens=2,2 delims==" %%i IN ('findstr /i "jvm.xmx" config.properties') DO set JVM_XMX=%%i
if "%JVM_XMX%"=="" set JVM_XMX=%JVM_XMX_DEF%
set JVM_XMS=64m
FOR /F "eol=; tokens=2,2 delims==" %%i IN ('findstr /i "jvm.xms" config.properties') DO set JVM_XMS=%%i
if "%JVM_XMS%"=="" set JVM_XMS=%JVM_XMS_DEF%
set JVM_PERMSIZE=8m
FOR /F "eol=; tokens=2,2 delims==" %%i IN ('findstr /i "jvm.permsize" config.properties') DO set JVM_PERMSIZE=%%i
if "%JVM_PERMSIZE%"=="" set JVM_PERMSIZE=%JVM_PERMSIZE_DEF%
echo "configurations for jvm:JVM_XMX=%JVM_XMX%, JVM_XMS=%JVM_XMS%, JVM_PERMSIZE=%JVM_PERMSIZE%"

cd ..\lib
set LIB_JARS=""
for %%i in (*) do set LIB_JARS=!LIB_JARS!;..\lib\%%i

cd ..\bin
if ""%1"" == ""debug"" goto debug
if ""%1"" == ""jmx"" goto jmx
java -Xms%JVM_XMS% -Xmx%JVM_XMX% -XX:MaxPermSize=%JVM_PERMSIZE% -classpath ..\conf;%LIB_JARS% %MAIN_CLASS%
goto end

:debug
java -Xms%JVM_XMS% -Xmx%JVM_XMX% -XX:MaxPermSize=%JVM_PERMSIZE% -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n -classpath ..\conf;%LIB_JARS% %MAIN_CLASS%
goto end

:jmx
java -Xms%JVM_XMS% -Xmx%JVM_XMX% -XX:MaxPermSize=%JVM_PERMSIZE% -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -classpath ..\conf;%LIB_JARS% %MAIN_CLASS%

:end
pause