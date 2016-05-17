package com.github.personaerazed.util;

import java.time.*;
import java.util.*;

public class EventEstimator {
  private TreeMap<LocalDateTime,GlobalSurfacePosition> knownEvents;

  public EventEstimator(
    TreeMap<LocalDateTime,GlobalSurfacePosition> knownEvents)
  {
    this.knownEvents = knownEvents;
  }

  public TreeMap<LocalDateTime,GlobalSurfacePosition>
    getEstimatedEventCoordinates(ArrayList<LocalDateTime> timestamps)
  {
    TreeMap<LocalDateTime,GlobalSurfacePosition> estimatedEvents
      = new TreeMap<LocalDateTime,GlobalSurfacePosition>();
    for (int i=0; i<timestamps.size(); i++) {
      estimatedEvents.put( timestamps.get(i),
        getEstimatedPosition(timestamps.get(i))
      );
    }
    return estimatedEvents;
  }

  public GlobalSurfacePosition getEstimatedPosition(LocalDateTime timestamp)
  {
    LocalDateTime dateBefore = knownEvents.ceilingKey(timestamp);
    LocalDateTime dateAfter = knownEvents.higherKey(timestamp);
    double lat1 = knownEvents.get(dateBefore).getLatitude();
    double lon1 = knownEvents.get(dateBefore).getLongitude();
    double lat2 = knownEvents.get(dateAfter).getLatitude();
    double lon2 = knownEvents.get(dateAfter).getLongitude();
    ZoneOffset zOff = ZoneOffset.ofHours( // another estimation error
      (int)(Math.toDegrees(lon1*24/360))
    );
    double frac = timestamp.toEpochSecond(zOff) /
      (dateAfter.toEpochSecond(zOff)-dateBefore.toEpochSecond(zOff))
    ;
    return new GlobalSurfacePosition(
      (lat2-lat1)*frac + lat1,
      (lon2-lon1)*frac + lon1, 'd'
    );
  }

  public String toString()
  {
    return "Known Events:\n" + knownEvents.toString();
  }
}
