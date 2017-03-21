package school.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LESSON")
@NamedQueries({
		@NamedQuery(name = Lesson.FIND_BY_START_TIME, query = Lesson.FIND_BY_START_TIME_QUERY),
		@NamedQuery(name = Lesson.FIND_BY_CURRENT_TIME, query = Lesson.FIND_BY_CURRENT_TIME_QUERY), })
public class Lesson {

	public static final String FIND_BY_START_TIME = "Lesson.findByStartTime";
	public static final String FIND_BY_START_TIME_QUERY = "SELECT less FROM Lesson less WHERE less.startTime = :startTime";
	public static final String FIND_BY_CURRENT_TIME = "Lesson.currentTime";
	public static final String FIND_BY_CURRENT_TIME_QUERY = "SELECT less FROM Lesson less WHERE :currentTime BETWEEN less.startTime AND less.finishTime";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "startTime", nullable = false)
	private Date startTime;

	@Column(name = "finishTime")
	private Date finishTime;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson")
	private List<Schedule> listSchedule;

	public Lesson() {
		super();

	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public void setListSchedule(List<Schedule> listSchedule) {
		this.listSchedule = listSchedule;
	}

	public Long getId() {
		return id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public List<Schedule> getListSchedule() {
		return listSchedule;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Long.toString(id);
	}

}
