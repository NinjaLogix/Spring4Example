package com.logix.utils;

import java.util.List;

import com.logix.model.Customer;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author bboyington
 * @version ${version}
 * @since 3.1.0
 */
@Repository
public class DataUtil {

    @Autowired
    private SessionFactory sessionFactory;

    /*
     * These methods will remain with generics:
     *      - void create
     *      - T update
     *      - void delete
     * For this example it's a bit overkill, but for a project with multiple hibernate objects
     * it's very usefull to be able to re-use the same basic crud operation classes on multiple objects.
     */

    public <T> void create(final T entity){
        sessionFactory.getCurrentSession().save(entity);
    }

    public <T> T update(final T entity){
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    public <T> void delete(final T entity){
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void delete(int id){
        Customer entity = fetchById(id);
        //for (Customer cust:entity){
            delete(entity);
        //}
    }

    @SuppressWarnings("unchecked")
    public List<Customer> fetchAll(){
        Session session = sessionFactory.getCurrentSession();
        Query qry_fetchAll = session.getNamedQuery("Customer.findAll");
        List<Customer> custList = qry_fetchAll.list();
        return custList;
    }

    @SuppressWarnings("unchecked")
    public List<Customer> fetchAll(String query){
        return sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }

    @SuppressWarnings("unchecked")
    public List<Customer> fetchByName(String name){
        Session session = sessionFactory.getCurrentSession();

        Query qry_byCustName = session.getNamedQuery("Customer.findByName");
        qry_byCustName.setParameter("name", name);
        List<Customer> custList = qry_byCustName.list();

        return custList;
    }

    @SuppressWarnings("unchecked")
    public Customer fetchById(int id){
        Session session = sessionFactory.getCurrentSession();
        try{
            Query qry_byId = session.getNamedQuery("Customer.findById");
            qry_byId.setParameter("id", id);
            Customer custObj = (Customer) qry_byId.uniqueResult();

            return custObj;
        }catch (NonUniqueObjectException n){
            return null;
        }
    }
}
