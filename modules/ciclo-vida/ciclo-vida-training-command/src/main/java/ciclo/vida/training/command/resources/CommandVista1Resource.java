package ciclo.vida.training.command.resources;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ContentTypes;

import java.io.IOException;
import java.io.Writer;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import ciclo.vida.training.command.constants.CicloVidaTrainingCommandPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + CicloVidaTrainingCommandPortletKeys.CicloVidaTrainingCommand, "mvc.command.name=resource1Command"

}, service = MVCResourceCommand.class)
public class CommandVista1Resource implements MVCResourceCommand {

    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException {
        System.out.println("LLamada a resource1Command");

        try (Writer writer = resourceResponse.getWriter()) {
            JSONObject json = JSONFactoryUtil.createJSONObject();
            json.put("hola", "mundo");
            writer.write(json.toString());

        }
        catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        resourceResponse.setContentType(ContentTypes.APPLICATION_JSON);
        return false;
    }

}
