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
    compile("org.codehaus.groovy:groovy:2.5.7")
    implementation("com.fifesoft:rsyntaxtextarea:3.0.4")
    implementation("org.koin:koin-core:2.0.1")
    implementation("org.koin:koin-core-ext:2.0.1")
    runtimeOnly("org.openjfx:javafx:13:win")
    runtimeOnly("org.openjfx:javafx:13:linux")
    runtimeOnly("org.openjfx:javafx:13:mac")
    testImplementation("org.koin:koin-test:2.0.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "org.libraryweasel.desktop.MainKt"
}
