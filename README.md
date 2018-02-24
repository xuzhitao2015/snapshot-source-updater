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
 
 Ok, after will find the newest source jar attached when you do clean task.
 
 
 
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
 
就这样两步配置每次执行clean操作就会为snapshot aar寻找最新的source jar关联。
