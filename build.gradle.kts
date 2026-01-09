import org.jetbrains.kotlin.gradle.dsl.JvmTarget

    plugins {
      java
      kotlin("jvm") version "2.2.0"
      id("org.jetbrains.intellij.platform") version "2.10.5"
    }

group = "com.ofya"
version = "2026-01-09"

repositories {
  mavenCentral()

  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdea("2025.3")
    bundledPlugin("com.intellij.java")
  }
}


java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
  }
}

tasks {
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
  }

  patchPluginXml {
    sinceBuild.set("253")
    untilBuild.set("253.*")
  }

  signPlugin {
    certificateChainFile.set(file("../keys/chain.crt"))
    privateKeyFile.set(file("../keys/private.pem"))
    password.set("nope")
  }

  publishPlugin {
    token.set("nope")
  }
}
