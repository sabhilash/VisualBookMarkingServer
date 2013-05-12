
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
							<li class="active"><a href="Uploader.jsp">Add</a></li>
							<li class="active"><a href="about.jsp">About</a></li>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>

		<%
			List<BookMark> bookmarkList = (ArrayList) request.getAttribute("bookmarkList");
			if (bookmarkList != null && !bookmarkList.isEmpty()) {
				for (int i = 0; i < bookmarkList.size(); i++) {
					try {
						BookMark row = (BookMark) bookmarkList.get(i);
		%>

		<div align="center">
			<ul class="thumbnails">
				<li>
					<div class="thumbnail">
						<img src="ImageServlet?id=<%=row.getId()%>" />
						<div class="caption">
							<h4><%=row.getName()%></h4>
							<p>
							<table>
								<tr>
									<td>Image Location:</td>
									<td>Lat:<%=row.getLat()%>, Long:<%=row.getLon()%></td>
								</tr>
								<tr>
									<td>Capture Date:</td>
									<td><%=row.getCaptureDate()%></td>
								</tr>
								<tr>
									<td>Notes:</td>
									<td><%=row.getAdditionalInfo()%></td>
								</tr>
								<tr>
								<td>
								<a href="https://twitter.com/share" class="twitter-share-button">Tweet</a>
								</tr>
							</table>
						</div>
					</div>
				</li>
			</ul>
		</div>

		<%
			} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
		%>
		Found no results..
		<%
			}
		%>

	</div>
	<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>
</body>
</html>