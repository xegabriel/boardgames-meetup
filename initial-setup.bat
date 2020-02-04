@echo off
kubectl config use-context minikube
kubectl create namespace bg-dev
kubectl config set-context --current --namespace=bg-dev
kubectl apply -f bg-authentication\k8s-deployment
kubectl apply -f bg-client\k8s-deployment
kubectl apply -f bg-core\k8s-deployment
kubectl apply -f bg-dashboard\k8s-deployment
kubectl apply -f bg-master\k8s-deployment
kubectl apply -f bg-secret.yml
@echo on
