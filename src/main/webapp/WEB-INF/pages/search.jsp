<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet/v0.7.7/leaflet.css"/>
<script src="http://cdn.leafletjs.com/leaflet/v0.7.7/leaflet.js"></script>

<div class="innerContent pull-left span3">
  <spring:url value="/flickr/search" var="searchURL" htmlEscape="true"/>

  <h4>Search on flicker:</h4>

  <form:form action="${searchURL}" method='POST' modelAttribute="searchData">
    <section class="">
      <div class="form-group">
        <label>Max number of photos:</label>
        <form:input path="maxNumberOfPhotos" size="20" class="span3" />
        <label>Name:</label>
        <form:input path="tag" size="100" class="span3"/>
        <label>Views:</label>
        <form:input path="views" size="100" class="span3"/>
        <label>Description:</label>
        <form:textarea path="description" size="100" class="span3"/>
        <label>Latitude:</label>
        <form:input path="latitude" size="100" class="span3" id="latitude"/>
        <label>Longitude:</label>
        <form:input path="longitude" size="100" class="span3" id="longitude"/>
        <div id="map"></div>
      </div>
    </section>
    <form:button type="submit" class="btn btn-block btn-primary">Search</form:button>
  </form:form>
</div>