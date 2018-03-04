# Minions for minikube

## Aims

To make an army of minions in minikube.

## Pre-requisites

Docker (https://docs.docker.com/install/) and minikube (https://kubernetes.io/docs/tasks/tools/install-minikube/) installed.

## How to Run

Start minikube:
 
`minikube start --memory 4000 --cpus 3`

Build minion image for minikube - from this directory run

`eval $(minikube docker-env)` <br/>
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

## To Create More Troops

To create more troops of each type do `minikube dashboard` and go to the deployment and change the number of replicas

## One Minion Falls, Another Takes His Place

Choose the host of one the minions open in your browser. To kill it:

`kubectl delete pod <host>`

Refresh the browser (it will take a little while) and see that another pod is already there

## Minion Upgrades

Change the version in the Controller class to 0.2. Do:

`docker build . -t minion:0.2`

Then open minion-army.yml and find-replace all the "latest" with "0.2". Save the changes and do:

`kubectl apply -f minion-army.yml --record`

Refresh the browser of one of the minion types to see the version change in line with what you see from `kubectl rollout status deployment <deployment_name>` where <deployment_name> is a minion type (e.g. one-eyed-minion).

## Minion Rollbacks

To see the history of what was deployed do `kubectl rollout history deployment <deployment_name>` and to rollback do `kubectl rollout undo deployment <deployment_name> --to-revision=1` (can take a little while)

## To Destroy the Army

`kubectl delete -f minion-army.yml`

Stop minikube with `minikube stop`
