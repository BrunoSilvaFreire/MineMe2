allprojects {
    group = "me.ddevil"
    version = "2.0.0-SNAPSHOT"
}
buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.3.72"))
    }
}
val shiroi_ver by project.extra("3.3.0-SNAPSHOT")


setup(rootProject)

fun setup(project: Project) {

    if (project.childProjects.isEmpty()) {
        project.plugins.apply("org.jetbrains.kotlin.jvm")
        project.plugins.apply("idea")
//        project.sourceCompatibility = 1.8
//        project.targetCompatibility = 1.8
//        project.compileJava.options.encoding = 'UTF-8'

        project.repositories {
            mavenCentral()
            mavenLocal()
//shiroi
            maven { url = uri("http://repo.dmulloy2.net/content/groups/public/") }
//bukkit
            maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
//mvdw
            maven { url = uri("http://repo.mvdw-software.com/content/groups/public/") }
//placeholder me.ddevil.mineme.craft.api
            maven { url = uri("http://repo.extendedclip.com/content/repositories/placeholderapi/") };
//TaskChain
            maven { url = uri("http://ci.emc.gs/nexus/content/groups/aikar/") }
//World Edit
            maven { url = uri("http://maven.sk89q.com/repo") }
            maven { url = uri("https://repo.lunari.studio/repository/maven-public/")}
        }

        project.dependencies {
            kotlin("stdlib-8")
        }
        /*project.tasks.withType(Jar) {
            destinationDir = file("$rootDir/output")
        }*/
    } else {
        project.childProjects.forEach { p ->
            setup(p.value)
        }
    }
}