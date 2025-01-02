-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**
#Keep  Dagger-generated classes
-keep class com.eneye.enquizgpa.core.di.**{*;}
#Keep Room-generated classes
#-keep class com.engpacalculator.gpcalculator.core.local_data_base.**{*;}
#-keep class com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.**{*;}
#-keep class com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.**{*;}
#-keep class com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.data.**{*;}
#-keep class com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.**{*;}
#-keep class com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.**{*;}
#-keep class com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.domain.**{*;}
#-keep class com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.domain.**{*;}
#-keep class com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.domain.**{*;}
#-keep class com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaViewModel
#-keep class com.engpacalculator.gpcalculator.features.Four_grading_system_sgpa_features.presentation.FourGpaViewModel


#Keep retrofit related
-keep class com.eneye.enquizgpa.features.demo_quiz_features.data.**{*;}
-keep class com.eneye.enquizgpa.features.demo_quiz_features.domain.**{*;}
-keepattributes *Annotation*, EnclosingMethod
-keepclasseswithmembers class *  {
   @retrofit2.http.* <methods>;
}



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

##FROM STACK OVERFLOW
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-dontpreverify

-dontnote android.net.http.*
-dontnote org.apache.commons.codec.**
-dontnote org.apache.commons.logging.**
-dontnote org.apache.http.**


# Enable Optimization. # Optimization is turned off by default.
-optimizations   code/simplification/arithmetic,!code/simplification/cast,!field
-optimizationpasses 5
-allowaccessmodification


#Disable Optimization
#-dontoptimize
#-dontpreverify

# Remove Log command from code
-assumenosideeffects class android.util.Log{
 public static *** d(...);
 public static *** i(...);
 public static *** v(...);
}

# ------------------------------------------------
# -------------------------------------------------
-keepattributes *Annotation*, Signature, Exception, EnclosingMethod, InnerClasses
-keepattributes JavascriptInterface

# Keep source file name and line number
-keepattributes SourceFile,LineNumberTable

#-keep class okhttp3.** {*;}
#-keep interface okhttp3.** {*;}
#-dontwarn okhttp3.**
#
#-keep class okio.** { *; }
#-keep interface okio.** { *; }
#-dontwarn okio.**
#
#-keep class org.apache.http.** { *; }
#-keep class org.apache.james.mime4j.** { *; }
#-dontwarn org.apache.**
#
#-keep class com.activeandroid.** { *; }
#-keep class com.activeandroid.**.** { *; }
#-dontwarn com.activeandroid.**
#
#
#-keep class android.support.v7.** { *; }
#-keep interface android.support.v7.** { *; }
#-dontwarn android.support.v7.**

# Dontwarn-----------------------------------------
-dontwarn javax.**
-dontwarn java.lang.management.**
-dontwarn org.apache.log4j.**
-dontwarn org.apache.commons.logging.**
-dontwarn android.support.**
-dontwarn com.google.ads.**
-dontwarn org.slf4j.**
-dontwarn org.json.**


#-keep public class * extends android.view.View {
#    public <init>(android.content.Context);
#    public <init>(android.content.Context, android.util.AttributeSet);
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#    public void set*(...);
#}


# -------------------------------------------------
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembernames class * {
    native <methods>;
}



-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#-keepclassmembers class * extends android.app.Activity {
#   public void *(android.view.View);
#}
#
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#
#-keepclassmembers class **.R$* {
#    public static <fields>;
#}
#
#-keepclassmembers class * {
#    @android.webkit.JavascriptInterface <methods>;
#}
#
#
## Support Library
#-keep class android.support.** {*;}
#-keep interface android.support.** {*;}
#
#
## Needed when building against Marshmallow SDK.
#  -dontwarn android.app.Notification


# Retrofit and GSON
#-keep class com.squareup.okhttp3.** { *; }
#-keep interface com.squareup.okhttp3.** { *; }
#-dontwarn com.squareup.okhttp3.**
#
#-keep class retrofit2.** { *; }
#-dontwarn retrofit2.**
#
#-keep class com.google.gson.** { *; }
#-dontwarn com.google.gson.**
#
#-keep class sun.misc.Unsafe.** { *; }
#-dontwarn sun.misc.Unsafe.**
#
#-keep public class com.google.gson.** {*;}
#-keep class * implements com.google.gson.** {*;}
#-keep class com.google.gson.stream.** { *; }
#-dontwarn com.google.gson.**

