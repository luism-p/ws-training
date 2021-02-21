<%@ page import="com.liferay.training.lifecycle.constants.LifeCycleTestPortletKeys" %>
<%@ include file="../init.jsp"%>
<div>
    <h3>First page</h3>
</div>

<portlet:renderURL var="viewURL">
    <portlet:param name="mvcPath" value="/view.jsp"/>
</portlet:renderURL>

<portlet:actionURL var="actionFirst" name="<%=LifeCycleTestPortletKeys.MVC_ACTION_COMMAND_FIRST%>"/>
<aui:form name="first" action="${actionFirst}">

<div class="">
    <aui:button-row cssClass="d-flex justify-content-center">
        <aui:button type="button" href="${viewURL}" value="Return"/>
        <aui:button type="submit" name="action-first" value="First"/>
    </aui:button-row>

</div>

</aui:form>
