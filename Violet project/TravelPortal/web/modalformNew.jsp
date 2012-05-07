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

                        <div class="row-fluid">
                            <div class="span6" style='background-color:blue'>

                                <form class="form-horizontal">
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="employee"><fmt:message key="modal.form.employeeName" /></label>
                                            <div class="controls">
                                                <label class="control-label" id="employee">fff</label>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="employee"><fmt:message key="modal.form.departureDate" /></label>
                                            <div class="controls">
                                                <div class="input-append" rel="calendarDiv">
                                                    <input class="span2" id="beginDate" size="16" type="text"><span
                                                        class="add-on"><i class="icon-calendar"></i></span>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="span6" style='background-color:green'>

                                <form class="form-horizontal">
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="employee"><fmt:message key="modal.form.office" /></label>
                                            <div class="controls">
                                                <label class="control-label" id="office">fff</label>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="employee"><fmt:message key="modal.form.returnDate" /> :</label>
                                            <div class="controls">
                                                <div class="input-append" rel="calendarDiv">
                                                    <input class="span2" id="endDate" size="16" type="text"><span
                                                        class="add-on"><i class="icon-calendar"></i></span>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>

                            </div>
                        </div>
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
            <a href="#" class="btn btn-success" onclick="processTRF()">Commit</a> 
            <a href="#" class="btn btn-success">Complete</a> 
            <a href="#" class="btn btn-primary">Save changes</a> 
            <a href="#" class="btn btn-danger">Reject</a> 
            <a href="#" class="btn">Cancel</a>
        </div>
    </div>
