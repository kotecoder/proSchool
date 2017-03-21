package school.dto.journal;

public class JournalSearch {

	private String subject;
	private byte groupNumber;
	private char groupLetter;
	private String quarter;

	public JournalSearch() {

	}

	public JournalSearch(String subject, byte groupNumber, char groupLetter,
			String quarter) {
		this.subject = subject;
		this.groupNumber = groupNumber;
		this.groupLetter = groupLetter;
		this.quarter = quarter;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public byte getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(byte groupNumber) {
		this.groupNumber = groupNumber;
	}

	public char getGroupLetter() {
		return groupLetter;
	}

	public void setGroupLetter(char groupLetter) {
		this.groupLetter = groupLetter;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

}