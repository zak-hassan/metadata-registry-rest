package com.redhat.data.analytics.repo;

import com.redhat.data.analytics.model.SinkDataStream;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by zhassan on 2016-10-25.
 */
@ApplicationScoped
public class SinkDataStreamService {


    @PersistenceContext
    private EntityManager em;

    public SinkDataStreamService() {
    }

    public void delete(SinkDataStream item) {
        em.remove(item);
    }

    public SinkDataStream get(String id) {
        SinkDataStream item = em.find(SinkDataStream.class, id);
        return item;
    }

    public List<SinkDataStream> getAll() {
        List<SinkDataStream> resultList = em.createQuery("select p from SinkDataStream p", SinkDataStream.class).getResultList();
        return resultList;
    }

    public SinkDataStream update(SinkDataStream item) {
        return em.merge(item);

    }

    public SinkDataStream add(SinkDataStream item) {
        em.persist(item);
        return item;
    }


}
