package tasklistsb.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import tasklistsb.exception.NoSuchTaskException;
import tasklistsb.model.Task;
import tasklistsb.service.TaskService;
import tasklistsb.web.constants.MVCCommandNames;
import tasklistsb.web.constants.TasklistSBWebPortletKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + TasklistSBWebPortletKeys.TASKLISTSBWEB,
                "mvc.command.name=" + MVCCommandNames.EDIT_TASK
        },
        service = MVCRenderCommand.class
)
public class EditTaskMVCRenderCommand implements MVCRenderCommand {
    @Override
    public String render(
            RenderRequest renderRequest, RenderResponse renderResponse)
            throws PortletException {

        Task task = null;

        long taskId = ParamUtil.getLong(renderRequest, "taskId", 0);

        if (taskId > 0) {
            try {

                // Call the service to get the task for editing.

                task = _taskService.getTask(taskId);
            }
            catch (NoSuchTaskException nsae) {
                nsae.printStackTrace();
            }
            catch (PortalException pe) {
                pe.printStackTrace();
            }
        }

        ThemeDisplay themeDisplay =
                (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

        // Set back icon visible.

        PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

        portletDisplay.setShowBackIcon(true);

        String redirect = renderRequest.getParameter("redirect");

        portletDisplay.setURLBack(redirect);

        // Set task to the request attributes.

        renderRequest.setAttribute("task", task);
        renderRequest.setAttribute("taskClass", Task.class);

        return "/task/edit_task.jsp";
    }

    @Reference
    private TaskService _taskService;
}
