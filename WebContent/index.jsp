
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

.btn-toolbar {
	text-align: center;
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
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
					</a> 
					<a class="brand" href="#"><img src="assets/img/bookmarks.png" style="height:30px;width=30px;"/>Visual BookMarker</a>
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

		<div class="container" align="center">

			<h1>Visual BookMarker</h1>
			<p style="padding-bottom: 60px;">
				Search using a Bookmark Id or Username.<br>
			</p>

			<div class="btn-toolbar">
				<div class="btn-group searchby" data-toggle="buttons-radio">
					<button type="button" class="btn text-center active" value="0">Use Bookmark Id</button>
					<button type="button" class="btn text-center" value="1">Use Username</button>
				</div>
			</div>
			<div>
				<br>
				<form class="span12 form-search" method="GET" action="BookMarkInputServlet">
					<input class="" type="search" id="id" name="id">
					<button class="btn text-center" type="submit">Search!</button>
					<input type="hidden" id="search_by" name="search_by" value="0" />
				</form>
			</div>
			<!-- /container -->

		</div>
		
		</div>

		<!-- Placed at the end of the document so the pages load faster -->
		<script type="text/javascript"
			src="http://platform.twitter.com/widgets.js"></script>
		<script src="assets/js/jquery-1.9.1.min.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
		<script>
			$(function() {
				$(".searchby .btn").click(function() {
					$("#search_by").val($(this).val());
				});
			});
		</script>
</body>

</html>