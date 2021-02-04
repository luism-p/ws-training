<%@ include file="/init.jsp" %>

<p>
    <b><liferay-ui:message key="claytest.caption"/></b>
</p>

<aui:form name="fm" validateOnBlur="true">
    <aui:fieldset>
        <aui:input name="text" type="text">
			<aui:validator name="gmail"/>
        </aui:input>

        <aui:input type="text" name="fecha" label="fecha">

            <aui:validator name="validDate" />
        </aui:input>
    </aui:fieldset>

</aui:form>

<aui:script>
    YUI().use(
            'aui-datepicker',
            function(Y) {
                new Y.DatePicker(
                        {
                            trigger: '#<portlet:namespace/>fecha',
                            mask: '%d/%m/%Y',
                            popover: {
                                zIndex: 9999
                            },
                            on: { selectionChange: function(event) {
                            }
                            }
                        }
                );
            }
    );
</aui:script>
