source bin/commons.sh
oc rsync --watch --no-perms=false --progress=false target/person-2.0/ ${POD_NAME}:/opt/java/server/as/wildfly/standalone/deployments-exploded/ROOT.war
