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

@WebServlet("/FirstServlet")  
public class FirstServlet extends HttpServlet  {  
   public void doGet(HttpServletRequest request, HttpServletResponse response)  
     throws IOException,ServletException  {

     String text = "{foo:bar}";
     JSONObject json = new JSONObject(text);
     response.setContentType("application/json");
     response.setCharacterEncoding("UTF-8");
//     response.getWriter().write(json.toString());
     json.write(response.getWriter());
   }
}
