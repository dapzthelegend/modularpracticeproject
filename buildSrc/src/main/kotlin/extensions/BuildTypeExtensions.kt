package extensions

import com.android.build.gradle.internal.dsl.BuildType


fun BuildType.buildConfigStringField(name:String, value:String){
    this.buildConfigField(name = name, value = "\"$value\"", type = "String")
}

fun BuildType.buildConfigIntField(name:String, value:Int){
    this.buildConfigField(name = name, value = value.toString(), type = "int")
}

fun BuildType.buildConfigBooleanField(name:String, value:Boolean){
    this.buildConfigField(name = name, value = value.toString(), type = "boolean")
}