#-keep class * implements com.google.gson.TypeAdapterFactory
#-keep class * implements com.google.gson.JsonSerializer
#-keep class * implements com.google.gson.JsonDeserializer

-keepclasseswithmembers class * {@retrofit2.http.* <methods>;}
-keepclasseswithmembers interface * { @retrofit2.* <methods>;}
-dontwarn com.google.appengine.**
-dontwarn java.nio.file.**
-dontwarn org.codehaus.**
-dontwarn org.codehaus.mojo.**
-dontnote retrofit2.Platform



# OkHttp and Picasso
#-keep class com.squareup.okhttp.** { *; }
#-keep interface com.squareup.okhttp.** { *; }
#-dontwarn com.squareup.okhttp.**
#


# Keep line number
-keepattributes SourceFile,LineNumberTable


## Google Play Admob
#    -keep public class com.google.android.gms.ads.** {
#        public *;
#    }
#
#    -keep public class com.google.ads.** {
#         public *;
#    }
#
#    -keep class com.google.ads.** # Don't proguard AdMob classes
#    -dontwarn com.google.ads.** # Temporary workaround for v6.2.1. It gives a warning that you can ignore
#
#    # Facebook
#-keep class com.facebook.** {*;}
#-dontwarn com.facebook.**


-keepclassmembers class * implements java.io.Serializable {
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Lru Cache
-dontwarn com.squareup.picasso.LruCache.**

# Firebase
#-keep class com.firebase.** { *; }
#-dontwarn com.firebase.**

#-keepnames class com.shaded.fasterxml.** { *; }
#-dontwarn org.shaded.apache.**
#
#-keep class org.apache.** { *; }
#-dontwarn org.apache.**
#
#-keepnames class org.ietf.jgss.** { *; }
#-dontwarn org.ietf.jgss.**
#
#-keepnames class com.fasterxml.jackson.** { *; }
#-dontwarn com.fasterxml.**
#
#-keepnames class javax.servlet.** { *; }
#
#-dontwarn org.w3c.dom.**
#-dontwarn org.joda.time.**

## Android architecture components: Lifecycle
# LifecycleObserver's empty constructor is considered to be unused by proguard

# ViewModel's empty constructor is considered to be unused by proguard

#-keepclassmembers class android.arch.** { *; }
#-keep class android.arch.** { *; }
#-dontwarn android.arch.**







#-keep class androidx.lifecycle.** { *; }

#all

-ignorewarnings
-keep class * {
    public private *;
}

#-keep class com.google.android.gms.** { *; }
#-dontwarn com.google.android.gms.**
#
#-dontwarn org.xmlpull.v1.**
#-dontnote org.xmlpull.v1.**
#-keep class org.xmlpull.** { *; }

#retRofit
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
#-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
#-dontwarn retrofit2.Platform$Java17
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
# Orm

-keepclassmembers class * {
    native <methods>;
}
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**




# Keep source file names, line numbers, and Parse class/method names for easier debugging
 -keepattributes SourceFile,LineNumberTable
 -keepnames class com.parse.** { *; }


 # Required for Parse
 -keepattributes *Annotation*
 -keepattributes Signature
 -dontwarn com.squareup.**
 -dontwarn okio.**


-keepattributes SourceFile,LineNumberTable
-keep class com.parse.*{ *; }
-dontwarn com.parse.**
-dontwarn com.squareup.picasso.**
-keepclasseswithmembernames class * {
    native <methods>;
}

-keepattributes *Annotation*,EnclosingMethod
-keep @interface dagger.*,javax.inject.*
-keep @dagger.Module class *
-keepclassmembers class * {
    @javax.inject.* *;
    @dagger.* *;

}
-keepclasseswithmembernames class * {
@javax.inject.* *;
}

-keep class javax.inject.** { *; }
-keep class **$$ModuleAdapter
-keep class **$$InjectAdapter
-keep class **$$StaticInjection

-keep class dagger.** { *; }
-keep class * extends dagger.** { *; }
-keep interface dagger.** {*;}
-dontwarn dagger.internal.codegen.**

-keep class * extends dagger.hilt.*