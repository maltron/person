if [ "$#" == 0 ] || [ "$#" -gt 3 ]; then
   echo "usage:   openshift-portforward.sh <component> <appication's name> <port>"
   echo "example: openshift-portforward.sh database second 27017"
   exit 1
fi
DEFAULT_COMPONENT=database
DEFAULT_PORT=27017
oc port-forward $(oc get pods --selector="component=${1:-$DEFAULT_COMPONENT},application=${2}" --output jsonpath='{$..metadata.name}') ${3:-$DEFAULT_PORT}:${3:-$DEFAULT_PORT}
