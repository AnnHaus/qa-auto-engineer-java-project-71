.PHONY: build

setup:
	./gradlew wrapper --gradle-version 9.3.0

clean:
	./gradlew clean

build:
	./gradlew clean build

run-dist:
	./build/install/app/bin/app

test:
	./gradlew test

report:
	./gradlew jacocoTestReport
