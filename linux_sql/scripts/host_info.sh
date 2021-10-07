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

# Host info data: id,hostname,cpu_number,cpu_architecture,cpu_model,cpu_mhz,l2_cache,total_mem,timestamp_info
# Gather data
lscpu_out=`lscpu`
hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=cpu_architecture=$(echo "$lscpu_out" | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | egrep "^Model name:" | awk --field-separator ':' '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep "^CPU MHz:" | awk --field-separator ':' '{print $2}' | xargs)
l2_cache=$(echo "$lscpu_out" | egrep "^L2 cache:" | awk --field-separator ':' '{print $2}' | xargs)
total_mem=$(cat /proc/meminfo | egrep "MemTotal:" | awk --field-separator ':' '{print $2}' | xargs)
timestamp_info=$(date -u +"%Y-%m-%d %H:%M:%S") #current timestamp in `2019-11-26 14:40:19` format

# Prepare insert statement, connect to database and insert
insert_stmt="INSERT INTO host_info (hostname,cpu_number,cpu_architecture,cpu_model,cpu_mhz,l2_cache,total_mem,timestamp_info) VALUES ('$hostname',$cpu_number,'$cpu_architecture','$cpu_model',$cpu_mhz,'$l2_cache','$total_mem','$timestamp_info');"
psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "$insert_stmt"
exit $?
