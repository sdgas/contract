package org.sdgas.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * 文件处理
 */
public class FileUtils {

    private static Logger logger = LogManager.getLogger(FileUtils.class.getName());

    private static boolean flag;

    /**
     * 判断后缀是否在列表中
     *
     * @param fileSufix 文件后缀
     * @param sufix     后缀列表 格式为: pdf,doc,ppt,xls
     * @return 是否匹配列表后缀
     */
    public static boolean isSufix(String fileSufix, String sufix) {
        flag = false;
        String[] sufixs = sufix.split(",");
        for (String s : sufixs) {
            if (fileSufix.equals(s)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取文件前缀
     *
     * @param fileName 文件路径
     * @return 无后缀文件路径
     */
    public static String getFilePrefix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex);
    }

    /**
     * 获取真实的文件名
     *
     * @param fileName 文件路径
     * @return 文件名
     */
    public static String getRealFileName(String fileName) {
        int splitIndex = fileName.lastIndexOf("/");
        return fileName.substring(splitIndex + 1);
    }

    /**
     * 获取文件后缀
     *
     * @param fileName 文件路径
     * @return 文件后缀
     */
    public static String getFileSufix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(splitIndex + 1);
    }

    /**
     * 复制文件
     *
     * @param inputFile  输入文件路径
     * @param outputFile 输出文件路径
     * @throws java.io.FileNotFoundException
     */
    public static void copyFile(String inputFile, String outputFile) throws FileNotFoundException {
        File sFile = new File(inputFile);
        File tFile = new File(outputFile);
        FileInputStream fis = new FileInputStream(sFile);
        FileOutputStream fos = new FileOutputStream(tFile);
        int temp = 0;
        try {
            while ((temp = fis.read()) != -1) {
                fos.write(temp);
            }
        } catch (IOException e) {
            logger.error(e);
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }


    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        if (files == null) {
            return false;
        }
        for (File file1 : files) {
            //删除子文件
            if (file1.isFile()) {
                flag = deleteFile(file1.getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(file1.getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        return dirFile.delete();
    }


}
