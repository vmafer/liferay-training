package tasklistsb.web.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.BaseManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.*;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.*;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TasksManagementToolbarDisplayContext extends BaseManagementToolbarDisplayContext {

    private final PortalPreferences _portalPreferences;
    private final ThemeDisplay _themeDisplay;

    public TasksManagementToolbarDisplayContext(LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse, HttpServletRequest httpServletRequest) {
        super(liferayPortletRequest, liferayPortletResponse, httpServletRequest);
        this._portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(liferayPortletRequest);
        this._themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
    }

    public CreationMenu getCreationMenu() {
        return new CreationMenu() {
            {
                this.addDropdownItem((dropdownItem) -> {
                    dropdownItem.setHref(TasksManagementToolbarDisplayContext.this.liferayPortletResponse.createRenderURL(), new Object[]{"mvcRenderCommandName", "/tasklistSB/task/edit", "redirect", TasksManagementToolbarDisplayContext.this.currentURLObj.toString()});
                    dropdownItem.setLabel(LanguageUtil.get(TasksManagementToolbarDisplayContext.this.request, "add-TASK"));
                });
            }
        };
    }

    public String getClearResultsURL() {
        return this.getSearchActionURL();
    }

    public String getDisplayStyle() {
        String displayStyle = ParamUtil.getString(this.request, "displayStyle");
        if (Validator.isNull(displayStyle)) {
            displayStyle = this._portalPreferences.getValue("tasklistsb_web_portlet_TasklistSBWebPortlet", "tasks-display-style", "descriptive");
        } else {
            this._portalPreferences.setValue("tasklistsb_web_portlet_TasklistSBWebPortlet", "tasks-display-style", displayStyle);
            this.request.setAttribute("LIFERAY_SHARED_SINGLE_PAGE_APPLICATION_CLEAR_CACHE", Boolean.TRUE);
        }

        return displayStyle;
    }

    public String getOrderByCol() {
        return ParamUtil.getString(this.request, "orderByCol", "title");
    }

    public String getOrderByType() {
        return ParamUtil.getString(this.request, "orderByType", "asc");
    }

    public String getSearchActionURL() {
        PortletURL searchURL = this.liferayPortletResponse.createRenderURL();
        searchURL.setProperty("mvcRenderCommandName", "/tasklistSB/tasks/view");
        String navigation = ParamUtil.getString(this.request, "navigation", "entries");
        searchURL.setParameter("navigation", navigation);
        searchURL.setParameter("orderByCol", this.getOrderByCol());
        searchURL.setParameter("orderByType", this.getOrderByType());
        return searchURL.toString();
    }

    public List<ViewTypeItem> getViewTypeItems() {
        PortletURL portletURL = this.liferayPortletResponse.createRenderURL();
        portletURL.setParameter("mvcRenderCommandName", "/tasklistSB/tasks/view");
        int delta = ParamUtil.getInteger(this.request, "delta");
        if (delta > 0) {
            portletURL.setParameter("delta", String.valueOf(delta));
        }

        String orderByCol = ParamUtil.getString(this.request, "orderByCol", "title");
        String orderByType = ParamUtil.getString(this.request, "orderByType", "asc");
        portletURL.setParameter("orderByCol", orderByCol);
        portletURL.setParameter("orderByType", orderByType);
        int cur = ParamUtil.getInteger(this.request, "cur");
        if (cur > 0) {
            portletURL.setParameter("cur", String.valueOf(cur));
        }

        return new ViewTypeItemList(portletURL, this.getDisplayStyle()) {
            {
                this.addCardViewTypeItem();
                this.addListViewTypeItem();
                this.addTableViewTypeItem();
            }
        };
    }

    protected List<DropdownItem> getOrderByDropdownItems() {
        return new DropdownItemList() {
            {
                this.add((dropdownItem) -> {
                    dropdownItem.setActive("title".equals(TasksManagementToolbarDisplayContext.this.getOrderByCol()));
                    dropdownItem.setHref(TasksManagementToolbarDisplayContext.this._getCurrentSortingURL(), new Object[]{"orderByCol", "title"});
                    dropdownItem.setLabel(LanguageUtil.get(TasksManagementToolbarDisplayContext.this.request, "title"));
                });
                this.add((dropdownItem) -> {
                    dropdownItem.setActive("createDate".equals(TasksManagementToolbarDisplayContext.this.getOrderByCol()));
                    dropdownItem.setHref(TasksManagementToolbarDisplayContext.this._getCurrentSortingURL(), new Object[]{"orderByCol", "createDate"});
                    dropdownItem.setLabel(LanguageUtil.get(TasksManagementToolbarDisplayContext.this.request, "create-date"));
                });
            }
        };
    }

    private PortletURL _getCurrentSortingURL() throws PortletException {
        PortletURL sortingURL = PortletURLUtil.clone(this.currentURLObj, this.liferayPortletResponse);
        sortingURL.setParameter("mvcRenderCommandName", "/tasklistSB/tasks/view");
        sortingURL.setParameter("cur", "0");
        String keywords = ParamUtil.getString(this.request, "keywords");
        if (Validator.isNotNull(keywords)) {
            sortingURL.setParameter("keywords", keywords);
        }

        return sortingURL;
    }
}
