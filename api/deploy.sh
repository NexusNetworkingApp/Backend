#!/bin/bash

### Replace the src directory of the deployed application ###

source_dir="./src"  # Path to the source directory you want to copy
destination_dir="../Cloud/backend/src"  # Path to the destination directory to replace

# Copy the source directory to the destination directory, excluding the excluded file
rsync -av --exclude="application.properties" "$source_dir/" "$destination_dir/"

echo "Directory replaced successfully!"

### Deploy ###

#Assuming resource-group and registry have already been established

# Log in to Azure
#az login

# Set the desired directory path
desired_directory="../Cloud/backend"

# Navigate to the desired directory
cd "$desired_directory"

# Build the Docker image
docker compose build

# Log into registry
az acr login --name PeptiCloudServerRegistry

# Push the Docker image
docker compose push

# Delete the existing container instance
az container delete --resource-group PeptiCloud-Server-Group --name pepticloud-server-container --yes

# Deploy the container to Azure (replace with your specific commands)
az container create --resource-group PeptiCloud-Server-Group --name pepticloud-server-container \
  --image pepticloudserverregistry.azurecr.io/server --registry-login-server pepticloudserverregistry.azurecr.io \
  --registry-username PeptiCloudServerRegistry --registry-password mLYnFFdhV2mrEj4PNiXTPOm3AVhZvwkmKWi5p2TqQ1+ACRA+9WYS \
  --dns-name-label pepticloud-server --ports 8080 --ip-address Public