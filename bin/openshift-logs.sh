if [ "$#" == 0 ] || [ "$#" -gt 1 ]; then
   echo "usage:   openshift-logs.sh <component>"
   echo "example: openshift.logs.sh database"
   exit 1
fi
oc logs --follow $(oc get pods --selector="component=${1}" -o jsonpath="{..metadata.name}")
