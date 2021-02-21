package com.liferay.training.lifecycle.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.training.lifecycle.constants.LifeCycleTestPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * @author luism on 21/02/2021
 * @project ws-training
 */
@Component(
        property = {
                "javax.portlet.name=" + LifeCycleTestPortletKeys.LIFECYCLETEST,
                "mvc.command.name="+LifeCycleTestPortletKeys.MVC_ACTION_COMMAND_SECOND
        },
        service = MVCActionCommand.class
)
public class SecondActionCommand implements MVCActionCommand{
    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        System.out.println("***** ActionCommand second *****");
        return Boolean.TRUE;
    }
}
