<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:setBundle
    basename="${localeBean.bundlePath}_${sessionScope.bundle}" />
<div id="myModal" class="modal hide fade">
    <div class="modal-header">
        <a class="close" data-dismiss="modal">&times;</a>
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
                        <form class="well">
                            <table class="table table-condensed">
                                <tbody>
                                    <tr>
                                        <td><fmt:message key="modal.form.employeeName" /></td>
                                        <td id="employee"></td>
                                        <td><fmt:message key="modal.form.office" /></td>
                                        <td id="office"></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.departureDate" /></td>
                                        <td><input type="text" class="" id="beginDate" value="fff">XX</td>
                                        <td><fmt:message key="modal.form.returnDate" /> :</td>
                                        <td><input type="text" class="" id="endDate">XX</td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.destinationCountry" /></td>
                                        <td><select class="combobox" id="country">
                                            </select></td>
                                        <td><fmt:message key="modal.form.destinationCity" /></td>
                                        <td><select class="combobox" id="city">
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.lineManager" /> :</td>
                                        <td id="lineManager"></td>
                                        <td><fmt:message key="modal.form.projectManager" /></td>
                                        <td <c:if test="${!deproles.contains('Travel Department')}">
                                                              disabled
                                                               </c:if> ><select class="combobox" id="projectManager">
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.hotelName" /> :</td>
                                        <td><input type="text" class="span2" id="hotelName"></td>
                                        <td><fmt:message key="modal.form.hotelWebSite" /> :</td>
                                        <td><input type="text" class="span2" id="hotelWebSite"></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.customer" /></td>
                                        <td id="customer"><select class="combobox">
                                                <option>Patric kruz</option>
                                                <option value="PA"></option>
                                                <option value="CT"></option>
                                            </select></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td><label class="checkbox">
                                                <input type="checkbox" <c:if test="${!deproles.contains('Travel Department')}">
                                                                       disabled
                                                                       </c:if> id="car"/><fmt:message key="modal.form.car" />
                                            </label>
                                        </td>
                                        <td></td>
                                        <td><label class="checkbox">
                                                <input type="checkbox" id="payByCash"/><fmt:message key="modal.form.payByCash" />
                                            </label>
                                        </td>
                                        <td></td>
                                    </tr>
                                </tbody>
                            </table>
                    </div>
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
        <a href="#" class="btn btn-success">Commit</a> <a href="#"
                                                          class="btn btn-success">Complete</a> <a href="#"
                                                          class="btn btn-primary">Save changes</a> <a href="#"
                                                          class="btn btn-danger">Reject</a> <a href="#" class="btn"
                                                          data-dismiss="modal">Cancel</a>
    </div>
</div>