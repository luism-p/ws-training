<%@ include file="/init.jsp" %>

<p>
    <b><liferay-ui:message key="claytest.caption"/></b>
</p>

<aui:form name="fm" validateOnBlur="true">
    <aui:fieldset>
        <aui:input name="text" type="text">
			<aui:validator name="gmail"/>
        </aui:input>
    </aui:fieldset>

</aui:form>
