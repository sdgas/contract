package org.sdgas.util;

/**
 * Created by 120378 on 2014/7/22.
 */

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * 本机系统信息
 *
 * @author longgangbai
 */


public final class SystemHelper {

    private static final Logger logger = Logger.getLogger(SystemHelper.class);

    //获得系统属性集
    public static Properties props = System.getProperties();
    //操作系统名称
    public static String OS_NAME = getProperty("os.name");
    //行分页符
    public static String OS_LINE_SEPARATOR = getProperty("line.separator");
    //文件分隔符号
    public static String OS_FILE_SEPARATOR = getProperty("file.separator");

    /**
     * 根据系统的类型获取本服务器的ip地址
     * <p/>
     * InnerAddress innerAddress = InnerAddress.getLocalHost();
     * 但是上述代码在Linux下返回127.0.0.1。
     * 主要是在linux下返回的是/etc/hosts中配置的localhost的ip地址，
     * 而不是网卡的绑定地址。后来改用网卡的绑定地址，可以取到本机的ip地址：）：
     *
     * @throws UnknownHostException
     */
    public static InetAddress getSystemLocalIp() throws UnknownHostException {
        InetAddress innerAddress = null;
        String osName = getSystemOSName();
        try {
            //针对window系统
            if (osName.toUpperCase().contains("WINDOWS")) {
                innerAddress = getWinLocalIp();
                //针对linux系统
            } else if (osName.equalsIgnoreCase("Linux")) {
                innerAddress = getUnixLocalIp();
            }
            if (null == innerAddress) {
                throw new UnknownHostException("主机的ip地址未知");
            }
        } catch (SocketException e) {
            logger.error("获取本机ip错误" + e.getMessage());
            throw new UnknownHostException("获取本机ip错误" + e.getMessage());
        }
        return innerAddress;
    }

    /**
     * 获取FTP的配置操作系统
     *
     * @return 操作系统名称
     */
    public static String getSystemOSName() {
        //获得系统属性集
        Properties props = System.getProperties();
        //操作系统名称
        String osName = props.getProperty("os.name");
        if (logger.isDebugEnabled()) {
            logger.info("the ftp client system os Name " + osName);
        }
        return osName;
    }

    /**
     * 获取属性的值
     *
     * @param propertyName 属性名字
     * @return
     */
    public static String getProperty(String propertyName) {
        return props.getProperty(propertyName);
    }


    /**
     * 获取window 本地ip地址
     *
     * @return ip地址
     * @throws UnknownHostException
     */
    private static InetAddress getWinLocalIp() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }

    /**
     * 可能多个ip地址只获取一个ip地址
     * 获取Linux 本地IP地址
     *
     * @return ip地址
     * @throws SocketException
     */
    private static InetAddress getUnixLocalIp() throws SocketException {
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip;
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = netInterfaces.nextElement();
            ip = ni.getInetAddresses().nextElement();
            if (!ip.isSiteLocalAddress()
                    && !ip.isLoopbackAddress()
                    && !ip.getHostAddress().contains(":")) {
                return ip;
            }
        }
        return null;
    }

    /**
     * 获取当前运行程序的内存信息
     *
     * @ return
     */
    public static String getRAMinfo() {
        Runtime rt = Runtime.getRuntime();
        return "RAM: " + rt.totalMemory() + " bytes total, " + rt.freeMemory() + " bytes free.";

    }


    //获取当前登录用户IP地址
    public static String getIpAddress() {
        String ipAddress = ServletActionContext.getRequest().getHeader("x-forwarded-for");

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = ServletActionContext.getRequest().getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = ServletActionContext.getRequest().getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = ServletActionContext.getRequest().getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if (inet != null) {
                    ipAddress = inet.getHostAddress();
                }
            }
        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}

