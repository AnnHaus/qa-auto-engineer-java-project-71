.PHONY: build

setup:
	cd app && ./gradlew wrapper --gradle-version 9.3.0

clean:
	cd app && ./gradlew clean

build:
	cd app && ./gradlew clean build

run-dist:
	cd app && ./build/install/app/bin/app

test:
	cd app && ./gradlew test

report:
	cd app && ./gradlew jacocoTestReport
