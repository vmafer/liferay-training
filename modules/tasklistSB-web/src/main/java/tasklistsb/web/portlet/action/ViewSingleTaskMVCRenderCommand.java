package tasklistsb.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import org.osgi.service.component.annotations.Reference;
import tasklistsb.model.Task;
import tasklistsb.service.TaskService;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.text.DateFormat;

public class ViewSingleTaskMVCRenderCommand implements MVCRenderCommand {

    @Reference
    private TaskService _taskService;
    @Reference
    private Portal _portal;
    @Reference
    private UserLocalService _userLocalService;

    public ViewSingleTaskMVCRenderCommand() {
    }

    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
        long taskId = ParamUtil.getLong(renderRequest, "taskId", 0L);

        try {
            Task task = this._taskService.getTask(taskId);
            DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("EEEEE, MMMMM dd, yyyy");
            renderRequest.setAttribute("task", task);
            renderRequest.setAttribute("createDate", dateFormat.format(task.getCreateDate()));
            PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
            String redirect = renderRequest.getParameter("redirect");
            portletDisplay.setShowBackIcon(true);
            portletDisplay.setURLBack(redirect);
            return "/task/view_task.jsp";
        } catch (PortalException var10) {
            throw new PortletException(var10);
        }
    }
}
