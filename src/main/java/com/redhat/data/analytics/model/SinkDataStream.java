package com.redhat.data.analytics.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by zhassan on 2016-10-25.
 *
 * SinkDataStream <br>
 *     Responsible for managing the current sink of data streaming into the data platform.
 */
@Entity
public class SinkDataStream {

    public SinkDataStream() {
    }

    public SinkDataStream(String sinkId, String name, String sourceId, String schemaId, String ipaddress, String path) {
        this.sinkId = sinkId;
        this.name = name;
        this.sourceId = sourceId;
        this.schemaId = schemaId;
        this.ipaddress = ipaddress;
        this.path = path;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String sinkId;
    @Column(name="name")
    String name;
    // Required for wiring the source to the sink.
    @Column(name="sourceId")
    String sourceId;
    // You will need the schema id to process the data that is being processed.
    @Column(name="schemaId")
    String schemaId;
    // Destination of where the data will be pushed to.
    @Column(name="ipaddress")
    String ipaddress;
    @Column(name="path")
    String path;

    public String getSinkId() {
        return sinkId;
    }

    public void setSinkId(String sinkId) {
        this.sinkId = sinkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "SinkDataStream{" +
                "sinkId='" + sinkId + '\'' +
                ", name='" + name + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", schemaId='" + schemaId + '\'' +
                ", ipaddress='" + ipaddress + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
