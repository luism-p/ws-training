<%@ page import="com.liferay.training.lifecycle.constants.LifeCycleTestPortletKeys" %>
<%@include file="../init.jsp"%>
<div>
    <h3>Second page</h3>
</div>

<portlet:renderURL var="viewURL">
    <portlet:param name="mvcPath" value="/view.jsp"/>
</portlet:renderURL>
<portlet:actionURL var="actionSecond" name="<%= LifeCycleTestPortletKeys.MVC_ACTION_COMMAND_SECOND%>"/>
<aui:form name="first" action="${actionSecond}">

    <div class="">
        <aui:button-row cssClass="d-flex justify-content-center">
            <aui:button type="button" href="${viewURL}" value="Return"/>
            <aui:button type="submit" name="action-second" value="Second"/>
        </aui:button-row>

    </div>

</aui:form>

