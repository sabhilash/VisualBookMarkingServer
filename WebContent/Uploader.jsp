
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
    <header></header>  
    <body>  
      <form action="BookMarkServlet" method="post"  enctype="multipart/form-data"  name="uploadForm" id="uploadForm"> 
            Upload: <input type="file" name="imageFile"><br>  
            Id: <input type="text" name="id"><br>  
             <input type="hidden" name="name" value="testname" >
             <input type="hidden" name="path" value="testpath">
             <input type="hidden" name="captureDate" value="<%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(new java.util.Date()) %>">
             <input type="hidden" name="lat" value="12.34">
             <input type="hidden" name="long" value="34.45">
             <input type="hidden" name="sharingFlag" value="p">
             <input type="hidden" name="additionalInfo" value="restuarant">
             <input type="submit" value="Submit">
        </form>  
    </body>  
</html>  