# Work Sample for Mobile Aspect, Kotlin Variant

## This is a library built on top of retrofit to send location data to given url endpoint. 

# Download 
To get a sdk into your build:

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.irenicj:ws-mobile-kotlin:0.5'
	}

[![](https://jitpack.io/v/irenicj/ws-mobile-kotlin.svg)](https://jitpack.io/#irenicj/ws-mobile-kotlin)




## Initilizing and usage

``` kotlin
 val eqMobileWorkLibrary = Library() // init
            eqMobileWorkLibrary.setup("https://httpbin.org/post/") //set url to the endpoint
            eqMobileWorkLibrary.log(LocationEvent(2.4f, 23.5f))
 ```
 
 - LocationEvent is a DataClass which can be dynamically initialized and used from the library. 
 - log function can explicitly set time to the server. 
 - If log is called with no params it will set the lat, long to (0,0) 
 - Field name "ext" : String can be used for silent error logging on the server

[What is this for?](https://github.com/EQWorks/work-samples#what-is-this)

