<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="header-result.jsp"/>
<%--<jsp:include page="header.jsp"/>--%>
<jsp:include page="sentimentSearch.jsp"/>

<div class="container-fluid">
  <div class="row-fluid">
<c:if test="${sentiments != null}">


    <%--<spring:url value="/flickr/search" var="searchURL" htmlEscape="true"/>--%>
    <%--<p>Keyword: ${sentiment.keyword}</p>--%>
    <%--<p>Sentiment: <fmt:formatNumber value="${sentiment.sentiment}" maxFractionDigits="2" minFractionDigits="2"/></p>--%>
    <%--<p>Number of comments: ${sentiment.commentsCount}</p>--%>
    <%--<p>Number of positive annotations: ${sentiment.positiveCount}</p>--%>
    <%--<p>Number of negative annotations: ${sentiment.negativeCount}</p>--%>


    <div class="results span9 pull-right">
        <c:forEach var="sentiment" items="${sentiments}">
            <div class="pull-left photo">
                <img
                    <%--width="150"--%>
                    <%--height="150"--%>
                        src="http://farm${sentiment.thumbnail.farm}.staticflickr.com/${sentiment.thumbnail.server}/${sentiment.thumbnail.id}_${sentiment.thumbnail.secret}_q.jpg"
                        class="pic-thumbnail"
                        style="opacity: 0"/>
                <br/>
                <div class="photo-description">
                    <p class="sentiment-score"><fmt:formatNumber value="${sentiment.sentiment}" maxFractionDigits="2" minFractionDigits="2"/></p>
                    <%--<strong>GEO:</strong> <c:if test="${photo.photo.geoData.latitude != null}">${photo.photo.geoData.latitude}/${photo.photo.geoData.longitude}</c:if><c:if test="${photo.photo.geoData.latitude == null}">-</c:if><br/>--%>
                        <%--<strong <c:if test="${fn:length(photo.photo.description) > 40}">data-toggle="tooltip" data-placement="left" title="${fn:escapeXml(photo.photo.description)}"</c:if>>--%>
                    <p class="keyword">${sentiment.keyword}</p>
                    <strong>Comments:</strong> ${sentiment.commentsCount}<br/>
                    <strong>Positive annotations:</strong> ${sentiment.positiveCount}<br/>
                    <strong>Negative annotations:</strong> ${sentiment.negativeCount}<br/>
                </div>
            </div>
        </c:forEach>
    </div>

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
    <%--<hr>--%>
    <%--<c:if test="${executionTimes != null}">--%>
        <%--<div class="form-group">--%>
            <%--<div>Execution time:<c:out value="${executionTimes.executionTime/1000000000}"/> s</div>--%>
            <%--<div>Download time (per photo):<c:out value="${executionTimes.downloadTime/1000000000}"/> s</div>--%>
            <%--<div>Reranking time (per photo):<c:out value="${executionTimes.rerankingTime/1000000000}"/> s</div>--%>
            <%--<div>Ratio (D/R):<c:out value="${executionTimes.ratioDownRerank}"/></div>--%>
        <%--</div>--%>
    <%--</c:if>--%>
</c:if>
  </div>
</div>
<jsp:include page="footer.jsp"/>
