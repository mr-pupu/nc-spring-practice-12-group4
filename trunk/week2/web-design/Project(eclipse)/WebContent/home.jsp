<%java.util.ResourceBundle locale = (java.util.ResourceBundle)session.getAttribute("resourceBoundle");%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bootstrap, from Twitter</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="<%=request.getContextPath() %>/assets/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
    <link href="<%=request.getContextPath() %>/assets/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=request.getContextPath() %>/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=request.getContextPath() %>/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%=request.getContextPath() %>/assets/ico/apple-touch-icon-57-precomposed.png">
  </head>
  <body>
    <jsp:include page="menu.jsp"></jsp:include>
    <div class="container">

<%if (session.getAttribute("messageText") != null) { %>
 	<jsp:include page="message.jsp"></jsp:include> 
<% } %>  	
    
<%if (session.getAttribute("name") == null) {%>
		<h1><%=locale.getString("page.home.hello1.unauth")%></h1>
        <p><%=locale.getString("page.home.hello2.unauth")%><br> 
        <p><%=locale.getString("page.home.hello3.unauth")%><br> 
        <div class="row">
 			 <div class="span4">
		        <form class="well form-vertical" action="<%=request.getContextPath()%>/auth" method="post">
		  			<input type="text" class="span3" placeholder="<%=locale.getString("form.login.login")%>" name="login"> <br>
		  			<input type="password" class="span3" placeholder="<%=locale.getString("form.login.password")%>" name ="password"> <br>
		  			<button type="submit" class="btn"><%=locale.getString("form.login.signIn")%></button>
				</form>
			 </div>
			 <div class="span4">
		        
			 </div>
		</div>

<%} else { %>
	<h1><%=locale.getString("page.home.hello1.auth")%>  <a href="<%=request.getContextPath()%>/quit"><%=session.getAttribute("name") %></a></h1>
        <p><%=locale.getString("page.home.hello2.auth")%><br> 			
<%} %>
    </div> <!-- /container -->
    <jsp:include page="scripts.jsp"></jsp:include>
  </body>
</html>
