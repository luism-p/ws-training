<%@ page import="com.liferay.training.lifecycle.constants.LifeCycleTestPortletKeys" %>
<%@ include file="./init.jsp" %>

<portlet:renderURL var="renderFirst">
    <portlet:param name="mvcRenderCommandName" value="<%=LifeCycleTestPortletKeys.MVC_RENDER_COMMAND_FIRST%>"/>
</portlet:renderURL>

<portlet:renderURL var="renderSecond">
    <portlet:param name="mvcRenderCommandName" value="<%=LifeCycleTestPortletKeys.MVC_RENDER_COMMAND_SECOND%>"/>
</portlet:renderURL>


<div class="">
    <aui:button-row cssClass="d-flex justify-content-center">
        <aui:button type="button" name="render-first" href="${renderFirst}" value="First"/>
        <aui:button type="button" name="render-second" href="${renderSecond}" value="Second"/>
        <aui:button type="button" name="showModal" value="Show Modal" cssClass="btn btn-primary"/>
    </aui:button-row>


    <div class="yui3-skin-sam">
        <div id="modal"></div>
    </div>

    <aui:input name="ckeck" type="hidden"/>


</div>

<script>

    let checkBox

    YUI().use(
        'aui-modal',
        function (Y) {
            var nodeCheck = '<div class="form-check"><input class="form-check-input" type="checkbox" value="" id="defaultCheck1"><label class="form-check-label" for="defaultCheck1">Default checkbox</label></div>';

            var modal = new Y.Modal(
                {
                    bodyContent: nodeCheck,
                    centered: true,
                    destroyOnHide: false,
                    headerContent: '<h3>Print</h3>',
                    modal: true,
                    render: '#modal',
                    resizable: {
                        handles: 'b, r'
                    },
                    visible: true,
                    width: 450
                }
            ).render();

            modal.addToolbar(
                [
                    {
                        label: 'Cerrar',
                        on: {
                            click: function () {

                                if ($('#defaultCheck1').is(':checked')) {
                                    $('#<portlet:namespace/>ckeck').val('true').trigger('change');
                                    modal.hide();
                                }
                            }
                        }
                    }
                ]
            );

            Y.one('#<portlet:namespace/>showModal').on(
                'click',
                function () {


                    modal.show();
                }
            );

            modal.hide();
        }
    );

</script>


