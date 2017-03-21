package school.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import school.controller.URLContainer;
import school.dto.journal.DiarySearchDTO;
import school.dto.journal.StudentWithMarksDTO;
import school.model.Role;
import school.service.DiaryService;
import school.service.utils.DiaryUtil;
import school.service.utils.JournalUtil;

@Controller
public class DiaryController {

	@Autowired
	private DiaryService diaryService;

	@RequestMapping(value = URLContainer.URL_DIARY)
	public String getDiaryByCurrentWeek(Principal principal, Model model,
			HttpServletRequest request) {

		if (principal == null) {
			return URLContainer.URL_REDIRECT + URLContainer.URL_LOGIN;
		}
		if (request.isUserInRole(Role.Secured.PARENT)) {
			model.addAttribute(DiaryUtil.MOD_ATT_KIDS,
					diaryService.getKids(principal.getName()));
		}

		model.addAttribute(URLContainer.JSP_OUTPUT_CURRENT_PAGE,
				URLContainer.URL_DIARY);

		return URLContainer.URL_DIARY;
	}

	@RequestMapping(value = URLContainer.URL_DIARY_MARK)
	public @ResponseBody List<StudentWithMarksDTO> getCurrentWeekMarks(
			@RequestBody DiarySearchDTO diarySearchDTO, Principal principal,
			HttpServletRequest request) {

		List<Date> currentWeek = JournalUtil.getWeek(Calendar.getInstance());
		List<StudentWithMarksDTO> diaryMarks = diaryService.getDiaryMarks(
				getId(request, principal, diarySearchDTO), currentWeek);

		return diaryMarks;
	}

	@RequestMapping(value = URLContainer.URL_CHANGE_WEEK)
	public @ResponseBody List<StudentWithMarksDTO> changeWeek(
			@RequestBody DiarySearchDTO diarySearchDTO, Principal principal,
			HttpServletRequest request) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(diarySearchDTO.getDate());

		if (diarySearchDTO.getWeekChange().equals(DiaryUtil.PREVIOUS_WEEK)) {
			calendar.add(Calendar.DATE, -Calendar.DAY_OF_WEEK);
		} else if (diarySearchDTO.getWeekChange().equals(DiaryUtil.NEXT_WEEK)) {
			calendar.add(Calendar.DATE, Calendar.DAY_OF_WEEK);
		}

		List<Date> currentWeek = JournalUtil.getWeek(calendar);
		List<StudentWithMarksDTO> diaryMarks = diaryService.getDiaryMarks(
				getId(request, principal, diarySearchDTO), currentWeek);

		return diaryMarks;
	}

	/**
	 * This method gets id of parent or student depending on the role.
	 * 
	 * @param request
	 * @param principal
	 * @param diarySearchDTO
	 * @return
	 */
	private long getId(HttpServletRequest request, Principal principal,
			DiarySearchDTO diarySearchDTO) {
		if (request.isUserInRole(Role.Secured.PARENT)) {
			return diarySearchDTO.getUserId();
		} else {
			return Long.parseLong(principal.getName());
		}
	}
}