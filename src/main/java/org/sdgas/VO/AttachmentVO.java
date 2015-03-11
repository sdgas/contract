package org.sdgas.VO;

import java.io.File;
import java.util.List;

/**
 * Created by 120378 on 2015-03-10.
 */
public class AttachmentVO extends BaseVO {

    private String contractId;
    private File uploadify;
    private String uploadifyFileName;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public File getUploadify() {
        return uploadify;
    }

    public void setUploadify(File uploadify) {
        this.uploadify = uploadify;
    }

    public String getUploadifyFileName() {
        return uploadifyFileName;
    }

    public void setUploadifyFileName(String uploadifyFileName) {
        this.uploadifyFileName = uploadifyFileName;
    }
}
