package ciclo.vida.training.portlet;

import ciclo.vida.training.constants.CicloVidaTrainingPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author jfernach
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ciclo-vida-training Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CicloVidaTrainingPortletKeys.CicloVidaTraining,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CicloVidaTrainingPortlet extends MVCPortlet {
	
	@Override
	public void init(PortletConfig config) throws PortletException {
		System.out.println("Se ha desplegado nuestro Portlet y esto es la llamada al init()");
		super.init(config);
	}
	
	@Override
	public void destroy() {
		System.out.println("Se ha eliminado el portlet del contenedor y estoes la llamada al destroy()");
		super.destroy();
	}
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		
		
		String param1 = ParamUtil.get(renderRequest, "param1", "defaultValue");
		if ("value1".equals(param1)){
			System.out.println("Render desde action con param1");
		}else{
			System.out.println("Render por defecto MVCPortlet");
		}
		
		super.render(renderRequest, renderResponse);
	}
	
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		System.out.println("ProcessAction por defecto MVCPortlet");
		actionResponse.setRenderParameter("param1", "value1");
		super.processAction(actionRequest, actionResponse);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		System.out.println("ServeResource por defecto MVCPortlet");
		
		resourceResponse.getWriter().println("<h2>respuesta del serveResource</h2>");
		resourceResponse.setContentType(ContentTypes.TEXT_HTML);
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}