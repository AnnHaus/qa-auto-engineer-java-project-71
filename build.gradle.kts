plugins {
    java
    application
    id("com.github.ben-manes.versions") version "0.52.0"
    id("com.diffplug.spotless") version "6.25.0"
    jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    dependencies {
        implementation("info.picocli:picocli:4.7.6")
        annotationProcessor("info.picocli:picocli-codegen:4.7.6")
        implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
        implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.2")
        testImplementation(platform("org.junit:junit-bom:5.10.2"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testImplementation("org.assertj:assertj-core:3.25.3")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }


}

tasks.test {
    useJUnitPlatform()
    outputs.upToDateWhen { false }
}
spotless {
    java {
        googleJavaFormat()
    }
}
jacoco {
    toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
    }
}
tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.jacocoTestReport)
    violationRules {
        rule {
            element = "CLASS"
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.55".toBigDecimal()
            }
            excludes = listOf("hexlet.code.App")
        }
    }
}
tasks.build {
    dependsOn(tasks.spotlessCheck)
    finalizedBy(tasks.jacocoTestCoverageVerification)
}

application {
    mainClass.set("hexlet.code.App")
}

