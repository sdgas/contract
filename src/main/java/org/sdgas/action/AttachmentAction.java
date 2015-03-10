package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.AttachmentVO;
import org.sdgas.model.Attachment;
import org.sdgas.model.User;
import org.sdgas.service.AttachmentService;
import org.sdgas.service.ContractService;
import org.sdgas.util.ChangeTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 120378 on 2015-03-10.
 */
@Component("attachment")
@Scope("prototype")
public class AttachmentAction extends MyActionSupport implements ModelDriven<AttachmentVO> {

    private AttachmentVO attachmentVO = new AttachmentVO();
    private AttachmentService attachmentService;
    private ContractService contractService;

    private static final Logger logger = LogManager.getLogger(AttachmentAction.class);

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    User user = (User) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    private static String contractId = "";


    //添加附件
    @Override
    public String execute() throws IOException {
        System.out.println("upload:" + contractId);
        if (contractId.isEmpty()) {
            return ERROR;
        } else {
            for (int i = 0; i < 1; i++) {
                String name = attachmentService.uploadAttachment(attachmentVO.getUploadify(), attachmentVO.getUploadifyFileName());
                Attachment attachment = new Attachment();
                attachment.setId(ChangeTime.formatDate());
                attachment.setAttachmentName(name);
                attachment.setContract(contractId);
                attachmentService.save(attachment);
                logger.info("用户：" + user.getUserName() + "上传了一份附件（" + attachment.getId() + ")IP:" + ip);
            }
            return SUCCESS;
        }
    }

    public String contractIdGet() {
        contractId = ServletActionContext.getRequest().getParameter("contract");
        System.out.println("AJAX:" + contractId);
        return SUCCESS;
    }

    @Resource(name = "attachmentServiceImpl")
    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @Resource(name = "contractServiceImpl")
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }

    @Override
    public AttachmentVO getModel() {
        return attachmentVO;
    }

}
