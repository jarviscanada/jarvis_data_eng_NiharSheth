# Linux Cluster Monitoring Agent
This project is under development. Since this project follows the GitFlow, the final work will be merged to the master branch after Team Code Team.

# Introduction
The purpose of this project is to design a basic program for recording hardware specifications of nodes in a Linux cluster and monitoring their resource usages in real time and inserting into a database. This is implemented using a ` psql` instance and bash agents on each node that run two scripts for collecting hardware info and monitoring resource usage with the use of `crontab`.

## Architecture
![my image](./assets/cluster_diagram.png)

## Scripts
* `host_info.sh` - This script collects hardware information and inserts into the database
* `host_usage.sh` - This script collects resource usage information every minute with the use of `crontab` and inserts into the database

## Database Modeling

# Test

# Deployment

# Improvements