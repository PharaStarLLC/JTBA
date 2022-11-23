import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("commons-io:commons-io:2.11.0")
    implementation(project(":jtba"))
}

tasks.named<ShadowJar>("shadowJar") {
    minimize()
    mergeServiceFiles()
    doLast {
        copy {
            from("$projectDir/../out/shaded/${project.name}-${project.version}-shaded.jar")
            rename("${project.name}-${project.version}-shaded.jar", "${project.name}-${project.version}.jar")
            into("$projectDir/../out/")
        }
    }
}
