# snapshot-source-updater
Update snapshot aar source jar by clean task.

### Guide
1.Add resolutionStrategy for gradle. Like:
```
configurations.all {
    // check for updates every build
    resolutionStrategy.cacheChangingModulesFor 0, 'seconds'
}
```

2. Add classpath under dependencies in project root build.gradle file.
 ```
 classpath 'com.tinyxu.tools:snapshot-source-updater:1.0.1'
 ```
 
 3. Add apply plugin the end of project root build.gradle file.
 ```
 apply plugin: 'snapshot-source-updater'
 ```
 4. Do clean task.
 
 Ok, after will find the newest source jar attached when you do clean task.
 
 Tip: Gradle download aar and source jar on Aync Now and build. So you want update snapshot source jar must download first.
 
 Sample:
 ```
 buildscript {
    ...
   
    dependencies {
        ...
        
        classpath 'com.tinyxu.tools:snapshot-source-updater:1.0.1'
    }
}

...

apply plugin: 'snapshot-source-updater'
 ```
 
 _____________________________________________________________________________________________________
 
 
 
 
### snapshot-source-updater使用说明
1.添加gradle更新策略，如:
```
configurations.all {
    // check for updates every build
    resolutionStrategy.cacheChangingModulesFor 0, 'seconds'
}
```
2. 在根项目的build.gradle中dependencies下添加classpath如下：
 ```
 classpath 'com.tinyxu.tools:snapshot-source-updater:1.0.1'
 ```
 
 3. 在根项目的build.gradle中dependencies下添加plugin引用：
 ```
 apply plugin: 'snapshot-source-updater'
 ```
 4. 执行项目clean操作。
 
就这样三步，每次执行clean操作就会为snapshot aar寻找最新的source jar关联。

注意：Gradle会在Sync Now和Build时下载aar和source jar，所以要更新snapshot source jar必须先下载source jar。



