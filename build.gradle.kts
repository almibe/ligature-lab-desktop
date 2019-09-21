plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.21")
    application
}

group = "org.almibe"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenLocal()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.almibe:ligature:0.1.0-SNAPSHOT")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "org.libraryweasel.desktop.MainKt"
}
