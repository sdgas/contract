package org.sdgas.service.impl;

import org.sdgas.service.NewsService;
import org.sdgas.service.UserService;
import org.sdgas.base.DaoSupport;
import org.sdgas.model.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wills_000 on 14-3-22.
 */
@Service
@Transactional
public class NewsServiceImpl extends DaoSupport<News> implements NewsService {

    private UserService userService;

    @Override
    public News findNews(int userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("receiver", userService.find(User.class, userId));
        params.put("newsState", NewsState.NOT_DEAL);
        return (News) this.findSpecialObject(News.class, params);
    }

    /*private User objToUser(Object user) {
        if (user instanceof User) {
            return (User) user;
        } else if (user instanceof Student) {
            return userService.findByDormitory(((Student) user).getDormitory());
        } else
            return null;
    }*/

    @Override
    public News createNews(Object send, Object receive, String message, String link) {
        /*User sender = objToUser(send);
        User receiver = objToUser(receive);

        News news = new News();
        news.setSender(sender);
        news.setReceiver(receiver);
        news.setContent(message);
        news.setLink(link);
        news.setNewsState(NewsState.NOT_DEAL);
        news.setSendTime(new Date());
        return news;*/
        return new News();
    }

    @Resource(name = "userServiceImpl")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
