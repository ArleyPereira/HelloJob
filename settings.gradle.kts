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
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HelloJob"
include(":app")
include(":design")
include(":onboarding")
include(":authentication")
include(":di")
include(":core")
include(":setup")
include(":common")
include(":job-details")
include(":job-search")
include(":job-applying")
include(":main")
