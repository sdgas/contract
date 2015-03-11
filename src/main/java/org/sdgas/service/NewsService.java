package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.News;

/**
 * Created by wills_000 on 14-3-22.
 */

public interface NewsService extends DAO {

    public News findNews(int user);

    public News createNews(Object send, Object receive, String message, String link);
}
