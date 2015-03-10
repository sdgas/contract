package org.sdgas.service;

import org.sdgas.VO.AttachmentVO;
import org.sdgas.base.DAO;
import org.sdgas.model.Attachment;

import java.io.File;
import java.util.List;

/**
 * Created by 120378 on 2015-03-09.
 */
public interface AttachmentService extends DAO {

    /**
     * 查找合同对应的附件
     *
     * @param contract 合同编号
     * @return 附件列表
     */
    public List<Attachment> findByContractId(String contract);

    /**
     * 上传附件
     *
     * @param attachment     文件信息
     * @param attachmentName 文件名
     * @return 文件名
     */
    public String uploadAttachment(File attachment, String attachmentName);
}
