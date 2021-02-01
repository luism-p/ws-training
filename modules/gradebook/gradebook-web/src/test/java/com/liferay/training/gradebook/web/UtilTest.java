package com.liferay.training.gradebook.web;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.training.gradebook.web.Util;
import com.liferay.training.gradebook.web.portlet.render.ViewAssignmentsMVCRenderCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.extension.listener.AnnotationEnabler;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.Mock;
import org.powermock.core.classloader.annotations.PowerMockListener;

import static org.powermock.api.mockito.PowerMockito.*;

/**
 * @author luism on 31/01/2021
 * @project ws-training
 */
@RunWith(MockitoJUnitRunner.class)
public class UtilTest {

    Util util;
    @Before
    public void setUp() throws Exception {
        util = new Util();
    }

    @org.junit.Test
    public void getNum1(){
        JSONArray jsonArray = mock(JSONArray.class);
        JSONObject jsonObject = mock(JSONObject.class);
        when(jsonArray.getJSONObject(0)).thenReturn(jsonObject);
        when(jsonObject.getString("num", "")).thenReturn("1");
        String result = util.getNum(jsonArray);

        Assert.assertSame("1", result);

    }

}
