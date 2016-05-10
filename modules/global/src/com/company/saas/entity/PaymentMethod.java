/*
 * Copyright (c) 2016 saas
 */
package com.company.saas.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

/**
 * @author krivopustov
 */
@NamePattern("%s|name")
@Table(name = "SAAS_PAYMENT_METHOD")
@Entity(name = "saas$PaymentMethod")
public class PaymentMethod extends StandardEntity {
    private static final long serialVersionUID = -3127116587002616383L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "DESCRIPTION")
    protected String description;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}