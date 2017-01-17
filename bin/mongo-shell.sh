echo "WARNING: Ensure the port-foward is running"
MONGODB_USER=$(oc get dc/microservice-data -o jsonpath='{$..env[?(@.name=="MONGODB_USER")].value}')
MONGODB_PASSWORD=$(oc get dc/microservice-data -o jsonpath='{$..env[?(@.name=="MONGODB_PASSWORD")].value}')
MONGODB_DATABASE=$(oc get dc/microservice-data -o jsonpath='{$..env[?(@.name=="MONGODB_DATABASE")].value}')
mongo localhost:27017/${MONGODB_DATABASE} --username ${MONGODB_USER} --password ${MONGODB_PASSWORD} ${1}
