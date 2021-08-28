plugins {
    kotlin("jvm") version "1.5.30"
    id("com.github.johnrengelman.shadow") version "7.0.0"

    `maven-publish`
}

group = properties["group"]!!
version = properties["version"]!!

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("net.kyori:adventure-api:4.7.0")
    implementation("org.xerial:sqlite-jdbc:3.36.0.2")
    implementation("net.projecttl:InventoryGUI-api:4.1.2")
    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
}

tasks {
    javadoc {
        options.encoding = "UTF-8"
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "16"
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

    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveVersion.set("")
        archiveClassifier.set("")
    }
}