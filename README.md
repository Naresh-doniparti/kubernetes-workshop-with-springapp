# Spring application used in the kubernetes workshop
1. Created a spring boot application with initializr plugin. Created as a Gradle project.
2. Used gradlew build to build it.
    * gradlew build from the project root directoRy
3. Created a docker image based on the openjdk8 image.
Added the following steps on top of it to create a custom image   
    * copied the application build created in the step2 into the container
    * run it using java -jar
4. Build the image as dockerhubusername/imagename:<tag or version>
   * docker build -t nareshdoniparti/kubernetes-with-spring:v1
5. push the image to the docker hub
   * docker push <image name>

## Frequently used commands 
`kubectl apply -f <yaml file>`
`kubectl logs --follow <pod name>`
`kubectl exec <pod name> <linux command>`
`kubectl delete -f <yaml file>`
`kubectl get <nodes/pods/deployments/services>`
`kubectl get/describe <type> <name>`

> Installing CURL on openjdk images -  apk add --update && apk --no-cache add curl

- Kubernetes cluster works based on the master-slave concept. You can create a cluster with multiple machines, 
where at least machine has to be a master, others can act as nodes
- kubernetes master is the brain behind the framework which stores the metadata, delegate the work to the nodes/workers, 
  managing the network in the cluster, etc.,
## POD
- Application deployed as a POD cannot be recovered, if the POD goes down for any reason
## DEPLOYMENT  
- Deployment is used for auto recovery. For example, you have created the kind as 'Deployment' with 2 replicas,
- kubernetes runs an infinite loop at the background which continuously check if the 2 replicas of the POD are available
- Rolling out releases- we can use different rollout strategies for quickly rolling out a release in multiple pods.
   - rollingUpdate - Advantages: No downtime. Disadvantages: You will have both the old and new versions running in parallel for a period of time.  
   - Recreate - Advantages: Terminates all the old version pods before creating new version pods. Disadvantages: Downtime expected.
   - blue/green - ? 
- Mitigating bad releases 
     - Rolling back to the previous version manually
         kubectl rollout undo deployment <deployment name>
     - Automatic rollback using readinessProbe and livenessProbe
       readinessProbe - Check which considers the container as ready for accepting requests
       livenessProble - Frequently checks the container availability. Restarts a pod, if it is unhealthy
## SERVICE
- provides an end point to pods
    - ClusterIp(Optional)
      - an endpoint which can only be accessed inside the cluster. creates a virtual IP which can be used by the members in the cluster. 
    - NodePort
      - opens a port on all the nodes. Redirects the requests received on that port to the application PODs behind the service. 
      - Service to POD mapping is done using selector in the yaml. We use labels to identify Pods that we want to map for this service. 
      - This endpoint is exposed to the outside world.
    - LoadBalancer
       - creates a load balancer on the cloud environment where this service is running.
       - In addition to exposing the PODs, it will also add a load balancer which faces the external client and routes the request to the
    application PODs behind it in an evenly distributed manner.
    -ExternalName
        - provides an end point to an external service like DB, other third-party application etc., It avoids hardcoding the endpoints of external URLs 
- service discovery can be done with service name via TCP. While creating the service, a service name entry gets created into kubernetes DNS. we can 
access the service with http://<service name>:<application port>ig
  
## INGRESS
https://kubernetes.github.io/ingress-nginx/deploy/#bare-metal
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v0.44.0/deploy/static/provider/baremetal/deploy.yaml
kubectl get pods -n ingress-nginx -l app.kubernetes.io/name=ingress-nginx --watch
- Ingress sits before your services and routes the requests to services based on the rules defined in the ingress file. 
- It kind of acts as a load balancer + router (routing requests to appropriate services based on rules). Rules can be based on the 
http request path or  host name
- Ingress type can't be used directly. You need to install a Controller implementation to make the Ingress work. We are using Nginx Controller here.

## CONFIG MAP
- It is used to pass environment variables to the container. 
- ConfigMap is a type in kubernetes which is used to store key value pairs. 
- You can also mount the configMap as files in the container. 

#SECRET
- work in the same way as config maps. But, they will not be persisted. They will be stored encrypted in the master node. 
- All the features that config map supports is supported by secret except the fact that the secret is stored in a secured manner.

#JOB
- Similar to deployment, used for running a specific task.
- Container will be stopped once the task execution is completed, and the status of the container will be set as COMPLETED.
- Job will also be marked as successful completion of tasks.
- Other features
    - Can run multiple tasks serially/parallel
    - can restart the job if the container fails for some reason
    - Ability to run scheduled jobs using CronJob resource

