# Redis Sentinel Based HA cluster in Kubernetes

This repository contains code for deploying HA enabled Redis in a kubernetes cluster.

This can easily be used to deploy into a large scale kubernetes cluster.

## How To Install

- Create directory /mnt/redis-data

		$ mkdir /mnt/redis-data

- Change to directory redis-failover

		$ cd this-folder

- Execute command create.sh

		$ ./create.sh
		$ ./apply.sh
		$ kubectl exec -it redis-cluster-master-0 sh
		# redis-cli



