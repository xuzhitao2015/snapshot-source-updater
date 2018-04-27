# snapshot-source-updater
Update snapshot aar source jar by clean task.

### Guide
1. Add classpath under dependencies in project root build.gradle file.
 ```
 classpath 'com.tinyxu.tools:snapshot-source-updater:1.0.0'
 ```
 
 2. Add apply plugin the end of project root build.gradle file.
 ```
 apply plugin: 'snapshot-source-updater'
 ```
 3. Do clean task.
 
 Ok, after will find the newest source jar attached when you do clean task.
 
 Tip: Gradle download aar and source jar on Aync Now and build. So you want update snapshot source jar must download first.
 
 Sample:
 ```
 buildscript {
    ext.kotlin_version = '1.1.51'
    repositories {
        google()
        jcenter()
//
        maven {
            url './repo'
        }
    }
    dependencies {
        ...
        
        classpath 'com.tinyxu.tools:snapshot-source-updater:1.0.0'
    }
}

...

apply plugin: 'snapshot-source-updater'
 ```
 
 _____________________________________________________________________________________________________
 
 
 
 
### snapshot-source-updater使用说明
1. 在根项目的build.gradle中dependencies下添加classpath如下：
 ```
 classpath 'com.tinyxu.tools:snapshot-source-updater:1.0.0'
 ```
 
 2. 在根项目的build.gradle中dependencies下添加plugin引用：
 ```
 apply plugin: 'snapshot-source-updater'
 ```
 3. 执行项目clean操作。
 
就这样三步，每次执行clean操作就会为snapshot aar寻找最新的source jar关联。

注意：Gradle会在Sync Now和Build时下载aar和source jar，所以要更新snapshot source jar必须先下载source jar。



