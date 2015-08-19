package org.sdgas.service.impl;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sdgas.model.Attachment;
import org.sdgas.service.AttachmentService;
import org.sdgas.service.PaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 120378 on 2015/8/19.
 */
public class AttachmentServiceImplTest {

    private static ApplicationContext ac;
    private static AttachmentService attachmentService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try {
            //通过读取spring容器，给dao注入相应的实现类
            ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            attachmentService = (AttachmentService) ac.getBean("attachmentServiceImpl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByContractId() throws Exception {
        List<Attachment> attachments = attachmentService.findByContractId("PC15-STD-1423");
        Assert.assertEquals(0,1);
    }
}