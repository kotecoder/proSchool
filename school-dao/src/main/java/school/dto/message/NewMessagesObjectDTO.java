package school.dto.message;

public class NewMessagesObjectDTO {

	private String newMessages;
	
	public NewMessagesObjectDTO(String newMessages) {
		this.newMessages = newMessages;
	}

	public String getNewMessages() {
		return newMessages;
	}

	public void setNewMessages(String newMessages) {
		this.newMessages = newMessages;
	}
}
