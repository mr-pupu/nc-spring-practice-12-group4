<% 
//HttpSession session = request.getSession();
java.util.ResourceBundle locale = (java.util.ResourceBundle)session.getAttribute("resourceBoundle");
java.util.Map<String, String> map = localization.TextLocalization.getLocaleNamesAndCodes();
%>
<div class="navbar  navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">   
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</a>         
			<a class="brand" href="#"><%=locale.getString("menu.projectName")%></a>        
			<div class="nav-collapse">         
				<ul class="nav">
					<li><a href="<%=request.getContextPath()%>"><%=locale.getString("menu.home")%></a></li>
<%	if (session.getAttribute("name") != null) {%>
					<li><a href="<%=request.getContextPath()%>/forms"><%=locale.getString("menu.forms")%></a></li>
					<li><a href="<%=request.getContextPath()%>/administrator"><%=locale.getString("menu.administrator")%></a></li>
					<li><a href="<%=request.getContextPath()%>/reports"><%=locale.getString("menu.reports")%></a></li>
<%	} %>
				</ul>                      
<%	if (session.getAttribute("name") == null) {%>
				<form class="navbar-form pull-right" action="<%=request.getContextPath()%>/auth" method="post">
					<input type="text" class="input-small" name="login" placeholder="<%=locale.getString("form.login.login")%>">
					<input type="password" class="input-small" placeholder="<%=locale.getString("form.login.password")%>" name ="password">
					<button type="submit" class="btn"><%=locale.getString("form.login.signIn")%></button>
				</form>
<%	} else { %>
				<p class="navbar-text pull-right"><%=locale.getString("menu.logged")%> <a href="<%=request.getContextPath()%>/quit"><%=session.getAttribute("name") %></a></p>
<%	} %>

				<ul class="nav pull-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <%=locale.getString("locale.name") %>
							<b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
<% for (java.util.Map.Entry<String,String> entry : map.entrySet()) { 
	if (locale.getString("locale.name").equals(entry.getKey())) {%>
	
<% } else {%>
							<li><a href="<%=request.getContextPath()%>/locale?locale=<%=entry.getValue() %>"><%=entry.getKey() %></a></li>
<% }} %>
						</ul>
					</li>
				</ul>		
			</div>
        </div>
      </div>
    </div>