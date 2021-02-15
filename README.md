# Spring application used in the kubernetes workshop
1. Created a spring boot application with initializr plugin. Created as a Gradle project.
2. Used gradlew build to build it.
    - gradlew build from the project root directoRy
3. Created a docker image based on the openjdk8 image.
Added the following steps on top of it to create a custom image   
    - copied the application build created in the step2 into the container
    - run it using java -jar
4. Build the image as dockerhubusername/imagename:<tag or version>
   - docker build -t nareshdoniparti/kubernetes-with-spring:v1
5. push the image to the docker hub
   - docker push <image name>

kubectl apply -f <yaml file>
kubectl logs --follow <pod name>
kubectl exec <pod name> <linux command>
kubectl delete -f <yaml file>
kubectl get pods
kubectl get deployments

- Kubernetes cluster works based on the master-slave concept. You can create a cluster with multiple machines, 
where at least machine has to be a master, others can act as nodes
- kubernetes master is the brain behind the framework which stores the metadata, delegate the work to the nodes/workers, 
  managing the network in the cluster, etc.,
POD
- Application deployed as a POD cannot be recovered, if the POD goes down for any reason
DEPLOYMENT  
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

  
