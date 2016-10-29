package com.redhat.data.analytics.repo;

import com.redhat.data.analytics.model.SinkDataStream;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhassan on 2016-10-25.
 */
public class SinkDataStreamService {


    public static String delete(String id) {
        return "deleted";//FIXME Implement delete logic
    }

    public static SinkDataStream get(String id) {
        // FIXME Implement update logic
        // SinkDataStream(String name, String sourceId, String schemaId, String ipaddress, String path)
        return new SinkDataStream( "hdfs",   "file",   "schema-id1",   "127.0.0.1",   "/dataingest");
    }

    public static List<SinkDataStream> getAll() {
        // FIXME Auto-generated method stub
        return Arrays.asList(new SinkDataStream( "hdfs",   "source-id-1",   "schema-id-1",   "127.0.0.1",   "/dataingest-1"),
                new SinkDataStream( "amq",   "source-id-2",   "schema-id-2",   "127.0.0.1",   "/dataingest-2"),
                new SinkDataStream( "kafka",   "source-id-3",   "schema-id-3",   "127.0.0.1",   "/dataingest-3"),
                new SinkDataStream( "sftp",   "source-id-4",   "schema-id-4",   "127.0.0.1",   "/dataingest-4")
                );
    }

    public SinkDataStream update(SinkDataStream item) {
        // FIXME Implement update logic
        return  item;
    }

    public SinkDataStream add(SinkDataStream item) {
        // FIXME Implement logic to save .
        return item;
    }

}
