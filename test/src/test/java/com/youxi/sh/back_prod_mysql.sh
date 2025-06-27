#!/bin/bash

# 检查是否传入参数
if [ -z "$1" ]; then
    echo "使用方法: $0 <serverId>"
    exit 1
fi

# 获取传入的参数
SERVER_ID=$1

# 定义变量
BACKUP_DIR="/home/public/prod_mysql"
BACKUP_FILE="$BACKUP_DIR/zrxy_prod_${SERVER_ID}.sql"
LOG_FILE="$BACKUP_DIR/backup_restore_${SERVER_ID}.log"
TEMP_FILE="$BACKUP_DIR/temp_zrxy_prod_${SERVER_ID}.sql"
DATABASE_NAME="zrxy_prod_${SERVER_ID}"

# 打印调试信息
echo "Server ID: $SERVER_ID" | tee -a $LOG_FILE
echo "Backup file: $BACKUP_FILE" | tee -a $LOG_FILE
echo "Database name: $DATABASE_NAME" | tee -a $LOG_FILE

# 清空日志文件
> $LOG_FILE

# 删除本地之前备份的sql文件
echo "删除之前的备份文件..." | tee -a $LOG_FILE
if [ -f "$BACKUP_FILE" ]; then
    rm -f "$BACKUP_FILE"
    echo "已删除 $BACKUP_FILE" | tee -a $LOG_FILE
else
    echo "$BACKUP_FILE 文件不存在，无需删除" | tee -a $LOG_FILE
fi

# 连接A库并备份
echo "开始备份A库..." | tee -a $LOG_FILE
mysqldump --login-path=xianshang $DATABASE_NAME > $BACKUP_FILE
if [ $? -eq 0 ]; then
    echo "A库备份成功，保存到 $BACKUP_FILE" | tee -a $LOG_FILE
else
    echo "A库备份失败" | tee -a $LOG_FILE
    exit 1
fi

# 删除备份文件中的 GTID 设置
echo "删除备份文件中的 GTID 设置..." | tee -a $LOG_FILE
sed '/^-- GTID state at/d;/^SET @@GLOBAL.GTID_PURGED/d' $BACKUP_FILE > $TEMP_FILE

# 连接B库，删除已存在的数据库
echo "删除B库中的现有数据库..." | tee -a $LOG_FILE
mysql --login-path=105neiwang -e "DROP DATABASE IF EXISTS $DATABASE_NAME"
if [ $? -eq 0 ]; then
    echo "已删除B库中的数据库 $DATABASE_NAME" | tee -a $LOG_FILE
else
    echo "删除B库中的数据库失败" | tee -a $LOG_FILE
    exit 1
fi

# 创建新的数据库
echo "创建新的数据库 $DATABASE_NAME..." | tee -a $LOG_FILE
mysql --login-path=105neiwang -e "CREATE DATABASE $DATABASE_NAME"
if [ $? -eq 0 ]; then
    echo "已创建新的数据库 $DATABASE_NAME" | tee -a $LOG_FILE
else
    echo "创建新的数据库失败" | tee -a $LOG_FILE
    exit 1
fi

# 将备份导入到B库
echo "将备份导入到B库..." | tee -a $LOG_FILE
mysql --login-path=105neiwang $DATABASE_NAME < $TEMP_FILE
if [ $? -eq 0 ]; then
    echo "备份已成功导入到B库 $DATABASE_NAME" | tee -a $LOG_FILE
    rm -f $TEMP_FILE
else
    echo "备份导入B库失败" | tee -a $LOG_FILE
    exit 1
fi

echo "备份和恢复过程完成" | tee -a $LOG_FILE

