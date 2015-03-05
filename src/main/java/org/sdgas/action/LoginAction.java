package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.UserVo;
import org.sdgas.model.User;
import org.sdgas.service.UserService;
import org.sdgas.util.SystemHelper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 120378 on 2015-03-05.
 */
@Component("login")
@Scope("prototype")
public class LoginAction extends MyActionSupport implements ModelDriven<UserVo> {

    private UserVo userVo = new UserVo();
    private UserService userService;

    private static String ip = "";

    private static final Logger logger = LogManager.getLogger(LoginAction.class);

    //用户登录
    @Override
    public String execute() throws Exception {
        if (userVo.getUserId().isEmpty() || userVo.getPwd().isEmpty()) {
            userVo.setResultMessage("<script>alert('请填写佛燃工号或密码！');location.href='login.jsp';</script>");
            return ERROR;
        }

        User user = userService.findById(userVo.getUserId());
        if (user.getUserId().isEmpty()) {
            userVo.setResultMessage("<script>alert('用户不存在！');location.href='login.jsp';</script>");
            return ERROR;
        }
        logger.entry(user.getUserName());

        if (userVo.getPwd().equals(user.getPwd())) {

            storePersonToSession(user);
            logger.info(user.getPosition().getPositionName() + " " + user.getUserName() + ",登录系统 IP:" + ip);
            HttpServletRequest request = ServletActionContext.getRequest();
            Object obj = request.getSession().getAttribute("requestURI");
            if (obj != null) {
                view = obj.toString();
            } else {
                view = "/index.jsp";
                return VIEW;
            }
            logger.trace(obj.toString());
            return VIEW;
        }
        logger.info("用户:" + user.getUserName() + "登录密码错误！");
        logger.exit(user.getUserName());
        userVo.setResultMessage("<script>alert('密码错误！');location.href='login.jsp';</script>");
        return ERROR;
    }

    //退出登录
    public String loginOut() throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession();
        User person = (User) session.getAttribute("person");
        if (person != null) {
            logger.info(person.getPosition().getPositionName() + " " + person.getUserName() + "，登出系统");
            session.removeAttribute("person");
            //session.invalidate();
        }
        view = "/login.jsp";
        return VIEW;
    }


    //修改密码
    public String alterPwd() {

        User user = userService.findById(userVo.getUserId());
        if (!user.getPwd().equals(userVo.getPwd())) {
            userVo.setResultMessage("<script>alert('原密码错误');location.href='/page/message/alterPwd.jsp';</script>");
            logger.info(user.getPosition().getPositionName() + ":" + user.getUserName() + " 修改用户密码，原密码错误）操作IP：" + ip);
            return ERROR;
        } else {
            user.setPwd(userVo.getPwd2());
            userService.update(user);
            userVo.setResultMessage("<script>alert('修改密码成功！请重新登录系统');location.href='login.jsp';</script>");
            logger.info(user.getPosition().getPositionName() + ":" + user.getUserName() + " 修改用户密码成功。操作IP：" + ip);
            return SUCCESS;
        }
    }

    @Override
    public UserVo getModel() {
        return userVo;
    }

    @Resource(name = "userServiceImpl")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private void storePersonToSession(User target) {
        HttpSession session = ServletActionContext.getRequest().getSession();
        User storedPerson = (User) session.getAttribute("person");
        ip = SystemHelper.getIpAddress();
        if (!ip.trim().equals("") && (storedPerson == null || !storedPerson.equals(target))) {
            session.setAttribute("person", target);
            session.setAttribute("ip", ip);
            session.setAttribute("loginKey", "success");
            session.setMaxInactiveInterval(-1);
        }
    }
}