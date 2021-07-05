package eu.talich.waller.plugin

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.kotlin.dsl.getPlugin

class WallerKotlinModule : Plugin<Project> {
    override fun apply(project: Project) {
        project.configurePlugins()
        project.configureDependencies()

        project.configureJava()
    }
}

private fun Project.configurePlugins() {
    plugins.apply("java-library")
    plugins.apply("kotlin")
}

private fun Project.configureJava() {
    convention.getPlugin<JavaPluginConvention>().apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

private fun Project.configureDependencies() {
    dependencies.apply {
        add("implementation", Kotlin.KotlinX.coroutinesCore)

        add("implementation", Koin.core)
        add("implementation", Koin.coreExt)
        add("testImplementation", Koin.test)
    }
}