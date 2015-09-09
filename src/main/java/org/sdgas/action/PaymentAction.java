package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.PaymentVO;
import org.sdgas.model.Contract;
import org.sdgas.model.Payment;
import org.sdgas.model.SettleAccount;
import org.sdgas.model.User;
import org.sdgas.service.ContractService;
import org.sdgas.service.PaymentService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by wilson.he on 2015/8/19.
 */
@Scope("prototype")
@Component("payment")
public class PaymentAction extends MyActionSupport implements ModelDriven<PaymentVO> {

    private PaymentVO paymentVO = new PaymentVO();

    private PaymentService paymentService;
    private ContractService contractService;

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    User user = (User) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    private static final Logger logger = LogManager.getLogger(PaymentAction.class);

    //添加付（收）款信息
    public String execute() {
        //添加款项信息
        if (!paymentVO.getPaymentDate().isEmpty() && !paymentVO.getPayMoney().isEmpty()) {
            Payment payment = paymentService.saveOrUpdatePay(paymentVO.getContract(), paymentVO.getPaymentDate(), paymentVO.getPayMoney(), paymentVO.getRemark());
            logger.info("用户：" + user.getUserName() + "添加付款信息成功（" + payment.getContract() + ")IP:" + ip);
        }

        //更新是否超合同结算
        if (!paymentVO.getSettleAccount().isEmpty()) {
            Contract contract = contractService.findContractById(paymentVO.getContract());
            switch (Integer.valueOf(paymentVO.getSettleAccount())) {
                case 0:
                    contract.setSettleAccount(SettleAccount.N);
                    break;
                case 1:
                    contract.setSettleAccount(SettleAccount.Y);
                    break;
                case 2:
                    contract.setSettleAccount(SettleAccount.Y_N);
                    break;
            }
            contractService.update(contract);
        }

        paymentVO.setResultMessage("<script>alert('添加付款信息成功！');location.href='Contract!findContract.action?flag=1';</script>");
        return SUCCESS;
    }

    @Override
    public PaymentVO getModel() {
        return paymentVO;
    }

    @Resource(name = "paymentServiceImpl")
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Resource(name = "contractServiceImpl")
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }
}
