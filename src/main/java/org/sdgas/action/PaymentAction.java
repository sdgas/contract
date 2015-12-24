package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.PaymentVO;
import org.sdgas.base.PageView;
import org.sdgas.model.*;
import org.sdgas.service.ContractService;
import org.sdgas.service.PaymentService;
import org.sdgas.util.UserUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
        if (paymentVO.getSettleAccount() != null && !paymentVO.getSettleAccount().isEmpty()) {
            try {
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
                contract.setState(ContractState.PAY);
                contractService.update(contract);
            } catch (Exception e) {
                e.printStackTrace();
                paymentVO.setResultMessage("系统故障请与管理员联系！");
                return ERROR;
            }
        }
        paymentVO.setResultMessage("<script>alert('添加付款信息成功！');location.href='Contract!findContract.action?flag=1';</script>");
        return SUCCESS;
    }

    public String closeContract() {
        if (UserUtil.checkUserLogIn(user)) {
            paymentVO.setResultMessage("<script>alert('请登录！');location.href='/login.jsp';</script>");
            return ERROR;
        }
        /** 每页显示的结果数 **/
        int maxResult = 20;
        /** 封装的页面数据 **/
        PageView<Payment> pageView = new PageView<Payment>(maxResult,
                paymentVO.getPage());
        int firstIndex = ((pageView.getCurrentPage() - 1) * pageView
                .getMaxResult());// 开始索引

        /** 列表条件 **/
        StringBuffer jpql = new StringBuffer("leftMoney=0 and contract.state=4");

        /** 列表条件的值 **/
        List<Object> params = new ArrayList<Object>();
        pageView.setQueryResult(contractService.getScrollData(Payment.class, firstIndex, maxResult, jpql.toString(),
                params.toArray()));
        paymentVO.setPageView(pageView);

        view = "/page/contract/closeContract.jsp";
        return VIEW;
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
