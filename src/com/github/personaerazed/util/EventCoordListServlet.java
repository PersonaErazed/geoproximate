package com.github.personaerazed.util;
import java.io.*;  
import java.util.*;
import java.util.regex.*;
import javax.servlet.*;  
import javax.servlet.http.*;  
import javax.servlet.annotation.WebServlet;
import java.time.*;
import java.time.format.*;
import javax.json.*;

@WebServlet("/eventcoordinates")  
public class EventCoordListServlet extends HttpServlet  {  
   public void doGet(HttpServletRequest request,  
                  HttpServletResponse response)  
               throws IOException,ServletException  {
      
      PrintWriter out = response.getWriter();
     
      TreeMap<LocalDateTime,GlobalSurfacePosition> events =
        convertToTreeMap( request.getParameter("knownEvents").split("\n") );
      EventEstimator worldModel = new EventEstimator( events );
      TreeMap<LocalDateTime,GlobalSurfacePosition> estimatedEvents =
        worldModel.getEstimatedEventCoordinates(
        readDates(request.getParameter("timestampsUnkownPositions").split("\n"))
      );
      out.println(worldModel.toString());
      out.println(estimatedEvents.toString());
      out.println(toJSON(estimatedEvents));
   }
   // make object events? MapTree<> ..
   private JsonObject toJSON(TreeMap<LocalDateTime,GlobalSurfacePosition> events) {
    TreeMap<LocalDateTime, GlobalSurfacePosition> eventsCopy = events;
    JsonObject featureCollection = new JsonObject();
    try {
      featureCollection.put("type", "FeatureCollection");
      JsonArray featureList = new JsonArray();
      Map.Entry<LocalDateTime, GlobalSurfacePosition> entry;
      LocalDateTime timestamp;
      GlobalSurfacePosition location;
      while (! eventsCopy.isEmpty() ) {
        // {"geometry": {"type": "Point", "coordinates": [-94.149, 36.33], "properties": {"prop0": "2016-04-07T13:21:08"}}
        entry = eventsCopy.pullFirstEntry();
        timestamp = entry.getKay();
        location = entry.getValue();
        JsonObject point = new JsonObject();
        point.put("type", "Point");
        JsonArray coord = new JsonArray("["+location.getLongitude()+","+location.getLatitude()+"]");
        point.put("coordinates", coord);
        JsonObject feature = new JsonObject();
        feature.put("geometry", point);
        JsonArray property = new JsonArray();
        property.put("prop0",timestamp);
        feature.put("properties",property);
        featureList.put(feature);
        featureCollection.put("features", featureList);
      }
    } catch (JSONException e) {
    }
   }
   private ArrayList<LocalDateTime> readDates(String[] lines) {
     ArrayList<LocalDateTime> dates = new ArrayList<LocalDateTime>();
     for (int i=0; i<lines.length; i++) {
       dates.add(readDate(lines[i]));
     }
     return dates;
   }
   private TreeMap<LocalDateTime, GlobalSurfacePosition>
     convertToTreeMap(String[] lines) {

     TreeMap<LocalDateTime, GlobalSurfacePosition> data
       = new TreeMap<LocalDateTime, GlobalSurfacePosition>();
     for (int i=0; i<lines.length; i++) {
       data.put(
         readDate(lines[i]),
         getPosition(lines[i])
       );
     }
     return data;
   }
   private GlobalSurfacePosition getPosition(String line) {
     GSPFormatter f = new GSPFormatter();
     return f.parse(line);
   }
   private LocalDateTime readDate(String line) {
     DateFormat f = DateFormat.ISO_LOCAL_DATE_TIME;
     Matcher m = Pattern.compile( f.getRegex() ).matcher(line);
     if (m.find()) {
       return LocalDateTime.parse(m.group(), f.getFormatter());
     } else {
       f = new DateFormat(
         DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss")
         , "\\d{4}:[01]\\d\\:[0-3]\\d [0-2]\\d\\:[0-6]\\d\\:[0-6]\\d"
       );
       m = Pattern.compile(f.getRegex()).matcher(line);
       if (m.find()) {
         return LocalDateTime.parse(m.group(), f.getFormatter());
       }
     }
     return null;
   }
} 
class DateFormat {
  // ISO_LOCAL_DATE_TIME   ISO Local Date and Time   '2011-12-03T10:15:30'
  static final DateFormat ISO_LOCAL_DATE_TIME = new DateFormat(
      DateTimeFormatter.ISO_LOCAL_DATE_TIME
    , "\\d{4}-[01][0-9]-[0-3][0-9]T[0-2]\\d\\:[0-6]\\d\\:[0-6]\\d"
  );
  // ISO_OFFSET_DATE_TIME   Date Time with Offset   2011-12-03T10:15:30+01:00'
  static final DateFormat ISO_OFFSET_DATE_TIME = new DateFormat(
      DateTimeFormatter.ISO_OFFSET_DATE_TIME
    , "\\d{4}-[01][0-9]-[0-3][0-9]T[0-2]\\d\\:[0-6]\\d\\:[0-6]\\d[+-][0-2]\\d\\:[0-6]\\d"
  );

  String regex;
  DateTimeFormatter formatter;
  DateFormat(DateTimeFormatter formatter, String regex) {
    this.regex = regex;
    this.formatter = formatter;
  }
  DateFormat(String format, String regex) {
    this.regex = regex;
    this.formatter = DateTimeFormatter.ofPattern(format);
  }
  String getRegex() { return regex; }
  DateTimeFormatter getFormatter() { return formatter; }
}
