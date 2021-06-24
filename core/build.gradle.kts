import extensions.buildConfigBooleanField
import extensions.buildConfigIntField
import extensions.buildConfigStringField
import extensions.getLocalProperty
import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies


plugins {
    id("commons.android-library")
}

android {
    buildTypes.forEach{

        try{
            it.buildConfigStringField("MARVEL_API_BASE_URL", "https://gateway.marvel.com/")
            it.buildConfigStringField("MARVEL_API_KEY_PUBLIC", getLocalProperty("marvel.key.public"))
            it.buildConfigStringField("MARVEL_API_KEY_PRIVATE", getLocalProperty("marvel.key.private"))

            it.buildConfigBooleanField("MARVEL_DATABASE_EXPORT_SCHEMA", false)
            it.buildConfigStringField("MARVEL_DATABASE_NAME", "characters-db")
            it.buildConfigIntField("MARVEL_DATABASE_VERSION", 1)

        }catch (ignore:Exception){
            throw InvalidUserDataException("You should define 'marvel.key.public' and " +
                    "'marvel.key.private' in local.properties. Visit 'https://developer.marvel.com' " +
                    "to obtain them.")
        }

    }
}

dependencies {
    implementation(Dependencies.ROOM)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.LOGGING)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_KTX)

    kapt(AnnotationProcessorsDependencies.ROOM)

}
