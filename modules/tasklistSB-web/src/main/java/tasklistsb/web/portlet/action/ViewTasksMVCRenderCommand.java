package tasklistsb.web.portlet.action;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import org.osgi.service.component.annotations.Reference;
import tasklistsb.model.Task;
import tasklistsb.service.TaskService;
import tasklistsb.web.display.context.TasksManagementToolbarDisplayContext;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

public class ViewTasksMVCRenderCommand implements MVCRenderCommand {

    @Reference
    protected TaskService _taskService;
    @Reference
    private Portal _portal;

    public ViewTasksMVCRenderCommand() {
    }

    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        this.addTaskListAttributes(renderRequest);
        this.addManagementToolbarAttributes(renderRequest, renderResponse);
        return "/view.jsp";
    }

    private void addTaskListAttributes(RenderRequest renderRequest) {
        ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
        int currentPage = ParamUtil.getInteger(renderRequest, "cur", 1);
        int delta = ParamUtil.getInteger(renderRequest, "delta", SearchContainer.DEFAULT_DELTA);
        int start = (currentPage > 0 ? currentPage - 1 : 0) * delta;
        int end = start + delta;
        String orderByCol = ParamUtil.getString(renderRequest, "orderByCol", "title");
        String orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");
        OrderByComparator<Task> comparator = OrderByComparatorFactoryUtil.create("Task", new Object[]{orderByCol, !"asc".equals(orderByType)});
        String keywords = ParamUtil.getString(renderRequest, "keywords");
        List<Task> tasks = this._taskService.getTasksByKeywords(themeDisplay.getScopeGroupId(), keywords, start, end, comparator);
        renderRequest.setAttribute("tasks", tasks);
        renderRequest.setAttribute("taskCount", this._taskService.getTasksCountByKeywords(themeDisplay.getScopeGroupId(), keywords));
    }

    private void addManagementToolbarAttributes(RenderRequest renderRequest, RenderResponse renderResponse) {
        LiferayPortletRequest liferayPortletRequest = this._portal.getLiferayPortletRequest(renderRequest);
        LiferayPortletResponse liferayPortletResponse = this._portal.getLiferayPortletResponse(renderResponse);
        TasksManagementToolbarDisplayContext tasksManagementToolbarDisplayContext = new TasksManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, this._portal.getHttpServletRequest(renderRequest));
        renderRequest.setAttribute("tasksManagementToolbarDisplayContext", tasksManagementToolbarDisplayContext);
    }
}
