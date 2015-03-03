package org.sdgas.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LW
 *         该类抽象了我平时常用的一些属性和方法，降低耦合度和代码量
 */

public class MyActionSupport extends ActionSupport {
    /**
     * action执行成功，并显示一个动态定义的view
     */
    public final String VIEW = "view";
    /**
     * action执行成功，并转向一个动态定义的action
     */
    public final String ACTION = "action";

    /**
     * view的url，告诉struts你要转到哪个视图
     */
    public String view;

    /**
     * action的url，告诉struts你要转到哪个action
     */
    public String action;

    /**
     * @param message 该方法主要是给前台传送一个字符串，多用于处理ajax请求
     */
    public void sendResponse(Object message) {
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
            out.print(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert out != null;
            out.close();
        }
    }

    /**
     * @param message 该方法主要是给前台传送一个字符串，多用于处理ajax请求
     */
    public void sendResponse(String message) {
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
            out.print(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert out != null;
            out.close();
        }
    }

    /**
     * 简化获取session的过程
     */
    public void getSession() {

    }
}
