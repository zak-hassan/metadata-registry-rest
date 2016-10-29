package com.redhat.data.analytics.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * DataStream  store
 *
 * @author Zak Hassan <zak.hassan1010@redhat.com>
 */
@Entity
public class DataStream {

    public DataStream() {

    }

    public DataStream(String dataStreamId, String sourceId, String status, String sinkId) {
        this.dataStreamId = dataStreamId;
        this.sourceId = sourceId;
        this.status = status;
        this.sinkId = sinkId;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String dataStreamId;
    //from
    @Column(name = "sourceId")
    String sourceId;


    @Column(name = "status") // Can only be running, paused or stopped
            String status;

    // to
    @Column(name = "sinkId")
    String sinkId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataStreamId() {
        return dataStreamId;
    }

    public void setDataStreamId(String dataStreamId) {
        this.dataStreamId = dataStreamId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSinkId() {
        return sinkId;
    }

    public void setSinkId(String sinkId) {
        this.sinkId = sinkId;
    }

    @Override
    public String toString() {
        return "DataStream{" +
                "dataStreamId='" + dataStreamId + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", sinkId='" + sinkId + '\'' +
                '}';
    }


}
