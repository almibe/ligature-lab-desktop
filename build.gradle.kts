plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.50")
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
    compile("org.almibe:multi-page-display:0.4.0-SNAPSHOT")
    implementation("org.koin:koin-core:2.0.1")
    implementation("org.koin:koin-core-ext:2.0.1")
    testImplementation("org.koin:koin-test:2.0.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "org.libraryweasel.desktop.MainKt"
}
