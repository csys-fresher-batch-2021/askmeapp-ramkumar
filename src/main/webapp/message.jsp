<%
String errorMessage = request.getParameter("errorMessage");
if (errorMessage != null) {
	out.println("<font color='red'>" + errorMessage + "</font>");
}
String infoMessage = request.getParameter("infoMessage");
if (infoMessage != null) {
	out.println("<font color='green'>" + infoMessage + "</font>");
}
%>