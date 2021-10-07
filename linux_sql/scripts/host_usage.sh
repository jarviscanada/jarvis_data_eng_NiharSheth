#!/bin/bash

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Check arguments
if [ $# -ne 5 ]; then
  echo "Incorrect number of arguments. Usage: ./scripts/host_info.sh psql_host psql_port psql_port db_name psql_user psql_password"
  exit 1
fi
export PGPASSWORD=$psql_password

# Host usage data: timestamp,host_id,memory_free,cpu_idle,cpu_kernel,disk_io,disk_available
# Gather data

timestamp_usage=$(date -u +"%Y-%m-%d %H:%M:%S") #current timestamp in `2019-11-26 14:40:19` format
hostname=$(hostname -f)
host_id=$(psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "SELECT id FROM host_info WHERE hostname='$hostname';" | awk '{if (NR==3) print $1}')
memory_free=$(cat /proc/meminfo | egrep "MemFree:" | awk --field-separator ':' '{print $2}' | xargs)
cpu_idle=$(vmstat | awk '{if(NR==3) print $15}')
cpu_kernel=$(vmstat | awk '{if(NR==3) print $14}')
disk_io=$(vmstat -d | awk '{if(NR==3) print $10}')
disk_available=$(df -BM / | egrep "^/" | awk '{print $4}')

# Prepare insert statement, connect to database and insert
insert_stmt="INSERT INTO host_usage (timestamp_usage,host_id,memory_free,cpu_idle,cpu_kernel,disk_io,disk_available) VALUES ('$timestamp_usage',$host_id,'$memory_free',$cpu_idle,$cpu_kernel,$disk_io,'$disk_available');"
psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "$insert_stmt"
exit $?