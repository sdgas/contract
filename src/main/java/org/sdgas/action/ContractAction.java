package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.ContractVO;
import org.sdgas.model.*;
import org.sdgas.service.*;
import org.sdgas.util.ChangeTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

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
        contract.setPerformanceBond(Double.valueOf(contractVO.getPerformanceBond()));
        contract.setContractType(contractTypeService.findTypeById(Integer.valueOf(contractVO.getContractType())));
        contract.setContractBeginDate(ChangeTime.parseStringToShortDate(contractVO.getContractBeginDate()));
        contract.setSignContractDate(new Date());
        contract.setContractEndDate(ChangeTime.parseStringToShortDate(contractVO.getContractEndDate()));
        contract.setContractOperator(contractVO.getContractOperator());
        contract.setCount(Integer.valueOf(contractVO.getCount()));
        contract.setContractSignCompany(contractVO.getContractSignCompany());

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

        if (contractVO.getBiddingType().equals("")) {
            System.out.println("bt:" + contractVO.getBiddingType());
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
                    contract.setBiddingType(BiddingType.OTHERS);
                    break;
                default:
                    break;
            }
        }

        contract.setMainContent(contractVO.getMainContent());
        contract.setRemark(contractVO.getRemark());

        contractService.save(contract);
        logger.info("用户：" + user.getUserName() + "添加了一份合同（" + contract.getContractId() + ")IP:" + ip);
        contractVO.setResultMessage("<script>alert('添加成功！');location.href='page/contract/addContract.jsp';</script>");
        return SUCCESS;
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
