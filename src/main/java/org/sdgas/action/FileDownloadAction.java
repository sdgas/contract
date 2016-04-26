package org.sdgas.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.AttachmentVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Component("FileDownload")
@Scope("prototype")
public class FileDownloadAction extends ActionSupport implements ModelDriven<AttachmentVO> {

    private final AttachmentVO attachmentVO = new AttachmentVO();
    private Logger logger = LogManager.getLogger(FileDownloadAction.class);
    private final static String SAVE_PATH_DIR = "D:/contract/";

    //文件下载
    @Override
    public String execute() {

        String filePath;
        if (attachmentVO.getFlag() == 1) {
            filePath = SAVE_PATH_DIR + "uploadFile/" + attachmentVO.getFileName();  //文件在项目中的路径
        } else if (attachmentVO.getFlag() == 99) {
            filePath = SAVE_PATH_DIR + "downloadFile/" + attachmentVO.getPath();
        } else {
            filePath = SAVE_PATH_DIR + "/policy/ContractManagementProcess.pdf";
        }
        File outfile = new File(filePath);
        String filename = outfile.getName();// 获取文件名称
        InputStream fis;
        try {
            fis = new BufferedInputStream(new FileInputStream(
                    filePath));
        } catch (FileNotFoundException e) {
            logger.error(e);
            attachmentVO.setResultMessage("文件不存在，请与管理员联系！");
            return ERROR;
        }
        byte[] buffer;
        try {
            buffer = new byte[fis.available()];
            fis.read(buffer);  //读取文件流
            fis.close();
        } catch (IOException e) {
            logger.error(e);
            attachmentVO.setResultMessage("文件不存在，请与管理员联系！");
            return ERROR;
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.reset();  //重置结果集
        try {
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(filename.replaceAll(" ", "").getBytes("utf-8"),
                    "iso8859-1"));  //返回头 文件名
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
            attachmentVO.setResultMessage("字符串编码转换失败，请与管理员联系！！");
            return ERROR;
        }
        response.addHeader("Content-Length", "" + outfile.length());  //返回头 文件大小
        response.setContentType("application/octet-stream");    //设置数据种类
        //获取返回体输出权
        OutputStream os;
        try {
            os = new BufferedOutputStream(response.getOutputStream());
            os.write(buffer); // 输出文件
            os.flush();
            os.close();
        } catch (IOException e) {
            logger.error(e);
            attachmentVO.setResultMessage("输出错误，请与管理员联系！");
            return ERROR;
        }
        attachmentVO.setResultMessage("下载成功");
        return SUCCESS;
    }

    @Override
    public AttachmentVO getModel() {
        return attachmentVO;
    }
}