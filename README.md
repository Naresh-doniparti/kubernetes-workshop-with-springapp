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


  
