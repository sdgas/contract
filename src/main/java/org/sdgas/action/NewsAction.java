package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.NewsVO;
import org.sdgas.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by wills_000 on 14-3-22.
 */
@Component("news")
@Scope("prototype")
public class NewsAction extends MyActionSupport implements ModelDriven<NewsVO> {

    private final NewsVO newsVO = new NewsVO();
    private final static Logger logger = LogManager.getLogger(NewsAction.class);
    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    User person = (User) session.getAttribute("person");

    public String showNews() {
        return SUCCESS;
    }

    @Override
    public NewsVO getModel() {
        return newsVO;
    }
}
