<%@ include file="init.jsp"%>
<liferay-ui:error key="serviceErrorDetails">
		<liferay-ui:message arguments='<%= SessionErrors.get(liferayPortletRequest, "serviceErrorDetails") %>' key="error.assignment-service-error" />
</liferay-ui:error>
<liferay-ui:success key="assignmentAdded" message="assignment-added-successfully" />
<liferay-ui:success key="assignmentUpdated" message="assignment-updated-successfully" />
<liferay-ui:success key="assignmentDeleted" message="assignment-deleted-successfully" />

<%
TasksManagementToolbarDisplayContext tasksManagementToolbarDisplayContext = new TasksManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request);
%>

<div class="container-fluid-1280">

	<h1><liferay-ui:message key="Tasks" /></h1>

	<%-- Clay management toolbar. --%>

	<clay:management-toolbar
		disabled="${taskCount eq 0}"
		displayContext="<%= tasksManagementToolbarDisplayContext %>"
		itemsTotal="${taskCount}"
		searchContainerId="taskEntries"
		selectable="false"
	/>

	<%-- Search container. --%>

	<liferay-ui:search-container
		emptyResultsMessage="no-tasks"
		id="taskEntries"
		iteratorURL="${portletURL}"
		total="${taskCount}">

		<liferay-ui:search-container-results results="${tasks}" />

		<liferay-ui:search-container-row
			className="tasklistsb.model.Task"
			modelVar="entry">

			<%@ include file="/task/entry_search_columns.jspf" %>

		</liferay-ui:search-container-row>

		<%-- Iterator / Paging --%>

		<liferay-ui:search-iterator
			displayStyle="${tasksManagementToolbarDisplayContext.getDisplayStyle()}"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>