package com.tinyxu.tools

import org.gradle.api.Plugin
import org.gradle.api.Project

class SnapshotSourceUpdaterPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.tasks.findByName('clean').doLast {
            this.updateSnapshotSourcesPath(project)
        }
    }

    /**
     * 更新SNAPSHOT aar的sources
     *
     * @param project
     */
    void updateSnapshotSourcesPath (Project project) {
        def librariesConfigDirPath = project.rootProject.projectDir.absolutePath + "/.idea/libraries/"

        File libConfigDir = new File(librariesConfigDirPath)
        if (libConfigDir.exists() && libConfigDir.isDirectory()) {
            // 获取所有的snapshot aar对应的配置文件
            File[] snapshotLibFiles = libConfigDir.listFiles(new FilenameFilter() {
                @Override
                boolean accept(File file, String s) {
                    if (s != null && s.contains("SNAPSHOT")) {
                        return true
                    }
                    return false
                }
            })

            snapshotLibFiles.each {
                File file   ->
                    // 得到gradle缓存该lib的路径和目录名字
                    def lidDirAndOrigalDirName = getSnapshotLibPath(file)
                    // 该snapshot各个版本保存的路径
                    def snapshotAllDirName = lidDirAndOrigalDirName[0]
                    // 当前snapshot source版本保存的目录名
                    def curSourceDirName = lidDirAndOrigalDirName[1]

                    // 获取最新的source jar对应的目录名
                    def newestFileDirName = findNewestFileDirName(new File(snapshotAllDirName))
                    // 替换source jar为新的目录名
                    replaceNewestSourceFileDirName(file, curSourceDirName, newestFileDirName)
            }
        }
    }

    static String[] getSnapshotLibPath(File file) {
        def path = null
        def originSnapshotDirName = null

        file.eachLine {
            String str, int no  ->
                if (str.trim().contains("-SNAPSHOT-sources.jar")) {
                    def startTag = "USER_HOME\$"
                    def endTag = "-SNAPSHOT/"
                    def pathStartIndex = str.indexOf(startTag) + startTag.length()
                    def pathEndIndex = str.indexOf(endTag) + endTag.length()

                    def libPath = System.getProperty("user.home") + str.subSequence(pathStartIndex, pathEndIndex)
                    path = libPath

                    String dirNameStr = str.subSequence(pathEndIndex, str.length())
                    originSnapshotDirName = dirNameStr.subSequence(0, dirNameStr.indexOf("/"))
                    return
                }
        }

        String[] result = new String[2]
        result[0] = path
        result[1] = originSnapshotDirName
        return result
    }

    static String findNewestFileDirName (File dir) {
        def newestFileTime = 0
        def newestFileDirName = null

        if (dir != null && dir.isDirectory() && dir.exists()) {
            dir.listFiles().each {
                File file    ->
                    // 遍历该lib目录下的所有目录，找到包含source.jar的目录，并且是最新的
                    if (file.isDirectory() && file.lastModified() > newestFileTime) {
                        file.listFiles().each {
                            File f    ->
                                if (f.getName().contains("sources")) {
                                    newestFileTime = file.lastModified()
                                    newestFileDirName = file.getName()
                                }
                        }
                    }
            }
        }

        return newestFileDirName
    }

    static void replaceNewestSourceFileDirName(File file, String originDirName, String newDirName) {
        if (originDirName.equals(newDirName)) {
            return
        }

        if (file == null || !file.exists()) {
            return
        }


        char[] buf = new char[file.length()]
        FileReader reader = new FileReader(file)
        reader.read(buf, 0, file.length() as int)
        reader.close()

        String content = String.valueOf(buf)
        content = content.replace(originDirName, newDirName)

        FileWriter writer = new FileWriter(file)
        writer.write(content)
        writer.flush()
        writer.close()
    }
}