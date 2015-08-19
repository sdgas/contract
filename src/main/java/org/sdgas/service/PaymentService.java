package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.Payment;

import java.util.List;

/**
 * Created by 120378 on 2015/8/19.
 */
public interface PaymentService extends DAO {

    public List<Payment> findByContractId(String contractId);
}
