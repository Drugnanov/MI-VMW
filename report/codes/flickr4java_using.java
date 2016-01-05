flickr.getPhotosInterface().search(searchParameters, pageLimit, pageOrder);

//configuration
<bean id="flickrRest" class="com.flickr4java.flickr.REST"/>
  
<bean id="flickr" class="com.flickr4java.flickr.Flickr">
  <constructor-arg name="apiKey" value="${cz.vmw.flickr.api.apikey}"/>
  <constructor-arg name="sharedSecret" value="${cz.vmw.flickr.api.sharekey}"/>
  <constructor-arg name="transport" ref="flickrRest"/>
</bean>