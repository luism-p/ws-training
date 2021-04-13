package ciclo.vida.training.command.renders;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import ciclo.vida.training.command.constants.CicloVidaTrainingCommandPortletKeys;

@Component(
		immediate = true,
		property = {
			
			"javax.portlet.name=" + CicloVidaTrainingCommandPortletKeys.CicloVidaTrainingCommand,
			"mvc.command.name=render2Command"
		},
		service = MVCRenderCommand.class
	)
public class CommandVista2Render implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		System.out.println("Este es el render command de vista2.jsp");
		
		return "/vista2.jsp";
	}

}
