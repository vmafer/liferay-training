/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package tasklistsb.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import org.osgi.service.component.annotations.Component;

import tasklistsb.model.Task;
import tasklistsb.service.base.TaskLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
        property = "model.class.name=tasklistsb.model.Task",
        service = AopService.class
)
public class TaskLocalServiceImpl extends TaskLocalServiceBaseImpl {

    public Task addTask(long groupId, long companyId, String title, String userName,
                        ServiceContext serviceContext) throws PortalException {

        Group group = groupLocalService.getGroup(groupId);
        long userId = serviceContext.getUserId();
        User user = userLocalService.getUser(userId);

        long assignmentId = counterLocalService.increment(Task.class.getName());

        Task task = createTask(assignmentId);
        task.setCompanyId(group.getCompanyId());

        task.setCreateDate(serviceContext.getCreateDate(new Date()));
        task.setGroupId(groupId);
        task.setCompanyId(companyId);
        task.setModifiedDate(serviceContext.getModifiedDate(new Date()));
        task.setTitle(title);
        task.setUserName(userName);
        task.setUserId(userId);
        task.setUserName(user.getScreenName());

        return super.addTask(task);
    }

    public Task updateTask(long taskId, String title,
                                 String userName) throws PortalException {
        Task task = getTask(taskId);
        task.setModifiedDate(new Date());
        task.setTitle(title);
        task.setUserName(userName);

        task = super.updateTask(task);

        return task;
    }

    public List<Task> getTaskByGrouoId(long groupId) {
        return taskPersistence.findByGroupId(groupId);
    }

    @Override
    public Task addTask(Task task) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Task updateTask(Task task) {
        throw new UnsupportedOperationException("Not supported.");
    }

}