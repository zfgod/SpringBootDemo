#!/bin/bash
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf
CONF_FILE="$CONF_DIR/config.properties"

JVM_XMX_DEF=64m
JVM_XMS_DEF=64m
JVM_XMN_DEF=16m
JVM_PERMSIZE_DEF=8m
JVM_PAGESIZE_DEF=16m
JVM_XSS_DEF=256k

# configuration for jvm
JVM_XMX=`sed '/jvm.xmx/!d;s/.*=//' $CONF_FILE | tr -d '\r'`
if [ ! $JVM_XMX ]; then JVM_XMX=$JVM_XMX_DEF 
fi
JVM_XMS=`sed '/jvm.xms/!d;s/.*=//' $CONF_FILE | tr -d '\r'`
if [ ! $JVM_XMS ]; then JVM_XMS=$JVM_XMS_DEF 
fi
JVM_XMN=`sed '/jvm.xmn/!d;s/.*=//' $CONF_FILE | tr -d '\r'`
if [ ! $JVM_XMN ]; then JVM_XMN=$JVM_XMN_DEF 
fi
JVM_PERMSIZE=`sed '/jvm.permsize/!d;s/.*=//' $CONF_FILE | tr -d '\r'`
if [ ! $JVM_PERMSIZE ]; then JVM_PERMSIZE=$JVM_PERMSIZE_DEF 
fi
JVM_PAGESIZE=`sed '/jvm.pagesize/!d;s/.*=//' $CONF_FILE | tr -d '\r'`
if [ ! $JVM_PAGESIZE ]; then JVM_PAGESIZE=$JVM_PAGESIZE_DEF 
fi
JVM_XSS=`sed '/jvm.xss/!d;s/.*=//' $CONF_FILE | tr -d '\r'`
if [ ! $JVM_XSS ]; then JVM_XSS=$JVM_XSS_DEF 
fi

echo "configurations for jvm:JVM_XMX=$JVM_XMX, JVM_XMS=$JVM_XMS, JVM_XMN=$JVM_XMN, JVM_PERMSIZE=$JVM_PERMSIZE, JVM_XSS=$JVM_XSS, JVM_PAGESIZE=$JVM_PAGESIZE"

# get properties from config/config.properties
SERVER_NAME=`sed '/application.name/!d;s/.*=//' $CONF_FILE | tr -d '\r'`
SERVER_PROTOCOL=`sed '/protocol.name/!d;s/.*=//' $CONF_FILE | tr -d '\r'`
SERVER_PORT=`sed '/hsf.protocol.port/!d;s/.*=//' $CONF_FILE | tr -d '\r'`
LOGS_FILE=`sed '/logfile_path/!d;s/.*=//' $CONF_FILE | tr -d '\r'`
MAIN_CLASS=`sed '/mail.class/!d;s/.*=//' $CONF_FILE | tr -d '\r'`

if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME=`hostname`
fi

PIDS=`ps aux | grep java | grep "$CONF_DIR" |awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME has been started! PID: $PIDS"
    exit 1
fi

if [ -n "$SERVER_PORT" ]; then
    SERVER_PORT_COUNT=`netstat -tln | grep $SERVER_PORT | wc -l`
    if [ $SERVER_PORT_COUNT -gt 0 ]; then
        echo "ERROR: The $SERVER_NAME port $SERVER_PORT has been used!"
        exit 1
    fi
fi

LOGS_DIR=""
if [ -n "$LOGS_FILE" ]; then
    LOGS_DIR=$LOGS_FILE
else
    LOGS_DIR=$DEPLOY_DIR/logs
fi
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi
STDOUT_FILE=/dev/null

LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

PAY1PAYLIB_DIR=$DEPLOY_DIR/pay1paylib
PAY1PAYLIB_JARS=`ls $PAY1PAYLIB_DIR|grep .jar|awk '{print "'$PAY1PAYLIB_DIR'/"$0}'|tr "\n" ":"`

JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8  -DappName=$SERVER_NAME"
JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi
JAVA_JMX_OPTS=""
if [ "$1" = "jmx" ]; then
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi
JAVA_MEM_OPTS=""
BITS=`java -version 2>&1 | grep -i 64-bit`
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -server -Xmx$JVM_XMX -Xms$JVM_XMS -Xmn$JVM_XMN -XX:PermSize=$JVM_PERMSIZE -Xss$JVM_XSS -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=$JVM_PAGESIZE -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
else
    JAVA_MEM_OPTS=" -server -Xmx$JVM_XMX -Xms$JVM_XMS -XX:PermSize=$JVM_PERMSIZE -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi
JAVA_GC_OPTS=""
JAVA_GC_OPTS="-Xloggc:$LOGS_DIR/gc.log -XX:+PrintGCDetails"
echo -e "Starting the $SERVER_NAME ...\c"
nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS $JAVA_GC_OPTS -classpath $CONF_DIR:$LIB_JARS:$PAY1PAYLIB_JARS $MAIN_CLASS >$STDOUT_FILE 2>&1 &

COUNT=0
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1 
    PIDS=`ps aux | grep java | grep "$CONF_DIR" |awk '{print $2}'`
    if [ -n "$PIDS" ]; then
        break
    fi
done

PIDS=`ps aux | grep java | grep "$CONF_DIR" | awk '{print $2}'`
echo -e "\nOK, PID: $PIDS, $SERVER_NAME started!"