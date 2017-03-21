<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="forgotAPasswordSucces" class="modal fade" tabindex="-1"
	role="dialog">
	<div class="modal-dialog">
		<br> <br> <br> <br> <br> <br> <br>
		<br>
		<div class="modal-content" align="center">

			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3 class="modal-title">
					<spring:message code="label.forgotapassword.succes" />
				</h3>
				<table>
					<tr>
						<td><h4>
								<spring:message code="label.forgotapassword.confirm" />
							</h4></td>
					</tr>
					<tr>
						<td><div align="center">
								<table>
									<tr>
										<td><input type="button" class="btn btn-sample"
											class="close" data-dismiss="modal" value="Ok"></td>
									</tr>
								</table>
							</div></td>
					</tr>
				</table>
			</div>

		</div>
	</div>
</div>
