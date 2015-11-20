<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet/v0.7.7/leaflet.css"/>
<script src="http://cdn.leafletjs.com/leaflet/v0.7.7/leaflet.js"></script>

<script>


</script>


<div class="innerContent">
  <spring:url value="/flickr/search" var="searchURL" htmlEscape="true"/>

  <div>Search on flicker:</div>

  <form:form action="${searchURL}" method='POST' modelAttribute="searchData">
    <section class="">
      <div class="">
        <label>Name:</label>
        <form:input path="tag" size="100"/>
        <label>Latitude:</label>
        <form:input path="latitude" size="100" id="latitude"/>
        <label>Longitude:</label>
        <form:input path="longitude" size="100" id="longitude"/>
        <label>Views:</label>
        <form:input path="views" size="100"/>
        <label>Description:</label>
        <form:input path="description" size="100"/>
      </div>
    </section>
    <form:button type="submit" class="">Search</form:button>
    <div id="map"></div>
  </form:form>
</div>