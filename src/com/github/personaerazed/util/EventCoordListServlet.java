package com.github.personaerazed.util;

import java.io.*;  
import java.util.*;
import java.util.regex.*;
import javax.servlet.*;  
import javax.servlet.http.*;  
import javax.servlet.annotation.WebServlet;
import java.time.*;
import java.time.format.*;

@WebServlet("/eventcoordinates")  
public class EventCoordListServlet extends HttpServlet  {  
   public void doGet(HttpServletRequest request,  
                  HttpServletResponse response)  
               throws IOException,ServletException  {
      
      PrintWriter out = response.getWriter();
     
      TreeMap<LocalDateTime,GlobalSurfacePosition> events =
        convertToTreeMap( request.getParameter("knownEvents").split("\n") );
//      out.println(events);
      EventEstimator worldModel = new EventEstimator( events );
 /*
     ArrayList<LocalDateTime> timestamps
        = getDates(request.getParameter("timestampsUnkownPositions").split("\n"));

      for (int i=0; i<timestamps.size(); i++) {
        out.print(timestamps.get(i));
        out.println(worldModel.getEstimatedPosition(timestamps.get(i)));
      }
*/
      TreeMap<LocalDateTime,GlobalSurfacePosition> estimatedEvents =
        worldModel.getEstimatedEventCoordinates(
        getDates(request.getParameter("timestampsUnkownPositions").split("\n"))
      );
      out.println(worldModel.toString());
      out.println(estimatedEvents.toString());

//      response.setContentType("text/HTML");    
//      out.println(request.getParameter("knownEvents"));
//      out.println(request.getParameter("timestampsUnkownPositions"));
//      out.flush();  
   }
   private ArrayList<LocalDateTime> getDates(String[] lines) {
     ArrayList<LocalDateTime> dates = new ArrayList<LocalDateTime>();
     for (int i=0; i<lines.length; i++) {
       dates.add(getDate(lines[i]));
     }
     return dates;
   }
   private TreeMap<LocalDateTime, GlobalSurfacePosition>
     convertToTreeMap(String[] lines) {

     TreeMap<LocalDateTime, GlobalSurfacePosition> data
       = new TreeMap<LocalDateTime, GlobalSurfacePosition>();
     for (int i=0; i<lines.length; i++) {
       data.put(
         getDate(lines[i]),
         getPosition(lines[i])
       );
     }
     return data;
   }
   private GlobalSurfacePosition getPosition(String line) {
     GSPFormatter f = new GSPFormatter();
     return f.parse(line);
   }
   private LocalDateTime getDate(String line) {
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
