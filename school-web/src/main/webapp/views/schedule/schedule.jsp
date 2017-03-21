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


<h3>
	<spring:message code="schedule.description" />
</h3>



<form method="POST" action="schedule">

	<div id="navigation_bar">
		<div style="width: 100%; height: 1px; clear: both;">.</div>

		<div id="col1">

			<!-- NAVIGATION-->
			<div class="btn-group" style="height: 100%">
				<input id="back" type="button" class="btn btn-sample"
					name="previous" value="&lt;"> <input id="next"
					type="button" class="btn btn-sample" name="forward" value="&gt;">
			</div>
			<!-- DAYPEACKER-->
			<input id="my_d" name="dateFrom" value="${dateFrom}" type="text"
				class="datepicker">
			<!-- DAY/WEEK/MONTH-->
			<div class="btn-group" data-toggle="buttons" style="height: 100%">
				<c:if test="${inputWalls eq 'day'}">
					<label class="btn btn-sample active"> <input type="radio"
						name="inputWalls" value="day"> day
					</label>
					<label class="btn btn-primary"> <input type="radio"
						name="inputWalls" value="week"> week
					</label>
					<label class="btn btn-sample"> <input type="radio"
						name="inputWalls" value="month"> month
					</label>
				</c:if>
				<c:if test="${(inputWalls eq 'week') || (inputWalls == null)}">
					<label class="btn btn-sample "> <input type="radio"
						name="inputWalls" value="day"> day
					</label>
					<label class="btn btn-sample active"> <input type="radio"
						name="inputWalls" value="week"> week
					</label>
					<label class="btn btn-sample"> <input type="radio"
						name="inputWalls" value="month"> month
					</label>
				</c:if>
				<c:if test="${inputWalls eq 'month'}">
					<label class="btn btn-sample "> <input type="radio"
						name="inputWalls" value="day"> day
					</label>
					<label class="btn btn-sample"> <input type="radio"
						name="inputWalls" value="week"> week
					</label>
					<label class="btn btn-sample active"> <input type="radio"
						name="inputWalls" value="month"> month
					</label>
				</c:if>

			</div>


		</div>


		<div id="col2" class="checker">


			<select id="rm" name="roomy" style="height: 34px; width: 90px;">


				<option value="0">Room</option>
				<c:forEach items="${rooms}" var="room">
					<option <c:if test="${roomy==room.id}"> selected </c:if>
						value="${room.id}">${room.roomNumber}</option>
				</c:forEach>
				<option value="0">ALL</option>
			</select> <select id="gr" name="groupy" style="height: 34px; width: 90px;">

				<option value="0">Group</option>
				<c:forEach items="${groups}" var="group">
					<option <c:if test="${groupy==group.id}"> selected </c:if>
						value="${group.id}">${group.number}-${group.letter}</option>
				</c:forEach>
				<option value="0">ALL</option>
			</select> <select id="tch" name="teacheryy" style="height: 34px;">

				<option value="0">Teacher</option>
				<c:forEach items="${teachers}" var="teacher">
					<option <c:if test="${teacheryy==teacher.id}"> selected </c:if>
						value="${teacher.id}">${teacher.user.lastName}
						${teacher.user.firstName}</option>
				</c:forEach>
				<option value="0">ALL</option>
			</select>


			<button id="submit_ok" type="button" class="btn btn-sample">OK</button>
			<button id="refresh" type="button" class="btn btn-sample">Refresh</button>



		</div>

	</div>

	<div style="width: 100%; height: 1px; clear: both;">.</div>
</form>


<!-- TABLE -->

<div class="table-responsive scrolling">


	<table id="comp"
		class="table  table-bordered default_table table-hover paginated  my">


	</table>
	<img class="weekend_logo"
		src="<c:url value="/resources/img/photo/weekend.png"/>" /> <img
		class="search_logo"
		src="<c:url value="/resources/img/photo/search.png"/>" />


</div>


<!-- Pagination footer -->
<div class="context-footer">
	<select id="select" class="form-control row_count">
		<option value="16">4</option>
		<option value="32">8</option>
		<option value="48">12</option>
		<option value="64">16</option>
	</select>
	<div class="pages"></div>
</div>
<!-- Pagination footer -->




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











