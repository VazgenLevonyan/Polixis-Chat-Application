package com.polixis.repository;

import com.polixis.model.UsersGroup;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsersGroupRepository implements PanacheRepository<UsersGroup> {
    public  UsersGroup findByName(String name) {
        return find("name", name).firstResult();
    }
}
