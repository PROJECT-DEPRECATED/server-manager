plugins {
    kotlin("jvm") version "1.4.21"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    `maven-publish`
}

group = properties["group"]!!
version = properties["version"]!!

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("com.destroystokyo.paper:paper-api:1.12.2-R0.1-SNAPSHOT")
}

val shade = configurations.create("shade")
shade.extendsFrom(configurations.implementation.get())

tasks {
    javadoc {
        options.encoding = "UTF-8"
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    create<Jar>("sourceJar") {
        archiveClassifier.set("source")
        from(sourceSets["main"].allSource)
    }

    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
        }
    }

    jar {
        from ( shade.map { if (it.isDirectory) it else zipTree(it) } )
    }
}

publishing {
    publications {
        create<MavenPublication>("Greeting_Plugin") {
            artifact(tasks["sourceJar"])
            from(components["java"])
        }
    }
}