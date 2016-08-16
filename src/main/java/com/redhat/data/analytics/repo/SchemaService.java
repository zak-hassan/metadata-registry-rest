package com.redhat.data.analytics.repo;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.redhat.data.analytics.model.Schema;

@ApplicationScoped
public class SchemaService {

  @PersistenceContext
  private EntityManager em;

  public SchemaService() {

  }

  public void delete(Schema schema) {
    em.remove(schema);
  }

  public Schema get(String id) {
    Schema item = em.find(Schema.class, id);
    return item;
  }

  public List<Schema> getAll() {
    List<Schema> resultList = em.createQuery("select p from Schema p", Schema.class).getResultList();
    return resultList;
  }

  public Schema update(Schema item) {
    return em.merge(item);

  }

  public Schema add(Schema item) {
    em.persist(item);
    return item;
  }
}
