package com.liferay.training.react.portlet;

import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.training.react.constants.ReactPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;

/**
 * @author luism
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.Liferay Training",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ReactPortletKeys.React,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ReactPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		renderRequest.setAttribute("name", "Luis");
		renderRequest.setAttribute("lastName", "perez");
		renderRequest.setAttribute("message", "Feedback");
		System.out.println("React portlet");
		super.render(renderRequest, renderResponse);
	}

//	@Override
//	public void doView(
//			RenderRequest renderRequest, RenderResponse renderResponse)
//			throws IOException, PortletException {
//
//		renderRequest.setAttribute(
//				"mainRequire",
//				_npmResolver.resolveModuleName("react-portlet-2") + " as main");
//
//		super.doView(renderRequest, renderResponse);
//	}
//
//	@Reference
//	private NPMResolver _npmResolver;
}