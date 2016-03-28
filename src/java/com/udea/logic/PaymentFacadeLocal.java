/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.logic;

import com.udea.model.Payment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Daego_000
 */
@Local
public interface PaymentFacadeLocal {

    void create(Payment payment);

    void edit(Payment payment);

    void remove(Payment payment);

    Payment find(Object id);

    List<Payment> findAll();

    List<Payment> findRange(int[] range);

    int count();
    
}
