package com.liferay.training.gradebook.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;

/**
 * @author luis
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.training",
                "com.liferay.portlet.instanceable=false",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.css-class-wrapper=gradebook-portlet",
                "javax.portlet.display-name=Gradebook",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user",
                "javax.portlet.init-param.add-process-action-success-acton=false"
        },
        service = Portlet.class
)
public class GradebookPortlet extends MVCPortlet {
}