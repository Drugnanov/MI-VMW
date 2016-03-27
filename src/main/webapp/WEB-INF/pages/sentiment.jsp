<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="header-result.jsp"/>
<%--<jsp:include page="header.jsp"/>--%>
<%--<jsp:include page="search.jsp"/>--%>

<div class="container-fluid">
  <div class="row-fluid">

    <%--<spring:url value="/flickr/search" var="searchURL" htmlEscape="true"/>--%>
    <p>Keyword: ${sentiment.keyword}</p>
    <p>Sentiment: <fmt:formatNumber value="${sentiment.sentiment}" maxFractionDigits="2" minFractionDigits="2"/></p>
    <p>Number of comments: ${sentiment.commentsCount}</p>
    <p>Number of positive annotations: ${sentiment.positiveCount}</p>
    <p>Number of negative annotations: ${sentiment.negativeCount}</p>

    <%--<div class="results span9 pull-right">--%>
      <%--<c:forEach var="photo" items="${photos}">--%>
        <%--<div class="pull-left photo">--%>
          <%--<a href="http://farm${photo.photo.farm}.staticflickr.com/${photo.photo.server}/${photo.photo.id}_${photo.photo.secret}_c.jpg"><img--%>
              <%--&lt;%&ndash;width="150"&ndash;%&gt;--%>
              <%--&lt;%&ndash;height="150"&ndash;%&gt;--%>
              <%--src="http://farm${photo.photo.farm}.staticflickr.com/${photo.photo.server}/${photo.photo.id}_${photo.photo.secret}_q.jpg"--%>
              <%--class="pic-thumbnail"--%>
              <%--style="opacity: 0"/>--%>
          <%--</a><br/>--%>
            <%--<div class="photo-description">--%>
              <%--<p><fmt:formatNumber value="${photo.rank}" maxFractionDigits="2" minFractionDigits="2"/></p>--%>
              <%--<strong>GEO:</strong> <c:if test="${photo.photo.geoData.latitude != null}">${photo.photo.geoData.latitude}/${photo.photo.geoData.longitude}</c:if><c:if test="${photo.photo.geoData.latitude == null}">-</c:if><br/>--%>
              <%--&lt;%&ndash;<strong <c:if test="${fn:length(photo.photo.description) > 40}">data-toggle="tooltip" data-placement="left" title="${fn:escapeXml(photo.photo.description)}"</c:if>>&ndash;%&gt;--%>
              <%--<strong>Description:</strong> ${fn:escapeXml(fn:substring(photo.photo.description, 0, 40))}${fn:length(photo.photo.description) > 40 ? '...' : ''} <small>(${fn:length(photo.photo.description)}&nbsp;chars)</small><br/>--%>
              <%--<strong>Views:</strong> ${photo.photo.views}<br/>--%>
              <%--<strong>Date:</strong> <fmt:formatDate value="${photo.photo.dateTaken}" type="date"/><br/>--%>
            <%--</div>--%>
        <%--</div>--%>
      <%--</c:forEach>--%>
    <%--</div>--%>
  </div>
</div>
<jsp:include page="footer.jsp"/>
