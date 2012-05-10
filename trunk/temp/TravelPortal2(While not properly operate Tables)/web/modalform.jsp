<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:setBundle
    basename="${localeBean.bundlePath}_${sessionScope.bundle}" />
<div id="myModal" class="modal hide fade" style="width: 750px; margin: -250px 0 -0 -380px;">
    <div class="modal-header">
        <a href="#" class="close" data-dismiss="modal">&times;</a>
        <h3>
            <fmt:message key="modal.form.header" />
        </h3>
    </div>
    <div class="modal-body">
        <div class="accordion" id="accordion3" style="display:none;">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse"
                       data-parent="#accordion3" href="#collapseOne1">
                        <h5>
                            <fmt:message key="modal.form.currentStatus" />
                            :
                            <fmt:message key="modal.form.entering" />
                        </h5>
                    </a>
                </div>
                <div id="collapseOne1" class="accordion-body collapse in">
                    <div class="accordion-inner">
                        <form class="form-horizontal">

                            <table cellspacing="10">
                                <tbody>
                                    <tr style="height : 30px;">
                                        <td><fmt:message key="modal.form.employeeName" /></td>
                                        <td id="employee"></td>
                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                        <td><fmt:message key="modal.form.office" /></td>
                                        <td id="office"></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.departureDate" /></td>
                                        <td>
                                            <div class="input-append" rel="calendarDiv">
                                                <input style="width : 70px;" id="beginDate" size="16" type="text"><span
                                                    class="add-on"><i class="icon-calendar"></i></span>
                                            </div>                              
                                        </td>
                                        <td></td>
                                        <td><fmt:message key="modal.form.returnDate" /> :</td>
                                        <td>
                                            <div class="input-append" rel="calendarDiv">
                                                <input style="width : 70px;" id="endDate" size="16" type="text"><span
                                                    class="add-on"><i class="icon-calendar"></i></span>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.destinationCountry" /></td>
                                        <td><select class="combobox" style="width : 180px;" id="country">
                                            </select></td>
                                        <td></td>
                                        <td><fmt:message key="modal.form.destinationCity" /></td>
                                        <td><select class="combobox" style="width : 180px;" id="city">
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.lineManager" /> :</td>
                                        <td id="lineManager"></td>
                                        <td></td>
                                        <td><fmt:message key="modal.form.projectManager" /></td>
                                        <td><select class="combobox" style="width : 180px;" id="projectManager"<c:if test="${!deproles.contains('Travel Department')}">
                                                    disabled
                                                </c:if>>
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.hotelName" /> :</td>
                                        <td>
                                            <select class="combobox" style="width : 148px;" id="hotelName">
                                            </select>
                                            <a class="btn btn-mini" onclick='$("#pop1").popover("toggle")' id="pop1" rel="popover" >
                                                <i class="icon-plus-sign"></i>
                                            </a>

                                        </td></td>           
                                        <td></td>
                                        <td><fmt:message key="modal.form.hotelWebSite" /> :</td>
                                        <td><input type="text" disabled style="width : 170px;" id="hotelSite"></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.customer" /></td>
                                        <td><select class="combobox" style="width : 180px;" id="customer">
                                            </select></td>
                                        <td></td>
                                        <td><label class="checkbox">
                                                <input type="checkbox" id="car"/><fmt:message key="modal.form.car" />
                                            </label></td>
                                        <td><label class="checkbox">
                                                <input type="checkbox" id="payByCash" <c:if test="${!deproles.contains('Travel Department')}">
                                                       disabled
                                                    </c:if>/><fmt:message key="modal.form.payByCash" />
                                            </label></td>
                                    </tr>
                                </tbody>
                            </table>
                            <br><br><br>
                        </form>
                    </div>
                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse"
                           data-parent="#accordion3" href="#collapseTwo1">
                            <h4>
                                <fmt:message key="modal.form.statushistory" />
                            </h4>
                        </a>
                    </div>
                    <div id="collapseTwo1" class="accordion-body collapse">
                        <div class="accordion-inner">

                            <table class="table table-bordered table-condensed">
                                <thead>
                                    <tr>
                                        <th><fmt:message key="table.statusHistory.date" /></th>
                                        <th><fmt:message key="table.statusHistory.newStatus" /></th>
                                        <th><fmt:message key="table.statusHistory.who" /></th>
                                        <th><fmt:message key="table.statusHistory.comment" /></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    <tr>
                                        <td>New York U.S.A</td>
                                        <td>12.06.2012</td>
                                        <td>12.08.2012</td>
                                        <td>Entering</td>
                                    </tr>
                                    <tr>
                                        <td>Mexico Mexico</td>
                                        <td>12.06.2012</td>
                                        <td>12.08.2012</td>
                                        <td>Entering</td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="pagination span4 offset3">
                                    <ul>
                                        <li><a href="#">&larr;</a></li>
                                        <li class="active"><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li class="disabled"><a href="#">...</a></li>
                                        <li><a href="#">20</a></li>
                                        <li><a href="#">21</a></li>
                                        <li><a href="#">&rarr;</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn btn-success" onclick='processTRF("3")'>Commit</a> 
            <a href="#" class="btn btn-success" onclick='processTRF("4")'>Complete</a> 
            <a href="#" class="btn btn-primary" onclick='processTRF("0")'>Save changes</a> 
            <a href="#" class="btn btn-danger" onclick='processTRF("1")'>Reject</a> 
            <a href="#" class="btn btn-danger" onclick='processTRF("2")'>Cancel</a>
        </div>
    </div>
