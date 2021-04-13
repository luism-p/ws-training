<%@ include file="/META-INF/resources/init.jsp" %>


<portlet:actionURL var="actionURL" />
<portlet:resourceURL var="resourceURL" />

<p>
	<b><liferay-ui:message key="ciclo-vida-training.caption"/></b>
</p>

<a href="${actionURL}">LLamar a actionURL</a>
<p>${actionURL}<p>
<br/>
<a href="${resourceURL}">LLamar a resourceURL</a>
<p>${resourceURL}</p>


