package com.liferay.training.language.portlet;

import com.liferay.portal.kernel.language.UTF8Control;

import java.util.Enumeration;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
/**
 * Clase de internacionalizacion
 */
@Component(property = { "language.id=en_US" }, service = ResourceBundle.class)

/**
 * Hook para internacionalizacion de idiomas
 *
 * @author jbodegam
 *
 */
public class CustomLanguageHook extends ResourceBundle {

    @Override
    public Enumeration<String> getKeys() {
        return _resourceBundle.getKeys();
    }

    @Override
    protected Object handleGetObject(String arg0) {
        return _resourceBundle.getObject(arg0);
    }

    private final ResourceBundle _resourceBundle = ResourceBundle.getBundle("content.Language_en_US",
            UTF8Control.INSTANCE);

}
