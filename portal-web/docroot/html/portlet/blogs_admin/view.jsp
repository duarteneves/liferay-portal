<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/html/portlet/blogs_admin/init.jsp" %>

<%
long assetCategoryId = ParamUtil.getLong(request, "categoryId");
String assetTagName = ParamUtil.getString(request, "tag");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/blogs_admin/view");
%>

<portlet:actionURL name="/blogs/edit_entry" var="restoreTrashEntriesURL">
	<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESTORE %>" />
</portlet:actionURL>

<liferay-ui:trash-undo
	portletURL="<%= restoreTrashEntriesURL %>"
/>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcPath" value="/html/portlet/blogs/search.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL.toString() %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
	<aui:input name="deleteEntryIds" type="hidden" />

	<liferay-util:include page="/html/portlet/blogs_admin/toolbar.jsp" />

	<liferay-ui:categorization-filter
		assetType="entries"
		portletURL="<%= portletURL %>"
	/>

	<liferay-ui:search-container
		rowChecker="<%= new RowChecker(renderResponse) %>"
		searchContainer="<%= new EntrySearch(renderRequest, portletURL) %>"
	>

		<%
		EntrySearchTerms searchTerms = (EntrySearchTerms)searchContainer.getSearchTerms();
		%>

		<liferay-ui:search-container-results>
			<%@ include file="/html/portlet/blogs_admin/entry_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.portlet.blogs.model.BlogsEntry"
			escapedModel="<%= true %>"
			keyProperty="entryId"
			modelVar="entry"
			rowIdProperty="urlTitle"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/blogs/view_entry" />
				<portlet:param name="redirect" value="<%= searchContainer.getIteratorURL().toString() %>" />
				<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
			</liferay-portlet:renderURL>

			<%@ include file="/html/portlet/blogs_admin/search_columns.jspf" %>

			<liferay-ui:search-container-column-jsp
				align="right"
				cssClass="entry-action"
				path="/html/portlet/blogs_admin/entry_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<c:if test="<%= total > 0 %>">
			<aui:button disabled="<%= true %>" name="delete" onClick='<%= renderResponse.getNamespace() + "deleteEntries();" %>' value='<%= TrashUtil.isTrashEnabled(scopeGroupId) ? "move-to-the-recycle-bin" : "delete" %>' />

			<div class="separator"><!-- --></div>
		</c:if>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.Util.toggleSearchContainerButton('#<portlet:namespace />delete', '#<portlet:namespace /><%= searchContainerReference.getId() %>SearchContainer', document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

	function <portlet:namespace />deleteEntries() {
		if (<%= TrashUtil.isTrashEnabled(scopeGroupId) %> || confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-the-selected-entries") %>')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.attr('method', 'post');
			form.fm('<%= Constants.CMD %>').val('<%= TrashUtil.isTrashEnabled(scopeGroupId) ? Constants.MOVE_TO_TRASH :Constants.DELETE %>');
			form.fm('deleteEntryIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form, '<portlet:actionURL name="/blogs/edit_entry" />');
		}
	}
</aui:script>