
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
    <head>
<meta charset="utf-8">
<title>Visual Bookmarker</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

<style>
body {
	padding-top: 160px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
.center {
    text-align:center;
}

.center form {
    display:inline-block;
}
</style>


<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">
</head>


    <body>  
    
    <div class="container">
		<div class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a> <a class="brand" href="#">Visual BookMarker</a>
					<div class="nav-collapse">
						<ul class="nav">
							<li class="active"><a href="index.jsp">Home</a></li>
							<li class="active"><a href="Uploader.jsp">Add</a></li>
							<li class="active"><a href="about.html">About</a></li>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>
		<h1>Upload image</h1><br/>
      <form class="form-vertical well" action="BookMarkServlet" method="post"  enctype="multipart/form-data"  name="uploadForm" id="uploadForm"> 
      
      		<div class="editor-label">Upload: </div>
        	<div class="editor-field"><input type="file" name="imageFile"> </div>
        	<div class="editor-label">Id: </div>
			<div class="editor-field"><input type="text" name="id"></div>			
			<div class="editor-label">Name:</div> 
			<div class="editor-field"><input type="text" name="name" value="harvard"></div>
			<div class="editor-field"><input type="submit" value="Submit &raquo;"/><input type="reset" value="Reset &raquo;"/></div>
			
             <input type="hidden" name="path" value="testpath">
             <input type="hidden" name="captureDate" value="<%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(new java.util.Date()) %>">
             <input type="hidden" name="lat" value="12.34">
             <input type="hidden" name="long" value="34.45">
             <input type="hidden" name="sharingFlag" value="p">
             <input type="hidden" name="additionalInfo" value="Uploaded from website">
             <input type="hidden" name="website" value="true">
            
        </form>  
        </div>
    </body>  
</html>  