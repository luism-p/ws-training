<%@ page import="com.liferay.training.lifecycle.constants.LifeCycleTestPortletKeys" %>
<%@ include file="/init.jsp" %>

<portlet:renderURL var="renderFirst" >
	<portlet:param name="mvcRenderCommandName" value="<%=LifeCycleTestPortletKeys.MVC_RENDER_COMMAND_FIRST%>"/>
</portlet:renderURL>

<portlet:renderURL var="renderSecond" >
	<portlet:param name="mvcRenderCommandName" value="<%=LifeCycleTestPortletKeys.MVC_RENDER_COMMAND_SECOND%>"/>
</portlet:renderURL>


<div class="">
	<aui:button-row cssClass="d-flex justify-content-center">
		<aui:button type="button" name="render-first" href="${renderFirst}" value="First"/>
		<aui:button type="button" name="render-second" href="${renderSecond}" value="Second"/>
	</aui:button-row>

</div>



