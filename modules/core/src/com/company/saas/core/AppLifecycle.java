/*
 * Copyright (c) 2016 saas
 */

package com.company.saas.core;

import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.core.sys.listener.EntityListenerManager;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 *
 */
@Component("saas_AppLifecycle")
public class AppLifecycle implements AppContext.Listener {

    @Inject
    private EntityListenerManager entityListenerManager;

    public AppLifecycle() {
        AppContext.addListener(this);
    }

    @Override
    public void applicationStarted() {
        entityListenerManager.addListener(User.class, "saas_UserEntityListener");
    }

    @Override
    public void applicationStopped() {
    }
}
