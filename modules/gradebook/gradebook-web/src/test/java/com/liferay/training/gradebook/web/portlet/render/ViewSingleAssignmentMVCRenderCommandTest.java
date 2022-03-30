package com.liferay.training.gradebook.web.portlet.render;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.gradebook.service.AssignmentService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.text.DateFormat;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

class ViewSingleAssignmentMVCRenderCommandTest {

    @InjectMocks
    @Spy
    private ViewSingleAssignmentMVCRenderCommand renderCommand;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private AssignmentService assignmentService;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private DateFormat dateFormat;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private RenderRequest renderRequest;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private RenderResponse renderResponse;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private ThemeDisplay themeDisplay;

    private MockedStatic<DateFormatFactoryUtil> dateFormatFactoryUtilMockedStatic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dateFormatFactoryUtilMockedStatic = Mockito.mockStatic(DateFormatFactoryUtil.class, Mockito.RETURNS_MOCKS);
    }

    @AfterEach
    void tearDown() {
        dateFormatFactoryUtilMockedStatic.close();
    }

    @Test
    void render() throws PortletException {
        try (MockedStatic<PropsUtil> propsUtilMockedStatic = Mockito.mockStatic(PropsUtil.class, i -> null);//
                MockedStatic<ParamUtil> paramUtilMockedStatic = Mockito.mockStatic(ParamUtil.class, Mockito.RETURNS_MOCKS)) {
            Mockito.doReturn(Boolean.TRUE).when(themeDisplay).isSignedIn();
            Mockito.when(ParamUtil.getLong(Mockito.eq(renderRequest), Mockito.eq(GradebookPortletKeys.ASSIGNMENT_ID), Mockito.anyLong())).thenReturn(1L);
            Mockito.doReturn(themeDisplay).when(renderRequest).getAttribute(WebKeys.THEME_DISPLAY);
            String result = renderCommand.render(renderRequest, renderResponse);

            Assertions.assertEquals("/assignment/view_assignment.jsp", result);
            Mockito.verify(renderRequest, Mockito.times(3)).setAttribute(Mockito.anyString(), Mockito.any());

        }
    }

    @Test
    void return_error_render() throws PortletException {
        Mockito.doReturn(themeDisplay).when(renderRequest).getAttribute(WebKeys.THEME_DISPLAY);
        Mockito.doReturn(Boolean.FALSE).when(themeDisplay).isSignedIn();
        String result = renderCommand.render(renderRequest, renderResponse);

        Assertions.assertEquals("/error.jsp", result);
        Mockito.verify(renderRequest, Mockito.never()).setAttribute(Mockito.anyString(), Mockito.any());
    }

    @Test
    void catch_render() throws PortalException {
        try (MockedStatic<PropsUtil> propsUtilMockedStatic = Mockito.mockStatic(PropsUtil.class, i -> null);//
                MockedStatic<ParamUtil> paramUtilMockedStatic = Mockito.mockStatic(ParamUtil.class, Mockito.RETURNS_MOCKS)) {
            Mockito.doReturn(Boolean.TRUE).when(themeDisplay).isSignedIn();
            Mockito.when(ParamUtil.getLong(Mockito.eq(renderRequest), Mockito.eq(GradebookPortletKeys.ASSIGNMENT_ID), Mockito.anyLong())).thenReturn(1L);
            Mockito.doReturn(themeDisplay).when(renderRequest).getAttribute(WebKeys.THEME_DISPLAY);
            Mockito.doThrow(new PortalException()).when(assignmentService).getAssignment(Mockito.anyLong());

            Assertions.assertThrows(PortletException.class, () -> renderCommand.render(renderRequest, renderResponse));

        }
    }
}