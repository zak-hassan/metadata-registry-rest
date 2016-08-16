package com.redhat.data.analytics.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * Schema Registry store
 * 
 * @author Zak Hassan <zak.hassan1010@redhat.com>
 */
@Entity
//@Indexed
public class Schema {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  String schemaId;



  @Override
  public String toString() {
    return "Schema [schemaId=" + schemaId + ", type=" + type + ", name=" + name + ", namespace="
        + namespace + ", doc=" + doc + ", fields=" + fields + "]";
  }


  @Column(name = "type")
  String type;
  @Column(name = "name")
  String name;
  @Column(name = "namespace")
  String namespace;
  @Column(name = "doc")
  String doc;


  public Schema(String schemaId, String type, String name, String namespace, String doc,
      List<SchemaField> fields) {
    this.schemaId = schemaId;
    this.type = type;
    this.name = name;
    this.namespace = namespace;
    this.doc = doc;
    this.fields = fields;
  }


  @ElementCollection(fetch=FetchType.EAGER)
  @Column(name = "fields")
  private List<SchemaField> fields;



  public Schema() {}


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNamespace() {
    return namespace;
  }

  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

  public String getDoc() {
    return doc;
  }

  public void setDoc(String doc) {
    this.doc = doc;
  }

  public List<SchemaField> getFields() {
    return fields;
  }

  public void setFields(List<SchemaField> fields) {
    this.fields = fields;
  }


  public String getSchemaId() {
    return schemaId;
  }


  public void setSchemaId(String schemaId) {
    this.schemaId = schemaId;
  }


}
