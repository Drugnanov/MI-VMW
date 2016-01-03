<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet/v0.7.7/leaflet.css"/>
<script src="http://cdn.leafletjs.com/leaflet/v0.7.7/leaflet.js"></script>

<h1>Flickr metadata-based re-ranking</h1>

<div class="innerContent pull-left span3">
  <spring:url value="/flickr/search" var="searchURL" htmlEscape="true"/>

  <h4>Search:</h4>

  <form:form action="${searchURL}" method='POST' modelAttribute="searchData">
    <c:forEach var="error" items="${errors}">
      <div class="errorRed"><c:out value="${error}"/></div>
    </c:forEach>
    <section class="">
      <div class="form-group">
        <label>Max number of photos (more than 0):</label>
        <form:input path="maxNumberOfPhotos" size="20" class="span3"/>
        <label>Keyword (more than 2 chars):</label>
        <form:input path="tag" size="100" class="span3"/>
      </div>
      <div class="form-group">
        <label>Views:</label>
        <form:input path="views" size="100" class="span3"/>
        <label>Views weight (0-2):</label>
        <form:input type="range" path="viewsCountWeight" size="100" class="span3 weight" id="viewsCountWeight" min="0"
                    max="2" step="0.5"/>
        <label>Date taken:</label>
        <form:input type="date" path="createdAt" size="100" class="span3"/>
        <label>Date taken weight:</label>
        <form:input type="range" path="createdAtWeight" size="100" class="span3 weight" id="createdAtWeight" min="0"
                    max="2" step="0.5"/>
        <label>Description:</label>
        <form:textarea path="description" size="100" class="span3"/>
        <label>Description weight (0-2):</label>
        <form:input type="range" path="descriptionWeight" size="100" class="span3 weight" id="descriptionWeight" min="0"
                    max="2" step="0.5"/>
        <label>Latitude:</label>
        <form:input path="latitude" size="100" class="span3" disabled="true" id="latitude"/>
        <label>Longitude:</label>
        <form:input path="longitude" size="100" class="span3" disabled="true" id="longitude"/>
        <div id="map"></div>
        <label>Geo weight (0-2):</label>
        <form:input type="range" path="geoWeight" size="100" class="span3 weight" id="geoWeight" min="0" max="2"
                    step="0.5"/>
      </div>
    </section>
    <form:button type="submit" class="btn btn-block btn-primary">Search</form:button>
  </form:form>
</div>