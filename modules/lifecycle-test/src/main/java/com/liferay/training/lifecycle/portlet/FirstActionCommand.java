package com.liferay.training.lifecycle.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
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
                "mvc.command.name="+LifeCycleTestPortletKeys.MVC_ACTION_COMMAND_FIRST
        },
        service = MVCActionCommand.class
)
public class FirstActionCommand implements MVCActionCommand{
    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        System.out.println("***** ActionCommand first *****");

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String url = "";
        try {

            url = PortalUtil.getLayoutURL(themeDisplay);
        }
        catch (PortalException e) {
            e.printStackTrace();
        }
        actionRequest.setAttribute("url", url);
        actionResponse.getRenderParameters().setValue("jspPage", "/response.jsp");
        return Boolean.TRUE;
    }
}
