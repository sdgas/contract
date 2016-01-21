package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.FileVO;
import org.sdgas.model.*;
import org.sdgas.service.*;
import org.sdgas.util.ChangeTime;
import org.sdgas.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component("file")
@Scope("prototype")
public class FileAction extends MyActionSupport implements ModelDriven<FileVO> {

    @Qualifier("excelUtil")
    @Autowired
    private ExcelUtil excelUtil;
    private ContractService contractService;

    private final static Logger logger = LogManager.getLogger(FileAction.class);
    private final FileVO fileVO = new FileVO();
    private List<Object> obj = new ArrayList<Object>();
    private List<Contract> contracts = new ArrayList<Contract>();
    private int count = 0;
    private int num = 0;
    private static String SAVE_PATH_DIR = "D:/contract/downloadFile/";

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    User user = (User) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    //归档合同明细
    public String createContract() throws UnsupportedEncodingException {
        // 得到备份文件的目录的真实路径
        File dir = new File(SAVE_PATH_DIR);
        // 如果该目录不存在，就创建
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String date = ChangeTime.formatDate(ChangeTime.getCurrentDate());
        String fileName = date + ".xlsx";
        String path = SAVE_PATH_DIR + fileName;
        //使用于07以上的版本，03以下的可以修改参数
        String time = fileVO.getTime().replace("月","").replace("年","-");
        try {
            excelUtil.createExcel(time, path);
        } catch (Exception e) {
            if (e.getMessage().contains("数据异常"))
                fileVO.setResultMessage("导出数据异常，请与管理员联系");
            logger.error(e);
            return ERROR;
        }

        logger.info("用户：" + user.getUserId() + "成功生成归档合同明细文件！文件名为:" + fileName);
        fileVO.setResultMessage("<script>alert('成功生成归档合同明细文件:" + fileName + "。请点击确认下载');location.href='FileDownload.action?flag=99&path=" + fileName + "';</script>");
        return SUCCESS;
    }

    //未归档合同明细
    public String createContractNotClose() throws UnsupportedEncodingException {
        // 得到备份文件的目录的真实路径
        File dir = new File(SAVE_PATH_DIR);
        // 如果该目录不存在，就创建
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String date = ChangeTime.formatDate(ChangeTime.getCurrentDate());
        String fileName = date + ".xlsx";
        String path = SAVE_PATH_DIR + fileName;
        //使用于07以上的版本，03以下的可以修改参数
        try {
            excelUtil.createExcelV2(path);
        } catch (Exception e) {
            if (e.getMessage().contains("数据异常"))
                fileVO.setResultMessage("导出数据异常，请与管理员联系");
            logger.error(e);
            return ERROR;
        }

        logger.info("用户：" + user.getUserId() + "成功生成未归档合同明细文件！文件名为:" + fileName);
        fileVO.setResultMessage("<script>alert('成功生成未归档合同明细文件:" + fileName + "。请点击确认下载');location.href='FileDownload.action?flag=99&path=" + fileName + "';</script>");
        return SUCCESS;
    }

    @Resource(name = "contractServiceImpl")
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }

    @Override
    public FileVO getModel() {
        return fileVO;
    }

    public String uploadAttachment(File file, String fileName, String path) {
        // 得到保存上传文件的目录的真实路径
        File dir = new File(path);
        // 如果该目录不存在，就创建
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String[] temp = fileName.split("\\\\");
        fileName = temp[temp.length - 1];
        String name = ChangeTime.formatDate() + fileName;
        try {
            FileInputStream is = new FileInputStream(file);
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
        } catch (IOException ioe) {
            logger.error(ioe);
        }
        return name;
    }

}
