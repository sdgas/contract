package org.sdgas.service.impl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sdgas.service.PaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 120378 on 2015/8/19.
 */
public class PaymentServiceImplTest {

    private static ApplicationContext ac;
    private static PaymentService paymentService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try {
            //通过读取spring容器，给dao注入相应的实现类
            ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            paymentService = (PaymentService) ac.getBean("paymentServiceImpl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByContractId() throws Exception {

    }
}