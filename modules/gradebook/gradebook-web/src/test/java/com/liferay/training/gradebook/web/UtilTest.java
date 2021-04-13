package com.liferay.training.gradebook.web;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONObjectWrapper;
import com.liferay.training.gradebook.web.Util;
import com.liferay.training.gradebook.web.portlet.render.ViewAssignmentsMVCRenderCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.powermock.api.extension.listener.AnnotationEnabler;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.Mock;
import org.powermock.core.classloader.annotations.PowerMockListener;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

import static org.powermock.api.mockito.PowerMockito.*;

/**
 * @author luism on 31/01/2021
 * @project ws-training
 */
@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(JSONFactoryUtil.class)
public class UtilTest {


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getNum1(){
        JSONArray jsonArray = mock(JSONArray.class);
        JSONObject jsonObject = mock(JSONObject.class);
        when(jsonArray.getJSONObject(0)).thenReturn(jsonObject);
        when(jsonObject.getString("num", "")).thenReturn("1");
        String result = Util.getNum(jsonArray);

        Assert.assertSame("1", result);

    }

    @Test
    public void testFactory() throws Exception {
        JSONObject jsonObject = PowerMockito.mock(JSONObject.class);
        mockStatic(JSONFactoryUtil.class);

        PowerMockito.when(JSONFactoryUtil.createJSONObject()).thenAnswer((invocation) -> jsonObject );





        JSONObject jsonObject1 = Util.createJson();

        Assert.assertEquals(jsonObject, jsonObject1);
    }

}
