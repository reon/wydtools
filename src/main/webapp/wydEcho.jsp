<%@page import="java.util.*" %>
<html>
   <head>
       <title>Echo</title>
   </head>
   <body>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr>
	<td align="left">
		<h2>Echo - Request, System and Environment Variables</h2>
	</td>
</tr>
</tr>
<tr>
	<td width="100%" align="left" valign="top">

<table width="100%" align="left" border="0" cellpadding="1" cellspacing="1">
<tr>
	<td colspan="2">
		<h2>Request</h2>
	</td>
</tr>
<tr>
	<td><h3>Name</h3></td>
	<td><h3>Value</h3></td>
</tr>
<%
	ArrayList hnames = Collections.list(request.getHeaderNames()); 
	for(int i = 0; i < hnames.size(); i++) {
%>
<tr>
	<td><%=hnames.get(i) %></td>
	<td><%=request.getHeader((String) hnames.get(i))%></td>
</tr>
<%	}	%>
<tr>
	<td>Context Path</td>
	<td><%=request.getContextPath() %></td>
</tr>
<tr>
	<td>Path Info</td>
	<td><%=request.getPathInfo() %></td>
</tr>
<tr>
	<td>Request URI</td>
	<td><%=request.getRequestURI() %></td>
</tr>
<tr>
	<td>Request URL</td>
	<td><%=request.getRequestURL() %></td>
</tr>
<tr>
	<td>Servlet Path</td>
	<td><%=request.getServletPath() %></td>
</tr>
<tr>
	<td>Remote User</td>
	<td><%=request.getRemoteUser() %></td>
</tr>
<tr>
	<td>Remote Host</td>
	<td><%=request.getRemoteHost() %></td>
</tr>
<tr>
	<td>Remote IP</td>
	<td><%=request.getRemoteAddr() %></td>
</tr>
<tr>
	<td>Remote Port</td>
	<td><%=request.getRemotePort() %></td>
</tr>
<tr><td colspan="2">&nbsp;</td></tr>
<tr>
	<td colspan="2">
		<h2>System</h2>
	</td>
</tr>
<tr>
	<td><h3>Name</h3></td>
	<td><h3>Value</h3></td>
</tr>
<%
	Properties p = System.getProperties();
	Enumeration keys = p.keys();
	while (keys.hasMoreElements()) {
  		String key = (String) keys.nextElement();
  		String value = (String) p.get(key);
%>
<tr>
	<td><%=key %></td>
	<td><%=value %></td>
</tr>
<%	}	%>
<tr><td colspan="2">&nbsp;</td></tr>
<tr>
	<td colspan="2">
		<h2>Environment</h2>
	</td>
</tr>
<tr>
	<td><h3>Name</h3></td>
	<td><h3>Value</h3></td>
</tr>
<%
	Map map = System.getenv();
	Iterator it = map.keySet().iterator();
	while (it.hasNext()) {
  		String key = (String) it.next();
  		String value = (String) map.get(key);
%>
<tr>
	<td><%=key %></td>
	<td><%=value %></td>
</tr>
<%	}	%>
<tr><td colspan="2">&nbsp;</td></tr>
<tr>
	<td colspan="2">
		<h2>Context</h2>
	</td>
</tr>
<tr>
	<td><h3>Name</h3></td>
	<td><h3>Value</h3></td>
</tr>
<%
	ArrayList context = Collections.list(application.getAttributeNames());
	for(int i = 0; i < context.size(); i++) {
%>
<tr>
	<td><%=context.get(i) %></td>
	<td><%=application.getAttribute((String) context.get(i)) %></td>
</tr>
<%	}	%>
</table>
	
	</td>
</tr>
</table>
</body>
	