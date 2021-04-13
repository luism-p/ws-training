<%@ include file="/META-INF/resources/init.jsp" %>

<p>
	<b><liferay-ui:message key="ciclo-vida-training-command-advance.caption"/></b>
</p>

<portlet:actionURL name="action1Command" var="action1URL" />


<a href="${action1URL}" >Llamar a action1 command</a>
<p>${action1URL}</p>