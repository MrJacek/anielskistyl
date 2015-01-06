#!/bin/bash
mvn clean install
java -jar ./target/allegro-provider-1.0-SNAPSHOT.jar
