<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header-result.jsp"/>
<%--<jsp:include page="header.jsp"/>--%>
<jsp:include page="search.jsp"/>

<div class="container-fluid">
  <div class="row-fluid">

    <%--<spring:url value="/flickr/search" var="searchURL" htmlEscape="true"/>--%>

    <div class="results span9 pull-right">
      <c:forEach var="photo" items="${photos}">
        <div class="pull-left photo">
          <a href="http://farm${photo.photo.farm}.staticflickr.com/${photo.photo.server}/${photo.photo.id}_${photo.photo.secret}_c.jpg"><img
              <%--width="150"--%>
              <%--height="150"--%>
              src="http://farm${photo.photo.farm}.staticflickr.com/${photo.photo.server}/${photo.photo.id}_${photo.photo.secret}_q.jpg"
              class="pic-thumbnail"
              style="opacity: 0"/>
          </a><br/>
            <div class="photo-description">
              <p><fmt:formatNumber value="${photo.rank}" maxFractionDigits="2" minFractionDigits="2"/></p>
              <em>GEO</em>:<c:if test="${photo.photo.geoData.latitude != null}">${photo.photo.geoData.latitude}/${photo.photo.geoData.longitude}</c:if><c:if test="${photo.photo.geoData.latitude == null}">-</c:if><br/>
              <em>Description</em>: ${photo.photo.description}<br/>
              <em></em>Views</em>: ${photo.photo.views}<br/>
            </div>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
<jsp:include page="footer.jsp"/>
