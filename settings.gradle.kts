pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://maven.aliyun.com/repository/central")
        }
        maven {
            url = uri("https://repo.huaweicloud.com/repository/maven")
        }
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.aliyun.com/repository/central")
        }
        maven {
            url = uri("https://repo.huaweicloud.com/repository/maven")
        }
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "KirinCore"
include(":app")
include(":kirin-core")
