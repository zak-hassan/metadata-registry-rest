package com.redhat.data.analytics.repo;

import com.redhat.data.analytics.model.SourceDataStream;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by zhassan on 2016-10-25.
 */
@ApplicationScoped
public class SourceDataStreamService {


    @PersistenceContext
    private EntityManager em;

    public SourceDataStreamService() {
    }

    public void delete(SourceDataStream item) {
        em.remove(item);
    }

    public SourceDataStream get(String id) {
        SourceDataStream item = em.find(SourceDataStream.class, id);
        return item;
    }

    public List<SourceDataStream> getAll() {
        List<SourceDataStream> resultList = em.createQuery("select p from SourceDataStream p", SourceDataStream.class).getResultList();
        return resultList;
    }

    public SourceDataStream update(SourceDataStream item) {
        return em.merge(item);

    }

    public SourceDataStream add(SourceDataStream item) {
        em.persist(item);
        return item;
    }

}
