package com.liferay.training.portletmodule.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.training.portletmodule.constants.SimpleMVCPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + SimpleMVCPortletKeys.SIMPLEMVC,
                "mvc.command.name=handleForm"
        },
        service = MVCActionCommand.class
)
public class HandleFormMVCActionCommand implements MVCActionCommand {
    @Override
    public boolean processAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
            throws PortletException {

        _handleActionCommand(actionRequest);

        return true;
    }

    private void _handleActionCommand(ActionRequest actionRequest) {

        System.out.println("HandleFormMVCActionCommand.doProcessAction()");
        String backgroundColor = ParamUtil.getString(actionRequest,"backgroundColor");

        System.out.println("backgroundColor = " + backgroundColor);

    }
}