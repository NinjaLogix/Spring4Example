package com.logix.utils;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author bboyington
 * @version ${version}
 */
@Repository
public class HibernateUtil { //TODO - research why the <T> annotations were used here, maybe this is generics?

    @Autowired
    private SessionFactory sessionFactory;

    public <T> Serializable create(final T entity){
        return sessionFactory.getCurrentSession().save(entity);
    }

    public <T> T update(final T entity){
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    public <T> void delete(final T entity){
        sessionFactory.getCurrentSession().delete(entity);
    }

    public <T> void delete(Serializable id, Class<T> entityClass){
        T entity = fetchById(id, entityClass);
        delete(entity);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> fetchAll(Class<T> entityClass){
        return sessionFactory.getCurrentSession().createQuery(" FROM "+entityClass.getName()).list();
    }

    //@SuppressWarnings("rawtypes")
    @SuppressWarnings("unchecked")
    public <T> List<T> fetchAll(String query){
        return sessionFactory.getCurrentSession().createSQLQuery(query).list();
        //Query query = sessionFactory.getCurrentSession()
        //.getNamedQuery("Customer.fetchByName")
        //.setParameter("custName", name); //TODO - look into difference between .setString here
        //return query.list();
    }

    @SuppressWarnings("unchecked")
    public <T> T fetchById(Serializable id, Class<T> entityClass){
        return (T)sessionFactory.getCurrentSession().get(entityClass, id);
    }
}
