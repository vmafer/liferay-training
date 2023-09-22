package tasklistsb.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import tasklistsb.exception.TaskValidationException;
import tasklistsb.model.Task;
import tasklistsb.service.TaskService;
import tasklistsb.web.constants.MVCCommandNames;
import tasklistsb.web.constants.TasklistSBWebPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.util.Date;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + TasklistSBWebPortletKeys.TASKLISTSBWEB,
                "mvc.command.name=" + MVCCommandNames.ADD_TASK
        },
        service = MVCActionCommand.class
)
public class AddTaskMVCActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {

        ThemeDisplay themeDisplay =
                (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Task.class.getName(), actionRequest);

        // Get parameters from the request.

        long companyId = ParamUtil.getLong(actionRequest, "companyId");

        String title = ParamUtil.getString(actionRequest, "title");

        String userName = ParamUtil.getString(actionRequest, "userName", null);

        try {

            // Call the service to add a new task.

            _taskService.addTask(
                    themeDisplay.getScopeGroupId(), companyId, title, userName, serviceContext);

            // Set the success message.

            SessionMessages.add(actionRequest, "taskAdded");

            sendRedirect(actionRequest, actionResponse);
        }
        catch (TaskValidationException ave) {

            // Get error messages from the service layer.

            /*
            ave.getErrors().forEach(key -> SessionErrors.add(actionRequest, key));

            ave.printStackTrace();
            */

            actionResponse.setRenderParameter(
                    "mvcRenderCommandName", MVCCommandNames.EDIT_TASK);

        }
        catch (PortalException pe) {

            // Set error messages from the service layer.

            SessionErrors.add(actionRequest, "serviceErrorDetails", pe);

            pe.printStackTrace();



            actionResponse.setRenderParameter(
                    "mvcRenderCommandName", MVCCommandNames.EDIT_TASK);
        }
    }

    @Reference
    public TaskService _taskService;
}
