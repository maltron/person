source bin/commons.sh
curl -i -v -X GET -H "Content-type: application/json" -H "Accept: application/json" ${HOST}/api/v1/email/${3}
