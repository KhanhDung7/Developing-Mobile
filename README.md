   + In file "build.gradle" of project
	* Add "google()" within "repositories"
	* Change gradle version within dependencies
	   - If using JDK 16, choose version "7.x.x"
           - If using JDK 11 or below, choose version "6.x.x"
   + In file "builde.gradle" of module
   	* Change "compileSdkVersion" and targetSdkVersion to API level of Android SDK
   	* Change "buildToolVersion" compatible with "compileSdkVersion"
   + In file "gradle-wrapper.properties": change "distributrionUrl"
   + In file "AndroidManifest.xml"
   	* In "uses-sdk" change "minSdkVersion" and "targetSdkVersion" equal in file "build.gradle" of module
   	* If using JDK 16, add "android:exported="true"" in application->actitvity