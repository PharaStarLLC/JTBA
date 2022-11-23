import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin
import org.cadixdev.gradle.licenser.LicenseExtension
import org.cadixdev.gradle.licenser.Licenser

fun getCommitHash(): String = try {
    val runtime = Runtime.getRuntime()
    val process = runtime.exec("git rev-parse --short HEAD")
    val out = process.inputStream
    out.bufferedReader().readText().trim()
} catch (ignored: Exception) {
    "unknown"
}

val hash: String = getCommitHash()

plugins {
    id("java")
    id("java-library")
    id("maven-publish")
    id("signing")

    alias(libs.plugins.shadow)
    alias(libs.plugins.licenser)
    alias(libs.plugins.lombok)

    eclipse
    idea
}

group = "tech.digitaldojo.jtba"
version = "1.0-SNAPSHOT"
description = "Java Telegram Bot API Wrapper"

allprojects {
    repositories {
        mavenCentral()

        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/central/") }
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version
    description = rootProject.description

    apply {
        plugin<JavaPlugin>()
        plugin<JavaLibraryPlugin>()
        plugin<MavenPublishPlugin>()
        plugin<ShadowPlugin>()
        plugin<Licenser>()
        plugin<SigningPlugin>()
        plugin<EclipsePlugin>()
        plugin<IdeaPlugin>()
        plugin("io.freefair.lombok")
    }

    tasks.compileJava.configure {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(11)
    }

    configurations.all {
        attributes.attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 17)
    }

    configure<LicenseExtension> {
        header(rootProject.file("HEADER.txt"))
        include("**/*.java")
        newLine.set(true)
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
        withSourcesJar()
        withJavadocJar()
    }

    val javaComponent = components["java"] as AdhocComponentWithVariants
    javaComponent.withVariantsFromConfiguration(configurations["shadowRuntimeElements"]) {
        skip()
    }

    signing {
        if (!version.toString().endsWith("-SNAPSHOT")) {
            val signingKey: String? by project
            val signingPassword: String? by project
            useInMemoryPgpKeys(signingKey, signingPassword)
            signing.isRequired
            sign(publishing.publications)
        }
    }

    if (System.getProperty("publishName") != null && System.getProperty("publishPassword") != null) {
        publishing {
            (components["java"] as AdhocComponentWithVariants).withVariantsFromConfiguration(configurations["shadowRuntimeElements"]) {
                skip()
            }
            publications {
                create<MavenPublication>(project.name) {
                    from(components["java"])
                    pom {
                        name.set(project.name)
                        url.set("https://github.com/PharaStarLLC/JTBA")
                        properties.put("inceptionYear", "2022")
                        licenses {
                            license {
                                name.set("MIT License (MIT)")
                                url.set("https://opensource.org/licenses/MIT")
                                distribution.set("repo")
                            }
                        }
                        developers {
                            developer {
                                id.set("LuciferMorningstarDev")
                                name.set("Lucifer Morningstar")
                                email.set("contact@lucifer-morningstar.dev")
                            }
                        }
                    }
                }
                repositories {
                    maven("https://repo.digitaldojo.tech/repository/maven-snapshots/") {
                        this.name = "digiraldojo-repo"
                        credentials {
                            this.password = System.getProperty("publishPassword")
                            this.username = System.getProperty("publishName")
                        }
                    }
                }
            }
        }
    }

    tasks {

        compileJava {
            options.compilerArgs.addAll(arrayOf("-Xmaxerrs", "1000"))
            options.compilerArgs.add("-Xlint:all")
            for (disabledLint in arrayOf("processing", "path", "fallthrough", "serial")) options.compilerArgs.add("-Xlint:$disabledLint")
            options.isDeprecation = true
            options.encoding = Charsets.UTF_8.name()
        }

        jar {
            this.archiveClassifier.set(null as String?)
            this.archiveFileName.set("${project.name}-${project.version}.${this.archiveExtension.getOrElse("jar")}")
            this.destinationDirectory.set(file("$projectDir/../out/unshaded"))
            doFirst {
                manifest {
                    attributes["Implementation-Title"] = project.name
                    attributes["Implementation-Version"] = project.version
                    attributes["Implementation-Description"] = project.description
                    attributes["Specification-Version"] = project.version
                    attributes["Implementation-Vendor"] = "digitaldojo.tech"
                    attributes["Built-By"] = System.getProperty("user.name")
                    attributes["Build-Jdk"] = System.getProperty("java.version")
                    attributes["Created-By"] = "Gradle ${gradle.gradleVersion}"
                    attributes["DigitalDojo-AppId"] = "JTBA"
                    attributes["Commit-Hash"] = hash
                }
            }
        }

        processResources {
            filteringCharset = Charsets.UTF_8.name()
            filesMatching("*.txt") {
                expand(project.properties)
            }
        }

        named("build") {
            dependsOn(named("shadowJar"))
        }

        shadowJar {
            this.isZip64 = true
            this.archiveFileName.set("${project.name}-${project.version}-shaded.jar")
            this.destinationDirectory.set(file("$projectDir/../out/shaded"))
            exclude("META-INF/**")

            doFirst {
                manifest {
                    attributes["Implementation-Title"] = project.name
                    attributes["Implementation-Version"] = project.version
                    attributes["Implementation-Description"] = project.description
                    attributes["Specification-Version"] = project.version
                    attributes["Implementation-Vendor"] = "digitaldojo.tech"
                    attributes["Built-By"] = System.getProperty("user.name")
                    attributes["Build-Jdk"] = System.getProperty("java.version")
                    attributes["Created-By"] = "Gradle ${gradle.gradleVersion}"
                    attributes["DigitalDojo-AppId"] = "JTBA"
                    attributes["Commit-Hash"] = hash
                }
            }
        }
    }
}

defaultTasks("build")
