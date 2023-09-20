/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package tasklistsb.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import org.osgi.service.component.annotations.Component;

import tasklistsb.model.Task;
import tasklistsb.service.base.TaskServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
        property = {
                "json.web.service.context.name=tasklist",
                "json.web.service.context.path=Task"
        },
        service = AopService.class
)
public class TaskServiceImpl extends TaskServiceBaseImpl {

    public Task addTask(
            long groupId, long companyId, String title, String userName,
            ServiceContext serviceContext)
            throws PortalException {
        return taskLocalService.addTask(
                groupId, companyId, title, userName, serviceContext);
    }

    public Task deleteTask(long taskId)
            throws PortalException {
        Task task =
                taskLocalService.getTask(taskId);
        return taskLocalService.deleteTask(task);
    }

    public Task getTask(long taskId)
            throws PortalException {
        Task task =
                taskLocalService.getTask(taskId);
        return task;
    }
    public List<Task> getTaskByGroupId(long groupId) {
        return taskPersistence.findByGroupId(groupId);
    }

    public Task updateTask(long taskId, String title, String userName)
            throws PortalException {
        return taskLocalService.updateTask(
                taskId, title, userName);
    }
}