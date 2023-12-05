pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        /*
        maven {
            url = uri("http://jitpack.io")  // 그래프 그릴 때 사용하려고 했는데 오류 남 -> 해결 방법 찾는 중
        }*/
    }
}

rootProject.name = "mobilesoftware_project"
include(":app")
 