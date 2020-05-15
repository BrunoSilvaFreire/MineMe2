plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "5.2.0"
    kotlin("jvm")
}
val shiroi_ver: String by project.parent!!.extra!!
dependencies {
    compile("me.ddevil:shiroi-framework-craft-api:$shiroi_ver")
    compile("me.ddevil:shiroi-framework-ui-shiroi:$shiroi_ver")
    compile("com.sk89q.worldedit:worldedit-bukkit:6.1.4-SNAPSHOT")
    compile("com.gmail.filoghost.holographicdisplays:holographicdisplays-api:2.4.0")
    compile(project(":mineme-api"))
    api(kotlin("stdlib"))
    //compile "me.clip:placeholderapi:2.0.8"
}

tasks.shadowJar {
    dependencies {
        val kotlin_version = "1.3.72"
        include(project(":mineme-api"))
        include(dependency("org.jetbrains.kotlin:kotlin-runtime:$kotlin_version"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"))
        include(dependency("co.aikar:taskchain-bukkit:3.4.3"))
        include(dependency("co.aikar:taskchain-core:3.4.3"))
        include(dependency("me.ddevil:json:1.2.1-SNAPSHOT"))
        include(dependency("me.ddevil:kotlin-utils:1.2.1"))
        include(dependency("me.ddevil:kotlin-utils-gson:1.2.1"))
        include(dependency("me.ddevil:shiroi-framework-util:$shiroi_ver"))
        include(dependency("me.ddevil:shiroi-framework-craft-api:$shiroi_ver"))
        include(dependency("me.ddevil:shiroi-framework-craft-util:$shiroi_ver"))
        include(dependency("me.ddevil:shiroi-framework-ui-api:$shiroi_ver"))
        include(dependency("me.ddevil:shiroi-framework-ui-shiroi:$shiroi_ver"))
    }
}
repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
    maven { url = uri("http://repo.extendedclip.com/content/repositories/placeholderapi/") }
    maven { url = uri("https://repo.codemc.io/repository/maven-public/") }
}
