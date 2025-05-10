# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

########################################
# Jetpack Compose
########################################
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

########################################
# Navigation Compose
########################################
-keep class androidx.navigation.** { *; }
-dontwarn androidx.navigation.**

########################################
# Dagger Hilt
########################################
-keep class dagger.hilt.** { *; }
-dontwarn dagger.hilt.**

# Hilt-generated components and members
-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }
-keepclassmembers class ** {
    @dagger.hilt.EntryPoint *;
}
-keep class **_Factory { *; }
-keep class **_HiltModules* { *; }

########################################
# Room
########################################
# Keep annotated entities and DAOs
-keepclassmembers class * {
    @androidx.room.* <methods>;
    @androidx.room.* <fields>;
}
-keep class androidx.room.RoomDatabase { *; }
-dontwarn androidx.room.**

########################################
# Firebase
########################################
# Keep Firebase classes used via reflection
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**

# Needed for Firebase Analytics / Crashlytics
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

########################################
# Kotlin / Coroutines / Reflection
########################################
-dontwarn kotlin.**
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

# Keep @Keep annotated
-keep @androidx.annotation.Keep class * { *; }

# Optional: for Moshi/Gson if needed later
# -keep class com.squareup.moshi.** { *; }
# -keep class com.google.gson.** { *; }

########################################
# Avoid removing unused code that's used via reflection
########################################
-keepclassmembers class * {
    public <init>(...);
}
