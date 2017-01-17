if [ "$#" == 0 ] || [ "$#" -gt 2 ]; then
   echo "usage:   openshift-rsh.sh <component> <application's name>"
   echo "example: openshift.rsh.sh database second"
   exit 1
fi
oc rsh $(oc get pods --selector="component=${1},application=${2}" -o jsonpath="{..metadata.name}")
