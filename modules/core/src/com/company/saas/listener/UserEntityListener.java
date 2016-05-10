package com.company.saas.listener;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.entity.UserRole;
import org.springframework.stereotype.Component;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.util.List;

@Component("saas_UserEntityListener")
public class UserEntityListener implements BeforeInsertEntityListener<User> {

    @Inject
    private Persistence persistence;

    @Inject
    private Metadata metadata;

    @Override
    public void onBeforeInsert(User entity) {
        System.out.println("saas_UserEntityListener.onBeforeInsert");

        TypedQuery<Role> query = persistence.getEntityManager().createQuery("select r from sec$Role r where r.defaultRole = true", Role.class);
        List<Role> defaultRoles = query.getResultList();

        for (Role defaultRole : defaultRoles) {
            boolean notAssigned = entity.getUserRoles().stream()
                    .map(UserRole::getRole)
                    .noneMatch(role -> role.equals(defaultRole));
            if (notAssigned) {
                UserRole userRole = metadata.create(UserRole.class);
                userRole.setRole(defaultRole);
                userRole.setUser(entity);
                persistence.getEntityManager().persist(userRole);
            }
        }
    }
}