#!/bin/bash
# 参数 ：tag版本
TAG=$1
docker build -t 47.114.1.23/hld-k8s/eureka-server:$TAG .
docker push 47.114.1.23/hld-k8s/eureka-server:$TAG