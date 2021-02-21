package com.liferay.training.lifecycle.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.training.lifecycle.constants.LifeCycleTestPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author luism on 21/02/2021
 * @project ws-training
 */
@Component(
        property = {
                "javax.portlet.name="+ LifeCycleTestPortletKeys.LIFECYCLETEST,
                "mvc.command.name="+LifeCycleTestPortletKeys.MVC_RENDER_COMMAND_SECOND
        },
        service = MVCRenderCommand.class
)
public class SecondRenderCommand implements MVCRenderCommand{
    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        System.out.println("***** RenderCommand second *****");
        return LifeCycleTestPortletKeys.PATH_JSP_SECOND;
    }
}
