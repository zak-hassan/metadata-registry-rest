package com.redhat.data.analytics.repo;

import com.redhat.data.analytics.model.DataStream;
import com.redhat.data.analytics.model.SinkDataStream;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhassan on 2016-10-26.
 */
public class DataStreamService {

    public static String delete(String id) {
        return "deleted";//FIXME Implement delete logic
    }

    public static DataStream get(String id) {
        // FIXME Implement update logic
        //String sourceId, String sinkId
       new DataStream("source-id-1","sink-id-1");
    }

    public static List<DataStream> getAll() {
        // FIXME Auto-generated method stub
        return Arrays.asList(       new DataStream("source-id-1","sink-id-1"),
                new DataStream("source-id-2","sink-id-2"),
                new DataStream("source-id-3","sink-id-3"),
                new DataStream("source-id-4","sink-id-4")
        );
    }

    public DataStream update(DataStream item) {
        // FIXME Implement update logic
        return  item;
    }

    public DataStream add(DataStream item) {
        // FIXME Implement logic to save .
        return item;
    }
}
