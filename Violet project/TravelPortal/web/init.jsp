<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="localeBean" scope="application"
	class="beans.LocalizationsBean"></jsp:useBean>
<fmt:setBundle
	basename="${localeBean.bundlePath}_${sessionScope.bundle}"  scope="session"/>
	<% System.out.println("bundle loaded"); %>