# snapshot-source-updater
Update snapshot aar source jar by clean task.

### snapshot-source-updater使用说明
1. 在根项目的build中dependencies下添加classpath如下：
 ```
 classpath 'com.tinyxu.tools:snapshot-source-updater:1.0.0'
 ```
 
 2. 在根项目的build中dependencies下添加plugin引用：
 ```
 apply plugin: 'snapshot-source-updater'
 ```
 
就这样两步配置每次执行clean操作就会为snapshot aar寻找最新的source jar关联。
