import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("commons-io:commons-io:2.11.0")
}

tasks.named<ShadowJar>("shadowJar") {
    // Get rid of all the libs which are 100% unused.
    minimize()
    mergeServiceFiles()

    doFirst {
        manifest {
            attributes["Dojo-Web"] = "https://digitaldojo.tech/"
        }
    }

    doLast {
        copy {
            from("$projectDir/../out/shaded/${project.name}-${project.version}-shaded.jar")
            rename("${project.name}-${project.version}-shaded.jar", "${rootProject.name}-${rootProject.version}-withDependencies.jar")
            into("$projectDir/../out/")
        }
    }

}
