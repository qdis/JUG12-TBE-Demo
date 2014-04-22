package ro.dialogdata.jug.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Message extends AbstractEntity {

	private static final long serialVersionUID = 8646294512240657670L;

	@Column(nullable = false)
	private String value;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne
	private User user;
	
	public Message(){
		super();
	}

	public Message(String value, Date date, User user) {
		super();
		this.value = value;
		this.date = date;
		this.user = user;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Message [value=" + value + ", date=" + date + ", user=" + user
				+ "]";
	}

	public boolean equals(Object other) {
		return super.equals(other);
	}

	public int hashCode() {
		return super.hashCode();
	}
}
