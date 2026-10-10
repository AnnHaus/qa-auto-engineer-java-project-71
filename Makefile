.PHONY: build

setup:
	./gradlew wrapper --gradle-version 9.3.0

clean:
	./gradlew clean

build:
	./gradlew clean build

run-dist:
	./build/install/java-project-71/bin/java-project-71

test:
	./gradlew test

report:
	./gradlew jacocoTestReport
