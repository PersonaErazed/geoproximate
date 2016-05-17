package com.github.personaerazed.util;

import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import javax.servlet.annotation.WebServlet;

@WebServlet("/eventcoordinates")  
public class EventCoordListServlet extends HttpServlet  {  
   public void doGet(HttpServletRequest request,  
                  HttpServletResponse response)  
               throws IOException,ServletException  {
      
      request.getParameter("knownEvents");
      request.getParameter("timestampsUnkownPositions");


//      response.setContentType("text/HTML");    
      PrintWriter out = response.getWriter();
      out.println(request.getParameter("knownEvents"));
      out.println(request.getParameter("timestampsUnkownPositions"));
//      out.flush();  
   }
} 
