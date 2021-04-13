<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:renderURL var="vista1URL" >
	<portlet:param name="mvcRenderCommandName" value="render1Command" />
</portlet:renderURL>
<h2>Vista 1</h2>
<a href="${vista1URL}" >Llamar a vista1.jsp</a>
<p>${vista1URL}</p>
<br>
<portlet:renderURL var="vista2URL" >
	<portlet:param name="mvcRenderCommandName" value="render2Command" />
</portlet:renderURL>
<h2>Vista 2</h2>
<a href="${vista2URL}" >Llamar a vista2.jsp</a>
<p>${vista2URL}</p>