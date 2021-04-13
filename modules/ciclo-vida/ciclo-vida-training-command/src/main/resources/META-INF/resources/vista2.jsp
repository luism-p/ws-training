<%@ include file="/META-INF/resources/init.jsp" %>

<p>
	<h1>Cargada vista2.jsp</h1>
</p>

<portlet:actionURL name="action1Command" var="action1URL" />

<h2>Vista 2</h2>
<a href="${action1URL}" >Llamar a action1 command</a>
<p>${action1URL}</p>