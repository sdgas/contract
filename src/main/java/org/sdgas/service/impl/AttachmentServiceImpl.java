package org.sdgas.service.impl;

import org.sdgas.VO.AttachmentVO;
import org.sdgas.base.DaoSupport;
import org.sdgas.model.Attachment;
import org.sdgas.service.AttachmentService;
import org.sdgas.util.ChangeTime;

import java.io.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 120378 on 2015-03-09.
 */

@Service
@Transactional
public class AttachmentServiceImpl extends DaoSupport<Attachment> implements AttachmentService {

    private static String SAVE_PATH_DIR = "D:/contract/attachment/";
    private Logger logger = LogManager.getLogger(AttachmentServiceImpl.class);

    @Override
    public List<Attachment> findByContractId(String contract) {
        /*todo:待完善*/
        return null;
    }

    @Override
    public String uploadAttachment(File attachment, String attachmentName) {
        // 得到保存上传文件的目录的真实路径
        File dir = new File(SAVE_PATH_DIR);
        // 如果该目录不存在，就创建
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String name = ChangeTime.formatDate() + attachmentName;
        try {
            FileInputStream is = new FileInputStream(attachment);
            FileOutputStream os = new FileOutputStream(new File(dir, name));
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
            }

            is.close();
            os.close();
        } catch (FileNotFoundException f) {
            logger.error(f);
            return "";
        } catch (IOException ioe) {
            logger.error(ioe);
            return "";
        }
        return name;
    }
}
