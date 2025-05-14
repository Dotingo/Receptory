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

########################################
# Hilt-generated components and members
########################################
-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }
-keepclassmembers class ** {
    @dagger.hilt.EntryPoint *;
}
-keep class **_Factory { *; }
-keep class **_HiltModules* { *; }

########################################
# Room
# Keep annotated entities and DAOs
########################################
-keepclassmembers class * {
    @androidx.room.* <methods>;
    @androidx.room.* <fields>;
}
-keep class androidx.room.RoomDatabase { *; }
-dontwarn androidx.room.**

########################################
# Firebase
# Keep Firebase classes used via reflection
########################################
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**

########################################
# Firebase Analytics / Crashlytics
########################################
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

########################################
# Kotlin / Coroutines / Reflection
########################################
-dontwarn kotlin.**
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

########################################
# Keep @Keep annotated
########################################
-keep @androidx.annotation.Keep class * { *; }

########################################
# Avoid removing unused code that's used via reflection
########################################
-keepclassmembers class * {
    public <init>(...);
}
