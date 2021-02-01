package com.liferay.training.soy.portlet.portlet;

import com.liferay.training.soy.portlet.constants.TestSoyPortletKeys;

import com.liferay.portal.portlet.bridge.soy.SoyPortletRegister;

import java.io.IOException;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author luism
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.display-name=test-soy Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=View",
		"javax.portlet.name=" + TestSoyPortletKeys.TestSoy,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	}
)
public class TestSoySoyPortletRegister implements SoyPortletRegister {
}