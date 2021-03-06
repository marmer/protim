#!/bin/bash

set -e

echo
echo --- Building ---
echo
mvn clean install -DskipTests -Dmaven.javadoc.skip -Dmaven.source.skip -Dassembly.skipAssembly -B -V -U -T 1.5C

echo
echo --- Testing ---
echo
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent verify -V -T 1.5C

echo
echo --- Reporting ---
echo
cp /tmp/nodebin/node/node /bin/
mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dmaven.javadoc.skip -Dmaven.source.skip -Dassembly.skipAssembly -V -Dsonar.login=$SONAR_CLOUD_TOKEN -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=marmer-github -V -T 1.5C
