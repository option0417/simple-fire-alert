#!/bin/bash

H_CONTENT_TYPE=content-type:application/json;charset=UTF-8
BODY='{"code":0,"content":"TEST"}'
HOST_HEROKU=lit-springs-63926.herokuapp.com
HOST_LOCAL=127.0.0.1

# Send curl request to target-host
function sendToLocal() {
  curl -v -H ${H_CONTENT_TYPE}  --data ${BODY} -X POST http://${TARGET_HOST}:8080/hello
}


function sendToHeroku() {
  curl -v -H ${H_CONTENT_TYPE}  --data ${BODY} -X POST https://${TARGET_HOST}/hello
}

OPTION=$1
case $1 in
  1)
    TARGET_HOST=${HOST_LOCAL}
    echo "Send to Localhost ${HOST_LOCAL}"
    sendToLocal;
  ;;

  2)
    TARGET_HOST=${HOST_HEROKU}
    echo "Send to Heroku ${HOST_HEROKU}"
    sendToHeroku;
  ;;

  *)
    echo "Usage $0 1(Localhost), 2(Hekoru)"
  ;;
esac

