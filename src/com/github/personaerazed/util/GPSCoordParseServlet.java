package com.github.personaerazed.util;
import java.io.*;  
import java.util.*;
import java.util.regex.*;
import javax.servlet.*;  
import javax.servlet.http.*;  
import javax.servlet.annotation.WebServlet;
import java.time.*;
import java.time.format.*;
import org.json.*;

@WebServlet("/gps-parse")
public class GPSCoordParseServlet extends HttpServlet  {  
  public void doGet(HttpServletRequest request,
                  HttpServletResponse response)  
               throws IOException,ServletException  {

    String query = request.getQueryString();
    GlobalSurfacePosition gsp = getPosition(query);

    JSONObject json = toJSON(gsp);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    json.write(response.getWriter());
   }

   private JSONObject toJSON(GlobalSurfacePosition gsp) {
     JSONObject feature = new JSONObject();
     try {
       JSONArray coordinates = new JSONArray(
         "[" + gsp.getLongitude() + "," + gsp.getLatitude() + "]"
       );

       JSONObject point = new JSONObject();
       point.put("type","Point");
       point.put("coordinates", coordinates);
       
       feature.put("type", "Feature");
       feature.put("geometry", point);
     } catch (JSONException e) {}
     return feature;
   }
   private GlobalSurfacePosition getPosition(String line) {
     GSPFormatter f = new GSPFormatter();
     return f.parse(line);
   }
} 
