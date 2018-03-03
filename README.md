# Minions for minikube

## Aims

To make an army of minions in minikube.

## Pre-requisites

Docker (https://docs.docker.com/install/) and minikube (https://kubernetes.io/docs/tasks/tools/install-minikube/) installed.

## How to run

Start minikube:
 
`minikube start --memory 4000 --cpus 3`

Build minion image for minikube - from this directory run

`eval $(minikube docker-env)`
`docker build . -t minion`

Deploy the army
 
`kubectl create -f minion-army.yml`

To see the troop types: 

`open http://$(minikube ip):30080` <br/>
`open http://$(minikube ip):30081` <br/>
`open http://$(minikube ip):30082` <br/>
`open http://$(minikube ip):30083` <br/>

To see the whole army 

`kubectl get pods`

To create more troops of each type do `minikube dashboard` and go to the service and change the number of replicas

To destroy the army 

`kubectl delete -f minion-army.yml`

Stop minikube with `minikube stop`
