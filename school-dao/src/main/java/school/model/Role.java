package school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 45)
	private String name;
	public Role() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public static class Secured{
		public static final String DIRECTOR = "ROLE_DIRECTOR";
		public static final String HEAD_TEACHER = "ROLE_HEAD_TEACHER";
		public static final String TEACHER = "ROLE_TEACHER";
		public static final String STUDENT = "ROLE_STUDENT";
		public static final String PARENT = "ROLE_PARENT";
		public static final String ADMIN = "ROLE_ADMIN";
		public static final String IS_AUTHENTICATED_FULLY = "IS_AUTHENTICATED_FULLY";
	}
	
	
}
