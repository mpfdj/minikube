# A simple REST API using openjdk 8
https://github.com/thomaspoignant/hello-world-rest-json





kubectl get pods --all-namespaces
kubectl get pods -A
minikube dashboard

container > pod > namespace


# Add aliases to .bashrc to use minikube commands in WSL

vim ~/.bashrc
alias curl="cmd.exe /c curl"
#alias kubectl="cmd.exe /c kubectl"
alias kubectl="'/mnt/c/Program Files/Docker/Docker/resources/bin/kubectl.exe'"
alias minikube="'/mnt/c/Users/TO11RC/OneDrive - ING/miel/minikube/minikube.exe'"

which kubectl.exe
which minikube.exe



# There is one easy and effective way to push your local Docker image directly to minikube, which will save time from building the images in minikube again.

minikube image load <image name>
minikube image load registry/keycloak:latest
minikube image ls


# https://stackoverflow.com/questions/40686151/kubernetes-pod-gets-recreated-when-deleted
kubectl delete -n default deployment hello-keycloak
kubectl delete pod hello-keycloak-5b648bdd46-9ftns --grace-period=0 --force
kubectl delete -n default --all


kubectl create deployment hello-keycloak --image=registry/keycloak:latest


kubectl create deployment hello-keycloak --image=registry/keycloak:latest --image-pull-policy=Never

# Pushing directly to the in-cluster Docker daemon (docker-env)
# https://stackoverflow.com/questions/42564058/how-to-use-local-docker-images-with-minikube
# https://minikube.sigs.k8s.io/docs/handbook/pushing/#1-pushing-directly-to-the-in-cluster-docker-daemon-docker-env
kubectl run hello-keycloak --image=keycloak:latest --image-pull-policy=Never


# Kubernetes NodePort Custom Port
https://stackoverflow.com/questions/43935502/kubernetes-nodeport-custom-port


https://medium.com/@deepeshtripathi/all-about-kubernetes-port-types-nodeport-targetport-port-containerport-e9f447330b19


# Pushing images - comparing 8 ways to push your image into a minikube cluster.
https://minikube.sigs.k8s.io/docs/handbook/pushing/

    echo $env:MINIKUBE_ACTIVE_DOCKERD
docker ps

# Create an image in the minikube image registry
docker image build -f helloworld.dockerfile -t helloworld .


# Create a deployment in minikube
kubectl apply -f deployment-helloworld.yml
kubectl apply -f deployment-helloworld-api.yml

# Create a service in minikube
kubectl apply -f service-helloworld-api.yml


# Cheat sheet
https://kubernetes.io/docs/reference/kubectl/cheatsheet/


# (Kubernetes Tutorial for Beginners [FULL COURSE in 4 Hours])
- Nana Janashia
- https://www.youtube.com/watch?v=X48VuDVv0do
- https://gitlab.com/nanuchi/youtube-tutorial-series
> 2:38:07 - Persisting Data in K8s with Volumes NOG EENS BEKIJKEN

> 2:58:38 - Deploying Stateful Apps with StatefulSet

- https://www.youtube.com/watch?v=s_o8dwzRlu4 (Kubernetes Crash Course for Absolute Beginners [NEW])


kubectl create deployment nginx-deploy --image=nginx
kubectl edit deployment <DEPLOYMENT NAME>
kubectl get deployment
kubectl get replicaset

# Debugging pods
kubectl get pod
kubectl logs <POD NAME>
kubectl describe pod <POD NAME>
kubectl exec -it <POD NAME> -- /bin/bash  (get an interactive terminal)

kubectl get deployment
kubectl delete deployment <DEPLOYMENT NAME>

kubectl apply -f <CONFIG FILE>

deployment > replicaset > pod > container

kubectl describe service <SERVICE NAME>
kubectl get pod -o wide
kubectl get deployment <DEPLOYMENT NAME> -o yaml > deployment.yml
kubectl delete -f <CONFIG FILE>


# https://gitlab.com/nanuchi/youtube-tutorial-series/-/tree/master/demo-kubernetes-components
# 1:16:16 - Demo Project: MongoDB and MongoExpress

ingress > service > pod
- configmap
- secrets
- ingress (external service)
- srv service
- deployment (pod)

# Demo Project: MongoDB and MongoExpress
kubectl get all
echo -n 'username' | base64
echo -n 'password' | base64

kubectl apply -f mongo-secret.yaml
kubectl get secret
kubectl apply -f mongo.yaml  (The file contains a deployment and an internal service configuration)
kubectl get service
kubectl describe service mongodb-service
kubectl get pod -o wide
kubectl get all | grep mongodb
kubectl apply -f mongo-configmap.yaml
kubectl apply -f mongo-express.yaml

kubectl get pod
kubectl logs <POD NAME>

kubectl get service  (normally a public/external IP address with be assigned)
minikube service mongo-express-service  (opens a browser with the external IP address)

- internal service (type is ClusterIP no need to specify this since this is the default...)
- external service (type is LoadBalancer)


### Organizing your components with K8s Namespaces
kubectl get namespace
kubectl create namespace my-namespace
service objects can be used/accessed from other namespaces
<SERVICE NAME>.<NAMESPACE>

some resources live on cluster level (not on namespace level) for example volume and node
kubectl api-resources --namespaced=false
kubectl api-resources --namespaced=true

kubectl apply -f mysql-configmap.yaml
kubectl get configmap -n default
kubectl get configmap -o wide
kubectl get configmap -o yaml

kubectl apply -f mysql-configmap.yaml --namespace my-namespace
kubectl get configmap -n my-namespace
or specify it in the yaml file in the metadata section (namespace: my-namespace)

# Install kubens
# https://github.com/ahmetb/kubectx#installation
change the default namespace with tool kubens
brew install kubectx
kubens
kubens my-namespace


### K8s Ingress explained
routing rules
ingress controller pod (k8s nginx ingress controller)

browser > proxy/loadbalancer > ingress controller pod > ingress configuration > service > pod

# Install ingress on minikube
minikube addons enable ingress
kubectl get pod -n kube-system


# Create ingress rules for the dashboard
# kubernetes-ingress/dashboard-ingress.yaml
kubectl get ns
kubectl get all -n kubernetes-dashboard
kubectl apply -f dashboard-ingress.yaml
kubectl get ingress -n kubernetes-dashboard

vim /etc/hosts
192.168.49.2 dashboard.com

kubectl describe ingress dashboard-ingress -n kubernetes-dashboard






###  Helm - Package Manager
package manager for kubernetes
helm charts (bundle of yaml files)
helm hub(https://artifacthub.io/)
templating engine



### Persisting Data in K8s with Volumes
- persistent volume (pv)
- persistent volume claim (pvc)
- storage class



# Deploying Stateful Apps with StatefulSet

# K8s Services explained
- ClusterIP
- Headless
- NodePort
- LoadBalancer
kubectl get pod -o wide


# How Prometheus Monitoring works | Prometheus Architecture explained
https://www.youtube.com/watch?v=h4Sl21AKiDg
https://gitlab.com/nanuchi/youtube-tutorial-series