DEFAULT_COMPONENT=database
DEFAULT_PORT=27017
oc port-forward $(oc get pods --selector="component=${1:-$DEFAULT_COMPONENT}" --output jsonpath='{$..metadata.name}') ${2:-$DEFAULT_PORT}:${2:-$DEFAULT_PORT}
