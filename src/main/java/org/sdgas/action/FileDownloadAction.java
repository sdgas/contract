package org.sdgas.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sdgas.VO.AttachmentVO;
import org.sdgas.util.ChangeCharset;
import org.sdgas.util.WebTool;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;

@Component("FileDownload")
@Scope("prototype")
public class FileDownloadAction extends ActionSupport implements ModelDriven<AttachmentVO> {

    private final AttachmentVO attachmentVO = new AttachmentVO();
    private Logger logger = LogManager.getLogger(FileDownloadAction.class);
    private final static String SAVE_PATH_DIR = "D:/contract/";

    private String contentType;
    private String fileName;

    //返回一个输入流，作为一个客户端来说是一个输入流，但对于服务器端是一个 输出流
    public InputStream getDownloadFile() {
        String filePath;
        File file;
        try {
            if (attachmentVO.getFlag() == 1) {
                filePath = SAVE_PATH_DIR + attachmentVO.getFileName();
                file = new File(filePath);
                fileName = WebTool.changeCharset(attachmentVO.getFileName(), "ISO-8859-1");
            } else if (attachmentVO.getFlag() == 99) {
                filePath = SAVE_PATH_DIR + "/downloadFile/" + attachmentVO.getPath();
                System.out.println(filePath);
                fileName = ChangeCharset.toUTF_8(attachmentVO.getPath());
                file = new File(ChangeCharset.toUTF_8(filePath));
            } else {
                filePath = SAVE_PATH_DIR + "/policy/ContractManagementProcess.pdf";
                file = new File(filePath);
                fileName = "ContractManagementProcess.pdf";
            }
            return new FileInputStream(file);
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
            attachmentVO.setResultMessage("字符串编码转换失败，请与管理员联系！");
            return null;
        } catch (FileNotFoundException e) {
            logger.error(e);
            attachmentVO.setResultMessage("获取下载文件失败，请与管理员联系！");
            return null;
        }
    }

    //文件下载
    @Override
    public String execute() {
        try {
            this.contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8";
            return SUCCESS;
        } catch (Exception e) {
            logger.error(e);
            attachmentVO.setResultMessage("下载失败请与管理员联系！");
            return ERROR;
        }
    }

    @Override
    public AttachmentVO getModel() {
        return attachmentVO;
    }
}