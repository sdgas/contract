package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.ContractVO;
import org.sdgas.base.PageView;
import org.sdgas.model.*;
import org.sdgas.service.*;
import org.sdgas.util.ChangeTime;
import org.sdgas.util.UserUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by 120378 on 2015-03-06.
 */

@Scope("prototype")
@Component("contract")
public class ContractAction extends MyActionSupport implements ModelDriven<ContractVO> {

    private ContractVO contractVO = new ContractVO();
    private ContractService contractService;
    private ContractNameService contractNameService;
    private ContractTypeService contractTypeService;
    private DepartmentService departmentService;

    private static final Logger logger = LogManager.getLogger(ContractAction.class);

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    User user = (User) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    //新增合同
    @Override
    public String execute() {
        Contract contract = new Contract();
        contract.setContractId(contractVO.getContractId());
        contract.setContractName(contractNameService.findById(Integer.valueOf(contractVO.getContractName())));
        contract.setContractMoney(Double.valueOf(contractVO.getContractMoney()));
        contract.setContractSignCompany(contractVO.getContractSignCompany());
        contract.setPerformanceBond(Double.valueOf(contractVO.getPerformanceBond()));
        contract.setContractType(contractTypeService.findTypeById(Integer.valueOf(contractVO.getContractType())));
        contract.setContractBeginDate(ChangeTime.parseStringToShortDate(contractVO.getContractBeginDate()));
        contract.setSignContractDate(new Date());
        contract.setInvoice(Integer.valueOf(contractVO.getInvoice()));
        contract.setSupportFile(contractVO.getSupportFile());
        contract.setStampTax(Double.valueOf(contractVO.getStampTax()));
        contract.setVersion(Integer.valueOf(contractVO.getVersion()));
        contract.setStamp(Integer.valueOf(contractVO.getStamp()));
        contract.setUnit_price(Double.valueOf(contractVO.getUnit_price()));
        contract.setContractEndDate(ChangeTime.parseStringToShortDate(contractVO.getContractEndDate()));
        contract.setContractOperator(contractVO.getContractOperator());
        contract.setCount(Integer.valueOf(contractVO.getCount()));
        if (contractVO.getBudget() != null) {
            switch (Integer.valueOf(contractVO.getBudget())) {
                case 0:
                    contract.setBudget(Budget.IN);
                    break;
                case 1:
                    contract.setBudget(Budget.OUT);
                    break;
                default:
                    break;
            }
            contract.setBudgetType(contractVO.getBudgetType());
            contract.setBudgetMoney(Double.valueOf(contractVO.getBudgetMoney()));
        }

        if (!contractVO.getDepartment().isEmpty())
            contract.setDepartment(departmentService.findDepartmentById(Integer.valueOf(contractVO.getDepartment())));

        if (contractVO.getContractProperty() != null) {
            switch (Integer.valueOf(contractVO.getContractProperty())) {
                case 0:
                    contract.setContractProperty(ContractProperty.NEW);
                    break;
                case 1:
                    contract.setContractProperty(ContractProperty.RENEW);
                    break;
                case 2:
                    contract.setContractProperty(ContractProperty.CHANGE);
                    break;
                default:
                    break;
            }
        }

        if (!contractVO.getBiddingType().isEmpty()) {

            switch (Integer.valueOf(contractVO.getBiddingType())) {
                case 0:
                    contract.setBiddingType(BiddingType.OPEN);
                    break;
                case 1:
                    contract.setBiddingType(BiddingType.LAW_INVITE);
                    break;
                case 2:
                    contract.setBiddingType(BiddingType.IN_INVITE);
                    break;
                case 3:
                    contract.setBiddingType(BiddingType.DIRECT);
                    break;
                case 4:
                    contract.setBiddingType(BiddingType.COMPARE);
                    break;
                case 5:
                    contract.setBiddingType(BiddingType.OTHERS);
                    break;
                default:
                    break;
            }
        }

        contract.setMainContent(contractVO.getMainContent());
        contract.setRemark("款项类型：" + contractVO.getMoneyRemark().trim() + "     " + contractVO.getRemark());

        switch (Integer.valueOf(contractVO.getGuaranteePeriod())) {
            case 0:
                contract.setGuaranteePeriod(GuaranteePeriod.HALF);
                break;
            case 1:
                contract.setGuaranteePeriod(GuaranteePeriod.ONE);
                break;
            case 2:
                contract.setGuaranteePeriod(GuaranteePeriod.TWO);
                break;
            case 3:
                contract.setGuaranteePeriod(GuaranteePeriod.THREE);
                break;
            case 4:
                contract.setGuaranteePeriod(GuaranteePeriod.FOUR);
                break;
            case 5:
                contract.setGuaranteePeriod(GuaranteePeriod.FIVE);
                break;
            case 6:
                contract.setGuaranteePeriod(GuaranteePeriod.SIX);
                break;
            case 7:
                contract.setGuaranteePeriod(GuaranteePeriod.SEVEN);
                break;
            case 8:
                contract.setGuaranteePeriod(GuaranteePeriod.EIGHT);
                break;
            case 9:
                contract.setGuaranteePeriod(GuaranteePeriod.NINE);
                break;
            case 10:
                contract.setGuaranteePeriod(GuaranteePeriod.TEN);
                break;
            case 11:
                contract.setGuaranteePeriod(GuaranteePeriod.LIFELONG);
                break;
            case 12:
                contract.setGuaranteePeriod(GuaranteePeriod.NO);
                break;
        }

        if ("0".equals(contractVO.getReceiveOrPay()))
            contract.setReceivableOrPay(ReceiveOrPay.PAY);
        else
            contract.setReceivableOrPay(ReceiveOrPay.RECEIVE);
        if (!contractVO.getClosingMoney().isEmpty())
            contract.setClosingMoney(Double.valueOf(contractVO.getClosingMoney()));
        contract.setReceivableOrPayMoney(Double.valueOf(contractVO.getReceivableOrPayMoney()));
        switch (Integer.valueOf(contractVO.getSettleAccount())) {
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

        contractService.save(contract);
        logger.info("用户：" + user.getUserName() + "添加了一份合同（" + contract.getContractId() + ")IP:" + ip);
        contractVO.setResultMessage("<script>alert('添加成功！');location.href='page/contract/addContract.jsp';</script>");
        return SUCCESS;
    }

    //查找用户的合同
    public String findContract() {
        if (UserUtil.checkUserLogIn(user)) {
            contractVO.setResultMessage("<script>alert('请登录！');location.href='login.jsp';</script>");
            return ERROR;
        }
        /** 每页显示的结果数 **/
        int maxResult = 10;
        /** 封装的页面数据 **/
        PageView<Contract> pageView = new PageView<Contract>(maxResult,
                contractVO.getPage());
        int firstIndex = ((pageView.getCurrentPage() - 1) * pageView
                .getMaxResult());// 开始索引

        // 按照条件排序
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        orderBy.put("signContractDate", "DESC");
        /** 列表条件 **/
        StringBuffer jpql = new StringBuffer("1=1");
        jpql.append("and department = " + user.getDepartment().getDepartmentId());

        /** 列表条件的值 **/
        List<Object> params = new ArrayList<Object>();
        pageView.setQueryResult(contractService.getScrollData(Contract.class, firstIndex, maxResult, jpql.toString(),
                params.toArray(), orderBy));
        contractVO.setPageView(pageView);
        view = "/page/contract/findContract.jsp";
        return VIEW;
    }

    @Override
    public ContractVO getModel() {
        return contractVO;
    }

    @Resource(name = "contractServiceImpl")
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }

    @Resource(name = "contractNameServiceImpl")
    public void setContractNameService(ContractNameService contractNameService) {
        this.contractNameService = contractNameService;
    }

    @Resource(name = "contractTypeServiceImpl")
    public void setContractTypeService(ContractTypeService contractTypeService) {
        this.contractTypeService = contractTypeService;
    }

    @Resource(name = "departmentServiceImpl")
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
