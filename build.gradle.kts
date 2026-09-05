plugins {
    id("java-library")
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks {
    compileJava {
        options.compilerArgs.add("-Xlint:-deprecation")
    }

    processResources {
        val props = mapOf("version" to version)
        filesMatching("plugin.yml") {
            expand(props)
        }
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    jar {
        from(sourceSets.main.get().resources)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveFileName.set("Greenname.jar")
    }
}
