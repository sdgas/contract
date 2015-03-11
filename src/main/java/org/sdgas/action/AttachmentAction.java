package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.AttachmentVO;
import org.sdgas.model.Attachment;
import org.sdgas.model.User;
import org.sdgas.service.AttachmentService;
import org.sdgas.util.ChangeTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by 120378 on 2015-03-10.
 */
@Component("attachment")
@Scope("prototype")
public class AttachmentAction extends MyActionSupport implements ModelDriven<AttachmentVO> {

    private AttachmentVO attachmentVO = new AttachmentVO();
    private AttachmentService attachmentService;

    private static final Logger logger = LogManager.getLogger(AttachmentAction.class);

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    User user = (User) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    private static String contractId = "";

    //添加附件(Uploadify)
    @Override
    public String execute() throws IOException {

        if (contractId.isEmpty()) {
            return ERROR;
        } else {

            String name = attachmentService.uploadAttachment(attachmentVO.getUploadify(), attachmentVO.getUploadifyFileName());
            Attachment attachment = new Attachment();
            attachment.setId(ChangeTime.formatShortDate(new Date()) + (int) (999 + Math.random() * 9999));
            attachment.setAttachmentName(name);
            attachment.setContract(contractId);
            attachmentService.save(attachment);
            logger.info("用户：" + user.getUserName() + "上传了一份附件（" + attachment.getId() + ")IP:" + ip);
            return SUCCESS;
        }
    }

    //AJAX获取合同编号
    public String contractIdGet() {
        contractId = ServletActionContext.getRequest().getParameter("contract");
        return SUCCESS;
    }

    @Resource(name = "attachmentServiceImpl")
    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @Override
    public AttachmentVO getModel() {
        return attachmentVO;
    }

}
