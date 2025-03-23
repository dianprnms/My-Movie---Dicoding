# =======================
# KEEP CORE MODULE CLASSES
# =======================
-keep class com.example.core.** { *; }

# =======================
# ROOM DATABASE
# =======================
-keep class androidx.room.** { *; }
-keep class com.example.core.data.local.** { *; }
-keep class * extends androidx.room.RoomDatabase { *; }
-keep class * extends androidx.room.Dao { *; }
-keep class * extends androidx.room.Entity { *; }

# =======================
# RETROFIT & OKHTTP
# =======================
-keep interface com.example.core.data.remote.** { *; }
-keep class com.example.core.data.model.** { *; }
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn retrofit2.**
-dontwarn okhttp3.**

# =======================
# DAGGER HILT
# =======================
-keep class dagger.hilt.** { *; }
-keep class com.example.core.di.** { *; }
-dontwarn dagger.**

# =======================
# LIVEDATA & VIEWMODEL
# =======================
-keep class androidx.lifecycle.** { *; }
-keep class com.example.core.ui.viewmodel.** { *; }

# =======================
# GLIDE (IMAGE LOADER)
# =======================
-keep class com.bumptech.glide.** { *; }
-dontwarn com.bumptech.glide.**

# =======================
# RXJAVA & COROUTINES
# =======================
-keep class io.reactivex.** { *; }
-keep class io.reactivex.rxjava3.** { *; }
-keep class kotlinx.coroutines.** { *; }
-dontwarn io.reactivex.**
-dontwarn kotlinx.coroutines.**

# =======================
# GSON (JSON SERIALIZATION)
# =======================
-keep class com.google.gson.** { *; }
-keep class com.example.core.data.model.** { *; }
-dontwarn com.google.gson.**

# =======================
# DATASTORE (SHARED PREFERENCES)
# =======================
-keep class androidx.datastore.** { *; }
-keep class com.example.core.data.pref.** { *; }

# =======================
# CHUCKER (DEBUGGING TOOL)
# =======================
-keep class com.github.chuckerteam.chucker.** { *; }
-dontwarn com.github.chuckerteam.chucker.**

# =======================
# LOTTIE (ANIMASI)
# =======================
-keep class com.airbnb.lottie.** { *; }
-dontwarn com.airbnb.lottie.**

# =======================
# OKHTTP LOGGING INTERCEPTOR
# =======================
-dontwarn com.squareup.okhttp3.logging.**

# =======================
# SQLITE & SQLCIPHER
# =======================
-keep class net.sqlcipher.** { *; }
-keep class androidx.sqlite.db.** { *; }
-dontwarn net.sqlcipher.**
-dontwarn androidx.sqlite.db.**

# =======================
# ANNOTATION HANDLING
# =======================
-keepattributes *Annotation*
