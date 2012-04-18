<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:setBundle
	basename="${localeBean.bundlePath}_${sessionScope.bundle}" />
<div id="myModal" class="modal hide fade"
	style="width: 600px; margin: -250px 0 -0 -320px;">
	<div class="modal-header">
		<a class="close" data-dismiss="modal">&times;</a>
		<h2>
			<fmt:message key="modal.employee.header" />
		</h2>
	</div>
	<div class="modal-body">
		<table class="table table-condensed">
			<tbody>
				<tr>
					<td style="width: 50%;"><fmt:message
							key="modal.employee.firstName" /></td>
					<td><input type="text" class=""></td>
				</tr>
				<tr>
					<td><fmt:message key="modal.employee.lastName" /></td>
					<td><input type="text" class=""></td>
				</tr>
				<tr>
					<td><fmt:message key="modal.employee.position" /></td>
					<td><select class="btn">
							<option>Team lead</option>
							<option value="PA">dd</option>
							<option value="CT">ff</option>
					</select></td>
				</tr>
				<tr>
					<td><fmt:message key="modal.employee.office" /></td>
					<td><select class="btn">
							<option>Team lead</option>
							<option value="PA">dd</option>
							<option value="CT">ff</option>
					</select></td>
				</tr>
				<tr>
					<td><fmt:message key="modal.employee.email" /></td>
					<td><input type="text" class=""></td>
				</tr>
				<tr>
					<td><fmt:message key="form.login.login" /></td>
					<td><input type="text" class=""></td>
				</tr>
				<tr>
					<td><fmt:message key="form.login.password" /></td>
					<td><input type="password" class="input"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn btn-success">Save changes</a> <a href="#"
			class="btn" data-dismiss="modal">Cancel</a>
	</div>
</div>