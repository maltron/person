echo "WARNING: Ensure the port-foward is running"
if [ "$#" == 0 ]; then
   echo "usage:   mongo-shell.sh <component> <application's name> <scripting file>"
   echo "example: mongo-shell.sh database second"
   echo 
   echo "example: if you need to run a script file named example.js" 
   echo "example: mongo-shell.sh database second example.js"
   exit 1
fi
RESOURCE_NAME=$(oc get deploymentconfig --selector="component=${1},application=${2}" --output jsonpath="{$..items[0].metadata.name}")
MONGODB_USER=$(oc get dc/${RESOURCE_NAME} -o jsonpath='{$..env[?(@.name=="MONGODB_USER")].value}')
MONGODB_PASSWORD=$(oc get dc/${RESOURCE_NAME} -o jsonpath='{$..env[?(@.name=="MONGODB_PASSWORD")].value}')
MONGODB_DATABASE=$(oc get dc/${RESOURCE_NAME} -o jsonpath='{$..env[?(@.name=="MONGODB_DATABASE")].value}')
mongo localhost:27017/${MONGODB_DATABASE} --username ${MONGODB_USER} --password ${MONGODB_PASSWORD} ${3}
