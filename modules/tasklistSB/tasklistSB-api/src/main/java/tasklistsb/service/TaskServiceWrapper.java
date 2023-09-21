/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package tasklistsb.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TaskService}.
 *
 * @author Brian Wing Shun Chan
 * @see TaskService
 * @generated
 */
public class TaskServiceWrapper
	implements ServiceWrapper<TaskService>, TaskService {

	public TaskServiceWrapper() {
		this(null);
	}

	public TaskServiceWrapper(TaskService taskService) {
		_taskService = taskService;
	}

	@Override
	public tasklistsb.model.Task addTask(
			long groupId, long companyId, String title, String userName,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskService.addTask(
			groupId, companyId, title, userName, serviceContext);
	}

	@Override
	public tasklistsb.model.Task deleteTask(long taskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskService.deleteTask(taskId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _taskService.getOSGiServiceIdentifier();
	}

	@Override
	public tasklistsb.model.Task getTask(long taskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskService.getTask(taskId);
	}

	@Override
	public java.util.List<tasklistsb.model.Task> getTaskByGroupId(
		long groupId) {

		return _taskService.getTaskByGroupId(groupId);
	}

	@Override
	public java.util.List<tasklistsb.model.Task> getTasksByKeywords(
		long groupId, String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<tasklistsb.model.Task>
			orderByComparator) {

		return _taskService.getTasksByKeywords(
			groupId, keywords, start, end, orderByComparator);
	}

	@Override
	public long getTasksCountByKeywords(long groupId, String keywords) {
		return _taskService.getTasksCountByKeywords(groupId, keywords);
	}

	@Override
	public tasklistsb.model.Task updateTask(
			long taskId, String title, String userName)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskService.updateTask(taskId, title, userName);
	}

	@Override
	public TaskService getWrappedService() {
		return _taskService;
	}

	@Override
	public void setWrappedService(TaskService taskService) {
		_taskService = taskService;
	}

	private TaskService _taskService;

}