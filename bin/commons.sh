## POD_NAME=$(oc get pods -l component=application-server,application=third | grep -v NAME | awk '{print $1}')
POD_NAME=$(oc get pods -l component=${1},application=${2} | grep -v NAME | awk '{print $1}')
if [ $# == 3 ]; then
   HOST=$(oc get routes -l component=${1},application=${2} -o jsonpath="{.items[0].spec.host}")
fi
