#!/bin/bash

H_CONTENT_TYPE=content-type:application/json;charset=UTF-8
BODY='{"code":0,"content":"TEST"}'
#HOST=lit-springs-63926.herokuapp.com
HOST=127.0.0.1

echo ${BODY}

curl -v -H ${H_CONTENT_TYPE}  --data ${BODY} -X POST http://${HOST}:8080/hello
