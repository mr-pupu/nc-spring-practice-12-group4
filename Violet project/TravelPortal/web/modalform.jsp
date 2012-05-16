<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:setBundle
    basename="${localeBean.bundlePath}_${sessionScope.bundle}" />
<div id="editTrfModal" class="modal hide fade" style="width: 720px; margin: -250px 0 -0 -380px;">
    <div class="modal-header">
        <a href="#" class="close" onclick="$('#editTrfModal').modal('hide')">&times;</a>
        <h3 id="editTrfmodalTitle">
            <fmt:message key="modal.form.header" />
        </h3>
    </div>
    <div>
        <div align="right">
            <a href="#" class="btn btn-primary" onclick='processTRF("0")'>Save</a> 
            <a href="#" class="btn btn-success" onclick='processTRF("3")'>Commit</a>
            <a href="#" class="btn btn-danger" onclick='processTRF("2")'>Cancel</a>
            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;   
        </div>
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

                            <table cellspacing="10" >
                                <tbody>
                                    <tr>
                                        <td width="120px"><fmt:message key="modal.form.employeeName" />:</td>
                                        <td><select class="combobox" onchange="employeeChange()" 
                                                    style="width : 180px;" id="employee">
                                            </select></td>
                                        <td><span style="padding:0px 20px;"></span></td>
                                        <td width="120px"><fmt:message key="modal.form.office" />:</td>
                                        <td id="office"></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.departureDate" />:</td>
                                        <td> <div class="input-append" rel="calendarDiv">
                                                <input style="width : 134px;" id="flightBeginDate" size="16" type="text"><span
                                                    class="add-on"><img src="<%=request.getContextPath()%>/assets/img/calendar/calen7.jpg" 
                                                                    width=22px; height=22px;/></span>
                                            </div></td>
                                        <td></td>
                                        <td><fmt:message key="modal.form.returnDate" />:</td>
                                        <td> <div class="input-append" rel="calendarDiv">
                                                <input style="width : 134px;" id="flightEndDate" size="16" type="text"><span
                                                    class="add-on"><img src="<%=request.getContextPath()%>/assets/img/calendar/calen7.jpg" 
                                                                    width=22px; height=22px;/></span>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.destinationCountry" />:</td>
                                        <td><select class="combobox" onchange="countryChange()" 
                                                    style="width : 180px;" id="country">
                                            </select></td>
                                        <td></td>
                                        <td><fmt:message key="modal.form.destinationCity" />:</td>
                                        <td><select class="combobox" onchange="cityChange()"
                                                    style="width : 180px;" id="city">
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.lineManager" />:</td>
                                        <td id="lineManager"></td>
                                        <td></td>
                                        <td><fmt:message key="modal.form.projectManager" />:</td>
                                        <td><select class="combobox" style="width : 180px;" id="projectManager">
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.hotelName" />:</td>
                                        <td>
                                            <select class="combobox" onchange="hotelChange()" style="width : 148px;" id="hotelName">
                                            </select>
                                            <a class="btn btn-mini" onclick='$("#pop1").popover("toggle")' id="pop1" rel="popover" >
                                                <i class="icon-plus-sign"></i>
                                            </a></td>          
                                        <td></td>
                                        <td><fmt:message key="modal.form.hotelWebSite" />:</td>
                                        <td id="hotelSite"></td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message key="modal.form.customer" />:</td>
                                        <td><select class="combobox" style="width : 180px;" id="customer">
                                            </select></td>
                                        <td></td>
                                        <td><label class="checkbox">
                                                <input type="checkbox" id="carRental"/>
                                                <fmt:message key="modal.form.car" />
                                            </label></td>
                                        <td><label class="checkbox">
                                                <input type="checkbox" id="payByCash"/>
                                                <fmt:message key="modal.form.payByCash" />
                                            </label></td>
                                    </tr>
                                </tbody>
                            </table>
                            <br>
                            Commentary: &nbsp;
                            <textarea id="commentary" wrap="virtual" cols="100" ROWS="3" 
                                      style="width :450px">Changing status commentary...</textarea>
                            <br>
                        </form>
                    </div>
                </div>
                <div id="footerAccordion"  class="accordion-group">
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
                            <div>                 
                                <table id="statushistory"></table>
                                <div id="statushistorypager"></div> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript"
src="<%=request.getContextPath()%>/assets/js/traveljs/status-history.js"></script>