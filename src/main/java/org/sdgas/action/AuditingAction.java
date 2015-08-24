package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.AuditingVO;
import org.sdgas.model.Auditing;
import org.sdgas.model.Contract;
import org.sdgas.model.ContractState;
import org.sdgas.model.User;
import org.sdgas.service.AuditingService;
import org.sdgas.service.ContractService;
import org.sdgas.util.ChangeTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by 120378 on 2015/8/24.
 */

@Scope("prototype")
@Component("auditing")
public class AuditingAction extends MyActionSupport implements ModelDriven<AuditingVO> {

    private AuditingVO auditingVO = new AuditingVO();

    private AuditingService auditingService;
    private ContractService contractService;

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    User user = (User) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    private static final Logger logger = LogManager.getLogger(AuditingAction.class);

    //添加审核信息信息
    public String execute() {
        Auditing auditing = new Auditing();
        auditing.setContract(contractService.findContractById(auditingVO.getContract()));
        auditing.setRemark(auditingVO.getRemark());
        auditing.setState(auditingVO.getState());
        auditing.setAudDate(ChangeTime.parseDate(null));
        auditingService.save(auditing);
        if (auditing.getState().equals("审核通过")) {
            Contract contract = contractService.findContractById(auditingVO.getContract());
            contract.setState(ContractState.PAY);
            contractService.update(contract);
        }

        logger.info("用户：" + user.getUserName() + "添加审核信息成功（" + auditing.getContract() + ")IP:" + ip);
        auditingVO.setResultMessage("<script>alert('添加审核信息成功！');location.href='Contract!findContract.action?flag=3';</script>");
        return SUCCESS;
    }

    @Override
    public AuditingVO getModel() {
        return auditingVO;
    }

    @Resource(name = "auditingServiceImpl")
    public void setAuditingService(AuditingService auditingService) {
        this.auditingService = auditingService;
    }

    @Resource(name = "contractServiceImpl")
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }
}
