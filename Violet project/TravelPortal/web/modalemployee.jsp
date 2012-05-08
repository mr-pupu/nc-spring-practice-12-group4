<!--Author Allan-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:setBundle
    basename="${localeBean.bundlePath}_${sessionScope.bundle}" />
<div id="editEmployeeModal" class="modal hide fade"
	style="width: 400px; margin: -250px 0 -0 -220px;">
	<div class="modal-header">
		<a class="close" data-dismiss="modal">&times;</a>
		<h2>
			<fmt:message key="modal.employee.header" />
		</h2>
	</div>
	<div class="modal-body">
		<table class="table table-condensed" >
			<tbody >
				<tr>
					<td style="width: 50%;"><fmt:message
							key="modal.employee.firstName" /></td>
					<td><input id="firstName" type="text" class=""></td>
				</tr>
				<tr>
					<td><fmt:message key="modal.employee.lastName" /></td>
                                        <td><input id="lastName" type="text" class=""></td>
				</tr>
				<tr>
					<td><fmt:message key="modal.employee.position" /></td>
					<td><select class="combobox" style="width : 180px;" id="position">
                                            </select></td>
				</tr>
				<tr>
					<td><fmt:message key="modal.employee.office" /></td>
					<td><select class="combobox" style="width : 180px;" id="office">
                                            </select></td>
				</tr>
				<tr>
					<td><fmt:message key="modal.employee.email" /></td>
                                        <td><input id="email" type="text" class=""></td>
				</tr>
				<tr>
					<td><fmt:message key="form.login.login" /></td>
                                        <td><input id="login" type="text" class=""></td>
				</tr>
				<tr>
					<td><fmt:message key="form.login.password" /></td>
                                        
				</tr>
			</tbody>
		</table>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn btn-success" onclick='processEmployee()'>Save changes</a> 
                <a href="#" class="btn" data-dismiss="modal">Cancel</a>
	</div>
</div>