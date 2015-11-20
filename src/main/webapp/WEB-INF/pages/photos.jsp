<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header-result.jsp"/>
<%--<jsp:include page="header.jsp"/>--%>
<jsp:include page="search.jsp"/>

<div class="container-fluid">
  <div class="row-fluid">

    <%--<spring:url value="/flickr/search" var="searchURL" htmlEscape="true"/>--%>

    <div class="results span10">
      <c:forEach var="photo" items="${photos}">
        <div>
          <a href="http://farm${photo.photo.farm}.staticflickr.com/${photo.photo.server}/${photo.photo.id}_${photo.photo.secret}.jpg"><img
              width="75"
              height="75"
              src="http://farm${photo.photo.farm}.staticflickr.com/${photo.photo.server}/${photo.photo.id}_${photo.photo.secret}_s.jpg"
              style="opacity: 0"/></a><br/>
          rank:${photo.rank}<br/>
          GEO:${photo.photo.geoData.latitude}/${photo.photo.geoData.longitude}<br/>
          Description:${photo.photo.description}<br/>
          Views:${photo.photo.views}<br/>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
<jsp:include page="footer.jsp"/>
