<%@ include file="/init.jsp" %>

<div id="<portlet:namespace />"></div>

<aui:script require="react-portlet@1.0.0">
	const user = {};
user.name = "${name}";
user.lastName = "${lastName}";
user.message = "${message}";
	reactPortlet100.default('<portlet:namespace />', user);
</aui:script>