package tasklistsb.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import tasklistsb.service.TaskService;
import tasklistsb.web.constants.TasklistSBWebPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + TasklistSBWebPortletKeys.TASKLISTSBWEB,
                "mvc.command.name=/gradebook/task/delete"
        },
        service = MVCActionCommand.class
)
public class DeleteTaskMVCActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {

        // Get task id from request.

        long taskId = ParamUtil.getLong(actionRequest, "taskId");

        try {

            // Call service to delete the task.

            _taskService.deleteTask(taskId);

            // Set success message.

            SessionMessages.add(actionRequest, "taskDeleted");
        }
        catch (PortalException pe) {

            // Set error messages from the service layer.

            SessionErrors.add(actionRequest, "serviceErrorDetails", pe);
        }

    }

    @Reference
    protected TaskService _taskService;
}
