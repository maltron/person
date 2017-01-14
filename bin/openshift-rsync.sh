POD_NAME=$(oc get pods -l component=application-server,application=third | grep -v NAME | awk '{print $1}')
oc rsync --watch --no-perms=false --progress=false target/person-2.0/ ${POD_NAME}:/opt/java/server/as/wildfly/standalone/deployments-exploded/ROOT.war
