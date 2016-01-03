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
        <label>Keyword:</label>
        <form:input path="tag" size="100" class="span3"/>
        <label>Views:</label>
        <form:input path="views" size="100" class="span3"/>
        <label>Views weight (0-2):</label>
        <form:input type="range" path="viewsCountWeight" size="100" class="span3 weight" id="viewsCountWeight" min="0" max="2" step="0.5"/>
        <label>Upload date:</label>
        <form:input type="date" path="createdAt" size="100" class="span3"/>
        <label>Description:</label>
        <form:textarea path="description" size="100" class="span3"/>
        <label>Description weight (0-2):</label>
        <form:input type="range" path="descriptionWeight" size="100" class="span3 weight" id="descriptionWeight" min="0" max="2" step="0.5"/>
        <label>Latitude:</label>
        <form:input path="latitude" size="100" class="span3" id="latitude"/>
        <label>Longitude:</label>
        <form:input path="longitude" size="100" class="span3" id="longitude"/>
        <div id="map"></div>
        <label>Geo weight (0-2):</label>
        <form:input type="range" path="geoWeight" size="100" class="span3 weight" id="geoWeight" min="0" max="2" step="0.5"/>
      </div>
    </section>
    <form:button type="submit" class="btn btn-block btn-primary">Search</form:button>
  </form:form>
</div>