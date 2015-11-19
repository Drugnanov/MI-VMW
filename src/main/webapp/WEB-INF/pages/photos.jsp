<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="header-result.jsp"/>
<%--<jsp:include page="header.jsp"/>--%>
<div class="container-fluid">
  <div class="row-fluid">

    <%--<spring:url value="/flickr/search" var="searchURL" htmlEscape="true"/>--%>

    <%--<form:form action="${searchURL}" method='POST' modelAttribute="searchData">--%>
      <%--<section class="">--%>
        <%--<div class="">--%>
          <%--<tr>--%>
            <%--<td>Name:</td>--%>
            <%--<td><form:input path="tag" size="100"/></td>--%>
          <%--</tr>--%>
        <%--</div>--%>
      <%--</section>--%>
      <%--<form:button type="submit" class="">Search</form:button>--%>
    <%--</form:form>--%>

    <%--<div>--%>
    <%--Found number: ${photosFoundNumber}--%>
    <%--</div>--%>
    <%--<table>--%>
    <%--&lt;%&ndash;<c:forEach var="photo" items="${photosResult}">&ndash;%&gt;--%>
    <%--<tr>--%>
    <%--<td colspan="2">Geo:</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>Latitude:</td>--%>
    <%--<td>${photoResult.geoData.latitude}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td colspan="2">AuthorName:</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>UserName:</td>--%>
    <%--<td>${photoResult.owner.username}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td colspan="2">Views:</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>views:</td>--%>
    <%--<td>${photoResult.views}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td colspan="2">Taken:</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>taken:</td>--%>
    <%--<td>${photoResult.dateTaken}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td colspan="2">Description:</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>Description:</td>--%>
    <%--<td>${photoResult.description}</td>--%>
    <%--</tr>--%>
    <%--</table>--%>
    <div class="results span10">
      <a href="http://farm${photoResult.farm}.staticflickr.com/${photoResult.server}/${photoResult.id}_${photoResult.secret}.jpg"><img
          width="75"
          height="75"
          src="http://farm${photoResult.farm}.staticflickr.com/${photoResult.server}/${photoResult.id}_${photoResult.secret}_s.jpg"
          style="opacity: 0"/></a>
    </div>
    <%--</c:forEach>--%>
  </div>
</div>
<jsp:include page="footer.jsp"/>
