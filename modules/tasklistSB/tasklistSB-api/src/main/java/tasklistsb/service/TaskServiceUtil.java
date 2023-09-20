/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package tasklistsb.service;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import tasklistsb.model.Task;

/**
 * Provides the remote service utility for Task. This utility wraps
 * <code>tasklistsb.service.impl.TaskServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TaskService
 * @generated
 */
public class TaskServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>tasklistsb.service.impl.TaskServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Task addTask(
			long groupId, long companyId, String title, String userName,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addTask(
			groupId, companyId, title, userName, serviceContext);
	}

	public static Task deleteTask(long taskId) throws PortalException {
		return getService().deleteTask(taskId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static Task getTask(long taskId) throws PortalException {
		return getService().getTask(taskId);
	}

	public static List<Task> getTaskByGroupId(long groupId) {
		return getService().getTaskByGroupId(groupId);
	}

	public static Task updateAssignment(
			long taskId, String title, String userName)
		throws PortalException {

		return getService().updateAssignment(taskId, title, userName);
	}

	public static TaskService getService() {
		return _service;
	}

	public static void setService(TaskService service) {
		_service = service;
	}

	private static volatile TaskService _service;

}