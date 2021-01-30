<%@page import="javax.servlet.http.*" %>
request = <%=request.getAttribute("attr1")%>
<br>
<% if(pageContext.getAttribute("attr1", PageContext.SESSION_SCOPE)==null) {%>
is null!!!
<%	
pageContext.setAttribute("attr1", request.getAttribute("attr1"), PageContext.SESSION_SCOPE);
} %>
pageContext = <%=pageContext.getAttribute("attr1", PageContext.SESSION_SCOPE)%>
<script>
function submit() {
	document.form2.submit();
}
</script>
<div class="container">
    Welcome ${name}!! 
</div>

<form name="form2" action="/" method="get">
<button type="submit" onclick="submit">Submit</button></form>
<br>
<% boolean bool = (Boolean) request.getAttribute("attr1"); %>
<%=bool%>
<br>

<% if((Boolean) pageContext.getAttribute("attr1", PageContext.SESSION_SCOPE)) { %>
ok!!
	<% String id = (String) request.getAttribute("attr2"); %>
    <%=id%>
<%} else {%>
error??
<%}%>