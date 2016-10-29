package com.redhat.data.analytics.repo;

import com.redhat.data.analytics.model.DataStream;
import com.redhat.data.analytics.model.Schema;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhassan on 2016-10-26.
 */
@ApplicationScoped
public class DataStreamService {


    @PersistenceContext
    private EntityManager em;

    public DataStreamService() {
    }

    public void delete(DataStream schema) {
        em.remove(schema);
    }

    public DataStream get(String id) {
        DataStream item = em.find(DataStream.class, id);
        return item;
    }

    public List<DataStream> getAll() {
        List<DataStream> resultList = em.createQuery("select p from DataStream p", DataStream.class).getResultList();
        return resultList;
    }

    public DataStream update(DataStream item) {
        return em.merge(item);

    }

    public DataStream add(DataStream item) {
        em.persist(item);
        return item;
    }


}
