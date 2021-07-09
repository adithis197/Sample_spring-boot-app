# Kubernetes Redis Cluster

 Deploy Redis cluster in Kubernetes

## Install


Redis Cluster requires at least 3 master nodes, so we have 6 replicas here ( 3 master / 3 slave )

```bash
kubectl apply -f statefulset.yml
kubectl apply -f service.yml
```

### Get cluster nodes

```bash
kubectl get pods -l app=redis-cluster 
```

Now we are going to create the cluster ( assign master/slave roles, distribute slot maps) using the `redis-cli --cluster create` script.  

Since we are going create 3 masters cluster with 3 dedicated slaves, the `--cluster-replicas 1` flag is passed.

```bash
kubectl exec -it redis-cluster-0 -- redis-cli --cluster create --cluster-replicas 1 <<< node list from previous command >>>
```

---
