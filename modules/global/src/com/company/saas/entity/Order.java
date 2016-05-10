/*
 * Copyright (c) 2016 saas
 */
package com.company.saas.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author krivopustov
 */
@Table(name = "SAAS_ORDER")
@Entity(name = "saas$Order")
public class Order extends StandardClientEntity {
    private static final long serialVersionUID = -5047025345571272744L;

    @Column(name = "NUM", nullable = false)
    protected String num;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_")
    protected Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    protected Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_METHOD_ID")
    protected PaymentMethod paymentMethod;

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }


    public void setNum(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }


}