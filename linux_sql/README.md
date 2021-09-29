# Linux Cluster Monitoring Agent
This project is under development. Since this project follows the GitFlow, the final work will be merged to the master branch after Team Code Team.

# Introduction
The purpose of this project is to design a basic program for recording hardware specifications of nodes in a Linux cluster and monitoring their resource usages in real time and inserting into a database. This is implemented using a ` psql` instance and bash agents on each node that run two scripts for collecting hardware info and monitoring resource usage with the use of `crontab`.

## Architecture
![my image](./assets/cluster_diagram.png)

## Scripts
* `host_info.sh` - This script collects hardware information and inserts into the database. The following data is captured:
  * The host name 
  * Number of CPUs 
  * CPU architecture 
  * The CPU model 
  * CPU frequency in MHz 
  * L2 cache in kB 
  * Total system memory in kB 
  * Timestamp at the moment of data collection
  

* `host_usage.sh` - This script collects resource usage information every minute with the use of `crontab` and inserts into the database. The following data is captured:
  * Timestamp at the moment of data collection
  * Free memory in MB
  * Percentage of CPU time spent idle
  * Percentage of CPU that is spent running kernel code
  * Number of disk I/O operations
  * Available disk space at the root directory in MB

## Database Modeling

# Test

# Deployment

# Improvements