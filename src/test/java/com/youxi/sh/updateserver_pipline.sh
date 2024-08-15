o#!/bin/sh
cd `dirname $0`
#检测日志关键字 $1传递日志目录 $2传递关键字
check_wait_log () {
tail -f -n0 $1 | while read line;
do
        echo $line
        #关键字出现后退出日志打印
        echo "$line" | egrep "$2" >/dev/null 2>&1
        if [ $? = 0 ];then
                break
        fi

        #退出tail命令
        ps -aux | grep "tail -n" | grep -v "grep" | awk '{print $2}' | while read line;
        do
                kill -9 $line
        done
done
}


stop_server() {
    echo "stop server begin............."

    PID=`ps ux -u \`whoami\` |grep java |grep GameServer| awk '{print $2}'`
    cd ~/ServerBin/GameServer
    sh stop.sh
    sh stop_global.sh
    while [ -n "$PID" ]
    do
       sleep 3
       PID=`ps ux -u \`whoami\` |grep java |grep GameServer| awk '{print $2}'`
    done
    sleep 5
    echo "stop server end ......"
}

install_and_start(){
    echo "exec install_and_start begin......"
    DD=`date "+%Y-%m-%d"`
    cd ~/ServerBin/
    svn up
    sh gen_conf.sh ~/conf.m4
    cd ~/ServerBin/GameServer
    export JAVA_HOME=/usr/local/java/jdk1.8.0_202
    sh start.sh
    echo "waiting for game server start ......"
    #sleep 30
    #cat ~/ServerBin/GameServer/logs/main*.log

    #cat ~/ServerBin/GameServer/logs/main/main*$DD |tail -n 100 |grep "_server started"
    #tail -f ~/ServerBin/GameServer/logs/game/main/main-101.log | sed '/&&&&&&&&&&&&&|shutdownhook/q'
    check_wait_log ~/ServerBin/GameServer/logs/game/main/main-201.log "&&&&&&&&|6666666"
    echo "start game server end .............."

    sh start_global.sh
    echo "waiting for global server start......"
    #sleep 30
    #cat ~/ServerBin/GameServer/logs/main*.log

    #cat ~/ServerBin/GameServer/logs/main/main*$DD |tail -n 100 |grep "_server started"
    #tail -f ~/ServerBin/GameServer/logs/game/main/main-99#90#51.log | sed '/&&&&&&&&&&&&&|shutdownhook/q'
    check_wait_log ~/ServerBin/GameServer/logs/game/main/main-99#90#51.log "&&&&&&&&|6666666"
    echo "start global server end .............."
}

clean_db(){
    echo "clean_mysql......"
    server_id=$2
    db_name=$(mysql -uroot -p123456 -N -e "show databases like 'SECONDWAR_${server_id}'")

    mysql -uroot -p123456 -e "drop database ${db_name}"

    echo "clean_mysql_global......"
    db_name=$(mysql -uroot -p123456 -N -e "show databases like 'SECONDWAR_GLOBAL'")

    mysql -uroot -p123456 -e "drop database ${db_name}"

    echo "clean_redis......"
    redis-cli -h 127.0.0.1 -p 6379 -a '123456' flushdb
}



case "$1" in
  clean_db)
    clean_db "$@"
    ;;
  start)
    install_and_start "$@"
    ;;
  stop)
    stop_server "$@"
    ;;
  *)
    echo "Usage: $0 {start|stop|clean_db}"
    exit 1
    ;;
esac
