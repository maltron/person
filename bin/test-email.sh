if [ "$#" == 0 ] || [ "$#" -gt 3 ]; then
   echo "usage:   test-email.sh <component> <application's name> <value>"
   echo "example: test-email.sh application-server first maltron@gmail.com"
   exit 1
fi
curl -i -v -X GET -H "Content-type: application/json" -H "Accept: application/json" $(oc get routes -l component=${1},application=${2} -o jsonpath="{.items[0].spec.host}")/api/v1/email/${3}
