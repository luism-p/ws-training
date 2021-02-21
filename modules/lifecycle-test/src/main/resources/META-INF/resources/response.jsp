<%@ include file="./init.jsp" %>

<div class="alert alert-success">
    <liferay-ui:message key="Response ok!!"/>
</div>

<portlet:renderURL var="viewURL">
    <portlet:param name="mvcPath" value="/view.jsp"/>
</portlet:renderURL>

<aui:button type="button" href="${url}" value="Return"/>
