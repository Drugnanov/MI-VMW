<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
</script>

<div class="innerContent">
	<spring:url value="/flickr/search" var="searchURL" htmlEscape="true" />

	<div>Flicker test: ${flickrTest}</div>

	<form:form action="${searchURL}" method='POST' modelAttribute="searchData">
		<section class="">
			<div class="">
				<tr>
					<td>Name:</td>
					<td><form:input path="tag" size="100"/></td>
				</tr>
			</div>
		</section>
		<form:button type="submit" class="">Search</form:button>
	</form:form>
</div>