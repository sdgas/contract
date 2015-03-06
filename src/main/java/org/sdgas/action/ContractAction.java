package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.sdgas.VO.ContractVO;
import org.sdgas.service.ContractService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by 120378 on 2015-03-06.
 */

@Scope("prototype")
@Component("contract")
public class ContractAction extends MyActionSupport implements ModelDriven<ContractVO> {

    private ContractVO contractVO = new ContractVO();
    private ContractService contractService;

    //新增合同
    @Override
    public String execute() {
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
}
