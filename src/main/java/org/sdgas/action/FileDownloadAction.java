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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component("FileDownload")
@Scope("prototype")
public class FileDownloadAction extends ActionSupport implements ModelDriven<AttachmentVO> {

    private final AttachmentVO attachmentVO = new AttachmentVO();
    private Logger logger = LogManager.getLogger(FileDownloadAction.class);
    private final static String SAVE_PATH_DIR = "D:/contract/attachment/";

    //返回一个输入流，作为一个客户端来说是一个输入流，但对于服务器端是一个 输出流
    public InputStream getDownloadFile() throws Exception {
        String filePath;
        File file = null;
        if (attachmentVO.getFlag() == 1) {
            filePath = SAVE_PATH_DIR + attachmentVO.getFileName();
            file = new File(filePath);
            attachmentVO.setFileName(WebTool.changeCharset(attachmentVO.getFileName(), "ISO-8859-1"));
        } else if (attachmentVO.getFlag() == 99) {
            filePath = SAVE_PATH_DIR + attachmentVO.getPath();
            attachmentVO.setFileName(ChangeCharset.toUTF_8(attachmentVO.getPath()));
            file = new File(ChangeCharset.toUTF_8(filePath));
            return new FileInputStream(file);
        } else {
            filePath = SAVE_PATH_DIR + "/policy/ContractManagementProcess.pdf";
            file = new File(filePath);
            attachmentVO.setFileName("ContractManagementProcess.pdf");
        }
        return new FileInputStream(file);

    }

    //文件下载
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Override
    public AttachmentVO getModel() {
        return attachmentVO;
    }
}