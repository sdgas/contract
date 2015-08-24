package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.sdgas.VO.AuditingVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by 120378 on 2015/8/24.
 */

@Scope("prototype")
@Component("auditing")
public class AuditingAction extends MyActionSupport implements ModelDriven<AuditingVO>{

    private AuditingVO auditingVO = new AuditingVO();

    @Override
    public AuditingVO getModel() {
        return auditingVO;
    }
}
