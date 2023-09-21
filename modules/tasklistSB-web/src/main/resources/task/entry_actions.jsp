<%@ include file="../init.jsp"%>

<c:set var="task" value="${SEARCH_CONTAINER_RESULT_ROW.object}" />

<liferay-ui:icon-menu markupView="lexicon">

	<%-- View action. --%>

	<portlet:renderURL var="viewTaskURL">
		<portlet:param name="mvcRenderCommandName"
			value="<%=MVCCommandNames.VIEW_TASK %>" />
		<portlet:param name="redirect" value="${currentURL}" />
		<portlet:param name="taskId" value="${task.assignmentId}" />
	</portlet:renderURL>

	<liferay-ui:icon message="view" url="${viewTaskURL}" />

	<%-- Edit action. --%>

	<portlet:renderURL var="editTaskURL">
		<portlet:param name="mvcRenderCommandName"
			value="<%=MVCCommandNames.EDIT_TASK %>" />
		<portlet:param name="redirect" value="${currentURL}" />
		<portlet:param name="TaskId" value="${task.taskId}" />
	</portlet:renderURL>

	<liferay-ui:icon message="edit" url="${editTaskURL}" />

	<%-- Delete action. --%>

	<portlet:actionURL name="<%=MVCCommandNames.DELETE_Task %>" var="deleteTaskURL">
		<portlet:param name="redirect" value="${currentURL}" />
		<portlet:param name="taskId" value="${task.taskId}" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="${deleteTaskURL}" />

</liferay-ui:icon-menu>