package com.liferay.training.gradebook.web.portlet.render;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.AssignmentService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author luis on 26/11/20
 * @project ws-training
 * MVC Command for showing edit assignment view.
 *
 * @author liferay
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK,
                "mvc.command.name=" + MVCCommandNames.EDIT_ASSIGNMENT
        },
        service = MVCRenderCommand.class
)
public class EditAssignmentMVCRenderCommand implements MVCRenderCommand {

    @Reference
    private AssignmentService _assignmentService;

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse)
            throws PortletException {

        Assignment assignment = null;

        long assignmentId = ParamUtil.getLong(renderRequest, GradebookPortletKeys.ASSIGNMENT_ID, 0);

        if (assignmentId > 0) {
            try {

                // Call the service to get the assignment for editing.

                assignment = _assignmentService.getAssignment(assignmentId);
            } catch (PortalException nsae) {
                nsae.printStackTrace();
            }
        }

        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

        // Set back icon visible.

        PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

        portletDisplay.setShowBackIcon(true);

        String redirect = renderRequest.getRenderParameters().getValue("redirect");

        portletDisplay.setURLBack(redirect);

        // Set assignment to the request attributes.

        renderRequest.setAttribute(GradebookPortletKeys.ASSIGNMENT, assignment);
        renderRequest.setAttribute("assignmentClass", Assignment.class);

        return "/assignment/edit_assignment.jsp";
    }
}