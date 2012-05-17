<!--Author Allan-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:setBundle
    basename="${localeBean.bundlePath}_${sessionScope.bundle}" />
<div id="editEmployeeModal" class="modal hide fade"
	style="width: 420px; margin: -250px 0 -0 -220px;">
	<div class="modal-header">
            <meta name="author" content="Allan">
		<a href="#" class="close" onclick="$('#editEmployeeModal').modal('hide')">&times;</a>
		<h2 id="modalTitle">
			<fmt:message key="modal.employee.header" />
		</h2>
	</div>
	<div class="modal-body" >
		<table class="table table-condensed" >
			<tbody >
				<tr>
					<td style="width : 100px;"><fmt:message
							key="modal.employee.firstName" /></td>
					<td><input id="firstName" type="text" class="" style="width : 240px;"></td>
				</tr>
				<tr>
					<td style="width : 100px;"><fmt:message key="modal.employee.lastName" /></td>
                                        <td><input id="lastName" type="text" class="" style="width : 240px;"></td>
				</tr>
				<tr>
					<td style="width : 100px;"><fmt:message key="modal.employee.position" /></td>
					<td><select class="combobox" style="width : 240px;" id="position">
                                            </select></td>
				</tr>
				<tr>
					<td style="width : 100px;"><fmt:message key="modal.employee.office" /></td>
					<td><select class="combobox" style="width : 240px;" id="office">
                                            </select></td>
				</tr>
                                <tr>
					<td style="width : 100px;">Department:</td>
					<td><select class="combobox" style="width : 240px;" id="department">
                                            </select></td>
				</tr>
				<tr>
					<td style="width : 100px;"><fmt:message key="modal.employee.email" /></td>
                                        <td><input id="email" type="text" class="" style="width : 240px;"></td>
				</tr>
				<tr>
					<td style="width : 100px;"><fmt:message key="form.login.login" /></td>
                                        <td><input id="login" type="text" class="" style="width : 240px;"></td>
				</tr>
				<tr>
                                    <td colspan="2"><label class="checkbox" <td style="width : 150px;">
                                                <input type="checkbox" id="password" onchange="passwordHandler(id)"/>
                                                Generate new password
                                            </label>
                                    </td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="modal-footer">
		<a id="employeeCommit" href="#" class="btn btn btn-success" onclick='processEmployee()'>Save changes</a> 
                <a href="#" class="btn" onclick="$('#editEmployeeModal').modal('hide')">Cancel</a>
	</div>
</div>