/**
 * SPDX-FileCopyrightText: (c) {$year} Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
package tasklistsb.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class TaskValidationException extends PortalException {

	public TaskValidationException() {
	}

	public TaskValidationException(String msg) {
		super(msg);
	}

	public TaskValidationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public TaskValidationException(Throwable throwable) {
		super(throwable);
	}

}