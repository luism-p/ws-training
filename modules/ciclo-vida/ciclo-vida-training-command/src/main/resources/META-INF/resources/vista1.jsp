<%@ include file="/META-INF/resources/init.jsp" %>

<p>
	<h1>Cargada vista1.jsp</h1>
</p>

<portlet:resourceURL id="resource1Command" var="resource1URL" />

<h2>Vista 1</h2>
<a href="${resource1URL}" >Llamar a resource1 command</a>
<p>${resource1URL}</p>