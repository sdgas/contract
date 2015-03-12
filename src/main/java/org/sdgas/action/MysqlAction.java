package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sdgas.VO.AttachmentVO;
import org.sdgas.util.ChangeTime;
import org.sdgas.util.MysqlOperation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;

@Component("mysql")
@Scope("prototype")
public class MysqlAction extends MyActionSupport implements ModelDriven<AttachmentVO> {
    private static MysqlOperation mo = new MysqlOperation();
    private final AttachmentVO attachmentVO = new AttachmentVO();
    private static String hostName = "localhost";
    private static String dataBase = "contract";
    private static String userName = "root";
    private static String password = "root";
    private static String SAVE_PATH_DIR = "D:/contract/_sql/";
    private static String mysqlBinUrl = "C:/Program Files/MySQL/MySQL Server 5.5/bin/";

    private final static Logger logger = LogManager.getLogger(MysqlAction.class);

    public String backupMysqlDatabase() {

        // 得到备份文件的目录的真实路径
        File dir = new File(SAVE_PATH_DIR);
        // 如果该目录不存在，就创建
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String date = ChangeTime.formatDate(ChangeTime.getCurrentDate());
        try {
            mo.backupMysqlDatabase(mysqlBinUrl, hostName, dataBase, userName,
                    password, SAVE_PATH_DIR + date + ".sql");
        } catch (Exception e) {
            attachmentVO.setResultMessage("系统备份失败，请查看后台属性设置");
            logger.error(e);
            return ERROR;
        }
        attachmentVO.setResultMessage("系统成功备份数据库");
        logger.trace("the database backup success!");
        return SUCCESS;
    }

    public String resumeMysqlDatabase() {
        if (attachmentVO.getDbName().isEmpty()) {
            attachmentVO.setResultMessage("<script>alert('请选择文件！');location.href='/page/file/db.jsp';</script>");
            return ERROR;
        }
        // 得到备份文件的目录的真实路径
        File dir = new File(SAVE_PATH_DIR);
        // 如果该目录不存在，就创建
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            mo.resumeMysqlDatabase(mysqlBinUrl, hostName, dataBase, userName,
                    password, attachmentVO.getDbName());
        } catch (Exception e) {
            attachmentVO.setResultMessage("系统还原失败，请查看后台属性设置");
            logger.error(e);
            return ERROR;
        }
        attachmentVO.setResultMessage("系统成功还原数据库");
        logger.trace("the database resume success!");
        return SUCCESS;
    }

    @Override
    public AttachmentVO getModel() {
        return attachmentVO;
    }
}
