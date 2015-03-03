package org.sdgas.util;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.sdgas.base.PageIndex;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

public class WebTool {

    // 保留小数位,dout为输入实数,n为保留的位数
    public static double keepSmallDigital(double dout, int n) {
        double p = Math.pow(10, n);
        return Math.round(dout * p) / p;

    }

    //对字符串的简单检查
    public static boolean checkStatus(String param) {
        return null != param && !"".equals(param.trim());
    }

    public static boolean isDigit(String str) {
        return str.replaceAll("\\d", "").equals("");
    }

    //对字符串的简单检查
    public static double stringToDouble(String s) {
        if ("".equals(s.trim()))
            return 0.0d;
        else
            return Double.parseDouble(s.trim());
    }

    /**
     * 下载时调用的返回设置方法
     *
     * @param fileName    文件的名字
     * @param contentType 文件类型
     * @param ext         文件类型的后缀
     */
    public static void downloadFile(String fileName, String contentType,
                                    String ext) {
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH) + 1;
        int d = cal.get(Calendar.DATE);
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int s = cal.get(Calendar.SECOND);
        ActionContext context = ActionContext.getContext();
        HttpServletResponse response = (HttpServletResponse) context
                .get(ServletActionContext.HTTP_RESPONSE);
        response.setContentType(contentType);
        fileName = y + "-" + m + "-" + d + "-" + h + "-" + min + "-" + s
                + fileName;
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;filename="
                + fileName + "." + ext);
    }

    /**
     * 页面分页功能的工具
     */
    public static PageIndex getPageIndex(long viewPageCount, int currentPage, long totalPage) {
        long startPage = currentPage - (viewPageCount % 2 == 0 ? viewPageCount / 2 - 1
                : viewPageCount / 2);
        long endPage = currentPage + viewPageCount / 2;
        if (startPage < 1) {
            startPage = 1;
            if (totalPage >= viewPageCount) endPage = viewPageCount;
            else endPage = totalPage;
        }
        if (endPage > totalPage) {
            endPage = totalPage;
            if ((endPage - viewPageCount) > 0) startPage = endPage - viewPageCount + 1;
            else startPage = 1;
        }
        return new PageIndex(startPage, endPage);
    }

}
