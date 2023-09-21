<%@ include file="../init.jsp"%>

<div class="container-fluid-1280">

	<h1>${task.title}</h1>

	<h2><liferay-ui:message key="task-information" /></h2>

	<div class="task-metadata">

		<dl>
			<dt><liferay-ui:message key="created" /></dt>
			<dd>${createDate}</dd>

			<dt><liferay-ui:message key="created-by" /></dt>
			<dd>${task.userName}</dd>

			<dt><liferay-ui:message key="description" /></dt>
			<dd>${task.description}</dd>
		</dl>
	</div>
</div>