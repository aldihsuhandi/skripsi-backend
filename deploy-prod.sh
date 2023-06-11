# !/bin/bash

docker build -t shumishumi-image .
docker tag shumishumi-image gcr.io/moonlit-helper-388513/shumishumi-image
docker push gcr.io/moonlit-helper-388513/shumishumi-image
gcloud app deploy
