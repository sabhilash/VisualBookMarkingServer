
<%@ page import="visualbookmarking.bean.BookMark"%>
<%@ page import="java.io.*"%>

<html>
<head>
<meta charset="utf-8">
<title>Visual Bookmarker</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">

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
							<li class="active"><a href="upload.jsp">Add</a></li>
							<li class="active"><a href="about.jsp">About</a></li>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>

		<%
			BookMark bookmark = (BookMark) request.getAttribute("bookmarkObj");
			if (bookmark != null) {
		%>

		<div align="center">

			<ul class="thumbnails">
				<li class="span12">
					<div class="thumbnail">
						<img style="float: left" src="ImageServlet?id=<%=request.getParameter("id")%>" />
						<div class="caption">
							<h5><%=bookmark.getName()%></h5>
							<p><table>
								<tr>
							<td>Image Location:</td>
							<td>Lat:<%=bookmark.getLat()%>, Long:<%=bookmark.getLon()%></td>
						</tr>
						<tr>
							<td>Capture Date:</td>
							<td><%=bookmark.getCaptureDate()%></td>
						</tr>
						<tr>
							<td>Notes:</td>
							<td><%=bookmark.getAdditionalInfo()%></td>
						</tr>
					</table>
              
          					
             <!--  <p><a href="#" class="btn btn-success">Edit</a> <a href="#"class="btn btn-warning">Delete</a></p> -->
            </div>
          </div>
        </li>
      </ul>
  

      
	</div>
	<%
		} else {
	%>
	<p>No images to display!</p>
	<%
		}
	%>

</div>
<!-- <footer>
        <p>&copy; Simili Abhilash & Thankam Girija 2013</p>
      </footer> -->
</body>

</html>