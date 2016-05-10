/*
 * Copyright (c) 2016 saas
 */
package com.company.saas.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.chile.core.annotations.NamePattern;

/**
 * @author krivopustov
 */
@NamePattern("%s|name")
@Table(name = "SAAS_CUSTOMER")
@Entity(name = "saas$Customer")
public class Customer extends StandardClientEntity {
    private static final long serialVersionUID = 5675695863386759857L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}