<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="<c:url value="/resources/css/profile.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-select.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/schedules.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/base-pagination.css" />"
	rel="stylesheet">

<div>
                        <div class="text-block clearfix">
                            <div class="text">Whittlesford, Cambridge CB22, Great Britain</div>
                            <div class="phones">
                               <span class="glyphicon glyphicon-earphone"></span> +44 098 199 78 32
                                <br>
                                   <span class="glyphicon glyphicon-earphone"></span> +44 098 651 77 85
                            </div>
                            <div class="mail">
                               <span class="glyphicon glyphicon-envelope"></span> <a href="mailto:info@kschoolportal.com.uk">info@schoolportal.com.uk</a>
                            </div>
                      
                        </div>



<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2449.9796153265047!2d0.1365610159448959!3d52.11649967973983!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47d87b8351fa4e97%3A0xaf3be6410a1a139e!2z0KXQvtCz0LLQsNGA0YLRgQ!5e0!3m2!1sru!2sua!4v1490033412073" width=100% height="600" frameborder="0" style="border:0" allowfullscreen></iframe>
<script src="<c:url value="https://code.jquery.com/jquery-1.10.2.js" />">
	
</script>

<script
	src="<c:url value="http://code.jquery.com/jquery-2.1.1.min.js" />">
	
</script>
<script
	src="<c:url value="https://code.jquery.com/ui/1.11.2/jquery-ui.js" />"></script>


<script src="<c:url value="/resources/js/schedule.js" />"
	type="text/javascript"></script>

<jsp:include page="/views/profile/profile_information_runner.jsp" />

<script src="<c:url value="/resources/js/utils/pagination.js" />"
	type="text/javascript"></script>

