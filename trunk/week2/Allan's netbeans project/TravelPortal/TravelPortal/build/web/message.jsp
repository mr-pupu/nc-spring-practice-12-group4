<div class="row">
	<div class="span4">

		<div class="alert alert-<%=session.getAttribute("messageType") %>">
			<a class="close" data-dismiss="alert">&times;</a>
			<%=session.getAttribute("messageText") %>
		</div>
	</div>
</div>
<% session.removeAttribute("messageText"); %>