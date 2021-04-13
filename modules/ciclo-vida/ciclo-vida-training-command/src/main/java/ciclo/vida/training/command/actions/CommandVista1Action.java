package ciclo.vida.training.command.actions;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import ciclo.vida.training.command.constants.CicloVidaTrainingCommandPortletKeys;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + CicloVidaTrainingCommandPortletKeys.CicloVidaTrainingCommand,
			"javax.portlet.name=CicloVidaTrainingCommandAdvance",
			"mvc.command.name=action1Command"
		},
		service = MVCActionCommand.class
	)

public class CommandVista1Action implements MVCActionCommand{

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		
		System.out.println("Llamada a action1Command");
		boolean result;
		
		if(Math.random()>0.5){
			SessionErrors.add(actionRequest, "error");
			result = false;
			System.out.println("SessionErrors=error");
		}else{
			SessionMessages.add(actionRequest, "success");
			result = true;
			System.out.println("SessionMessages=success");
		}
		
		actionResponse.setRenderParameter("mvcRenderCommandName", "render3Command");
		
		return result;
	}

}
