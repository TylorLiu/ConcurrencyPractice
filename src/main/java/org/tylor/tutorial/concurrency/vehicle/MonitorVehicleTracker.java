package org.tylor.tutorial.concurrency.vehicle;

import org.tylor.tutorial.concurrency.annotation.GuardedBy;
import org.tylor.tutorial.concurrency.annotation.NotThreadSafe;
import org.tylor.tutorial.concurrency.annotation.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Tylor 2020/11/16
 */
@ThreadSafe
public class MonitorVehicleTracker {

    @GuardedBy("this")
    private final Map<String,MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String,MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    private Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> locations) {
        Map<String,MutablePoint> result = new HashMap<>();

        for (Map.Entry<String,MutablePoint> entry:locations.entrySet()) {
            result.put(entry.getKey(), new MutablePoint(entry.getValue()));
        }
        return Collections.unmodifiableMap(result);
    }

    public synchronized MutablePoint getLocation(String id ){
        MutablePoint point = locations.get(id);

        return point==null?null:new MutablePoint(point);
    }

    public synchronized void setLocation(String id,int x, int y ){
        MutablePoint p = locations.get(id);
        if (p==null){
            throw new IllegalArgumentException("No Such ID:"+id);
        }
        p.x = x;
        p.y = y;
    }


    @NotThreadSafe
    private class MutablePoint {
        public int x,y;

        public MutablePoint() {
            this.x = 0;
            this.y = 0;
        }

        public MutablePoint(MutablePoint p) {
            this.x = p.x;
            this.y = p.y;
        }
    }
}
