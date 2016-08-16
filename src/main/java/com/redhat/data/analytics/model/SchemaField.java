package com.redhat.data.analytics.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SchemaField {
  @Column(name = "name")
  String name;


  @Column(name = "type")
  String type;

  public SchemaField() {}


  public SchemaField(String name, String type) {
    super();
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "SchemaField [name=" + name + ", type=" + type + "]";
  }


}
