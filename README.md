# boardgames-meetup
Kubernetes, Spring Boot, ReactJS, MongoDB, Zuul

## How to deploy the microservices
* Step 1: Replace the JWT secret and the MongoDB connection URI inside the bg-secret.yml with base64 encoded valid ones.
* Step 2: Run the following commands:
```
minikube start --vm-driver hyperv --hyperv-virtual-switch "Your Minikube Switch"
./initial-setup.bat
minikube ip
```
Now you should be able to access the bg-client by using the provided IP, port 30000.
