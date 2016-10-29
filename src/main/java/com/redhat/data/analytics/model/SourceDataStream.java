package com.redhat.data.analytics.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Zak Hassan on 2016-10-25.
 *
 * SourceDataStream <br>
 *     Responsible for managing the current source of data streaming into the data platform.
 *
 */
@Entity
public class SourceDataStream {

    public SourceDataStream() {
    }

    public SourceDataStream(String name, String type, String sourceId) {
        this.name = name;
        this.type = type;
        this.sourceId = sourceId;
    }

    @Column(name = "name")
    String name;
    @Column(name = "type")
    String type;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String sourceId;

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

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public String toString() {
        return "SourceDataStream{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sourceId='" + sourceId + '\'' +
                '}';
    }
}
