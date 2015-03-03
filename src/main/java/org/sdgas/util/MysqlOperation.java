package org.sdgas.util;

import java.io.*;

public class MysqlOperation {
    public static void backupMysqlDatabase(String mysqlBinUrl, String hostName,
                                           String dataBase, String userName,
                                           String password, String outFilePath) throws Exception {
        // 组装备份MySQL的命令
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append(mysqlBinUrl).append("mysqldump -u").append(userName)
                .append(" -p").append(password).append(" ").append(dataBase);
        if (hostName != null && !hostName.equals(""))
            sqlStr.append(" ").append(" -h").append(hostName);
        // 调用系统cmd命令执行MySQL备份命令
        Runtime rt = Runtime.getRuntime();
        Process process = rt.exec(sqlStr.toString());
        InputStream in = process.getInputStream();  // 控制台的输出信息作为输入流
        InputStreamReader isr = new InputStreamReader(in, "utf-8");  //utf-8的字符集

        StringBuilder sb = new StringBuilder("");
        String inStr;
        // 组合控制台输出信息字符串
        BufferedReader br = new BufferedReader(isr);     //创建缓冲字符输入流
        while ((inStr = br.readLine()) != null)
            sb.append(inStr).append("\r\n");

        // 把备份数据写入到文件中
        FileOutputStream fout = new FileOutputStream(outFilePath);
        OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
        writer.write(sb.toString());
        writer.flush();
        // 写完文件，关闭相应的流
        in.close();
        isr.close();
        br.close();
        writer.close();
        fout.close();
    }

    public static void resumeMysqlDatabase(String mysqlBinUrl, String hostName,
                                           String dataBase, String userName,
                                           String password, String outFilePath) throws Exception {
        // 组装备份MySQL的命令
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append(mysqlBinUrl).append("mysql -u").append(userName)
                .append(" -p").append(password).append(" ").append(dataBase);
        if (hostName != null && !hostName.equals(""))
            sqlStr.append(" ").append(" -h").append(hostName);
        Runtime rt = Runtime.getRuntime();
        // 调用 mysql 安装目录的命令
        Process child = rt.exec(sqlStr.toString());
        OutputStream out = child.getOutputStream();// 控制台的输入信息作为输出流
        String inStr;
        StringBuffer sb = new StringBuffer("");
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(outFilePath), "utf-8"));
        while ((inStr = br.readLine()) != null)
            sb.append(inStr + "\r\n");
        OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
        writer.write(sb.toString());
        writer.flush();
        br.close();
        writer.close();
        out.close();
    }
}

