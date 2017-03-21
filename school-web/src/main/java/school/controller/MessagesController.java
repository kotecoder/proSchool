package school.controller;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import jms.publisher.Publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import school.dto.message.EmailObjectDTO;
import school.dto.message.MessageDto;
import school.dto.message.NewMessagesObjectDTO;
import school.model.Conversation;
import school.model.Message;
import school.service.ConversationService;
import school.service.MessagesService;

@Controller
public class MessagesController {

	@Autowired
	private MessagesService messagesService;

	@Autowired
	private ConversationService conversationService;

	@RequestMapping(value = "/inbox/{id}")
	public String inboxConversationMessages(Model model, @PathVariable long id,
			Principal principal, HttpServletRequest request) {

		if (principal == null) {
			return "redirect:/signinfailure";
		}

		long userId = Long.valueOf(principal.getName());
		Conversation conversation = conversationService.findById(id);
		List<Conversation> conversationsI = conversationService
				.findConversations(userId, "inbox");
		List<Conversation> conversationsS = conversationService
				.findConversations(userId, "sent");
		List<Message> messages = messagesService.findMessagesOfConversation(
				conversation, userId);
		messagesService.markMessagesAsRead(messages, userId);
		Locale loc = RequestContextUtils.getLocale(request);
		List<MessageDto> messagesDto = null;
		if (userId == conversation.getSenderId().getId()
				&& conversation.getCountOfReceivers() > 1) {
			conversation.setNewForSender(false);
			messagesDto = messagesService.constructGroupMessagesDto(messages,
					conversation.getSenderId().getId(), loc);
		} else {
			if (userId == conversation.getSenderId().getId())
				conversation.setNewForSender(false);
			else
				conversation.setNewForReceiver(false);
			messagesDto = messagesService.constructMessagesDto(messages,
					conversation.getReceiverId().getId(), conversation
							.getSenderId().getId(), loc);
		}
		model.addAttribute("messagesDto", messagesDto);
		model.addAttribute("inboxSize", conversationsI.size());
		model.addAttribute("sentSize", conversationsS.size());
		model.addAttribute("subject", conversation.getSubject());
		model.addAttribute("root_action", "../");
		int newMessages = messagesService.countOfNewMessages(userId);
		request.getSession(false).setAttribute("newMessages", newMessages);
		request.getSession(false).setAttribute("currentPage", "inbox/" + id);
		return "inboxMessages";

	}

	@RequestMapping(value = "/sent/{id}")
	public String sentConversationMessages(Model model, @PathVariable long id,
			Principal principal, HttpServletRequest request) {

		if (principal == null) {
			return "redirect:/signinfailure";
		}

		long userId = Long.valueOf(principal.getName());
		Conversation conversation = conversationService.findById(id);
		List<Conversation> conversationsI = conversationService
				.findConversations(userId, "inbox");
		List<Conversation> conversationsS = conversationService
				.findConversations(userId, "sent");
		List<Message> messages = messagesService.findMessagesOfConversation(
				conversation, userId);
		messagesService.markMessagesAsRead(messages, userId);
		Locale loc = RequestContextUtils.getLocale(request);
		List<MessageDto> messagesDto = null;
		if (userId == conversation.getSenderId().getId()
				&& conversation.getCountOfReceivers() > 1) {
			conversation.setNewForSender(false);
			messagesDto = messagesService.constructGroupMessagesDto(messages,
					conversation.getSenderId().getId(), loc);
		} else {
			if (userId == conversation.getSenderId().getId())
				conversation.setNewForSender(false);
			else
				conversation.setNewForReceiver(false);
			messagesDto = messagesService.constructMessagesDto(messages,
					conversation.getReceiverId().getId(), conversation
							.getSenderId().getId(), loc);

		}
		model.addAttribute("messagesDto", messagesDto);
		model.addAttribute("inboxSize", conversationsI.size());
		model.addAttribute("sentSize", conversationsS.size());
		model.addAttribute("subject", conversation.getSubject());
		model.addAttribute("root_action", "../");
		request.getSession().setAttribute("currentPage", "sent/" + id);
		return "sentMessages";

	}

	@RequestMapping(value = "delete-message", method = RequestMethod.POST)
	public String deleteMessages(
			@RequestParam(value = "messageId", required = false) String messageId,
			Principal principal, HttpServletRequest request) throws NumberFormatException {
		long id = Long.valueOf(principal.getName());
		messagesService.deleteMessage(Long.valueOf(messageId), id);
		String currentPage = (String) request.getSession().getAttribute(
				"currentPage");
		return "redirect:/" + currentPage;
	}

	@RequestMapping(value = "reply", method = RequestMethod.POST)
	public String reply(
			@RequestParam("messageId") String messageId,
			@RequestParam(value = "replyText", required = false) String replyText,
			HttpServletRequest request, Principal principal) throws NumberFormatException {

		Message repliedMessage = messagesService.findById(Long
				.valueOf(messageId));
		Conversation conversation = conversationService.findById(repliedMessage
				.getConversationId().getId());
		long principalId = Long.valueOf(principal.getName());
		int countReceivers = conversation.getCountOfReceivers();
		if (countReceivers > 1) {

			messagesService.replyMessage(conversation, replyText, principalId);

			ApplicationContext ac = initApplContext();
			Publisher publisher = (Publisher) ac.getBean("publisher");
			publisher.setReply(true);
			publisher.setConversation(conversation);
			publisher.setHashCode(conversation.getHashCode());
			publisher.setGroup(conversation.getOutcomeName().split(":")[1]
					.trim());
			publisher.sendMessage(replyText);

		} else {
			messagesService.replyMessage(conversation, replyText, principalId);
		}

		String currentPage = (String) request.getSession().getAttribute(
				"currentPage");
		return "redirect:/" + currentPage;
	}

	private ApplicationContext initApplContext() {
		ApplicationContext ctx = null;
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext("jmsPublisherConfig.xml");
		}
		return ctx;
	}

	@RequestMapping(value = "/emailInput", method = RequestMethod.POST)
	public @ResponseBody
	List<EmailObjectDTO> getEmails(@RequestParam String tagName,
			HttpServletRequest request, @RequestParam String emailOrGroup) {
		boolean isParent = request.isUserInRole("ROLE_PARENT");
		List<Object> usersOrGroups = messagesService.simulateSearchResult(
				tagName, isParent, emailOrGroup);

		return messagesService.contructEmailObjectDTO(usersOrGroups);
	}

	@RequestMapping(value = "/newMessages", method = RequestMethod.GET)
	public @ResponseBody
	NewMessagesObjectDTO getNewMessages(Principal principal) throws NumberFormatException {
		NewMessagesObjectDTO newMessagesObjectDTO;
		if (principal != null) {
			newMessagesObjectDTO = messagesService
					.constructNewMessagesObjectDTO(Long.valueOf(principal
							.getName()));
		} else {
			newMessagesObjectDTO = new NewMessagesObjectDTO("0");
		}
		return newMessagesObjectDTO;
	}
}
