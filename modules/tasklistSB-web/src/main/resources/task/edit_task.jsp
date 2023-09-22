<%@ include file="../init.jsp"%>
<liferay-ui:error key="serviceErrorDetails">
	<liferay-ui:message key="error.task-service-error" arguments='<%= SessionErrors.get(liferayPortletRequest, "serviceErrorDetails") %>' />
</liferay-ui:error>
<liferay-ui:error key="taskTitleEmpty" message="error.task-title-empty" />
<liferay-ui:error key="taskDescriptionEmpty" message="error.task-description-empty" />

<%-- Generate add / edit action URL and set title. --%>

<c:choose>
	<c:when test="${not empty task}">
		<portlet:actionURL var="taskActionURL" name="<%=MVCCommandNames.EDIT_TASK %>">
			<portlet:param name="redirect" value="${param.redirect}" />
		</portlet:actionURL>

		<c:set var="editTitle" value="edit-task"/>
	</c:when>
	<c:otherwise>
		<portlet:actionURL var="taskActionURL" name="<%=MVCCommandNames.ADD_TASK %>">
			<portlet:param name="redirect" value="${param.redirect}" />
		</portlet:actionURL>

		<c:set var="editTitle" value="add-task"/>
	</c:otherwise>
</c:choose>

<div class="container-fluid-1280 edit-task">

	<h1><liferay-ui:message key="${editTitle}" /></h1>

	<aui:model-context bean="${task}" model="${taskClass}" />

	<aui:form action="${taskActionURL}" name="fm">

		<aui:input name="taskId" field="taskId" type="hidden" />

		<div markupView="lexicon">

			<aui:fieldset>

				<%-- Title field. --%>

				<aui:input name="title">
					<aui:validator name="required" />
					<%-- Custom AUI validator. --%>

					<aui:validator errorMessage="error.task-title-format" name="custom">
						function(val, fieldNode, ruleValue) {
							var wordExpression =
								new RegExp("^[^\\[\\]\\^$<>]*$");

							return wordExpression.test(val);
						}
					</aui:validator>
				</aui:input>

				<%-- Username field. --%>

				<aui:input name="userName">
					<aui:validator name="required" />
				</aui:input>
			</aui:fieldset>
		</div>

		<%--Buttons. --%>

		<aui:button-row>
			<aui:button cssClass="btn btn-primary" type="submit" />
			<aui:button cssClass="btn btn-secondary" onClick="${param.redirect}" type="cancel" />
		</aui:button-row>
	</aui:form>

</div>