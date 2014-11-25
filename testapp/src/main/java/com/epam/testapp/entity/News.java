package com.epam.testapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="NEWS")
@NamedQueries({
	@NamedQuery(name="News.getNewsList", query="SELECT n FROM News n ORDER BY n.date DESC"),
	@NamedQuery(name="News.deleteNews", query="DELETE n FROM News n WHERE n.id IN (:id)")
})

public class News implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "NEWS_ID")
	@SequenceGenerator( name="id_seq", sequenceName="TEST_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_seq")
	private int id;
	
	@Column(name="NEWS_TITLE")
	private String title;
	
	@Column(name="NEWS_DATE")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="NEWS_BRIEF")
	private String brief;
	
	@Column(name="NEWS_CONTENT")
	private String content;
	
	public News(){}
	
	public News( int id, String title, Date date, String brief, String content ) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.brief = brief;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		News other = (News) obj;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", title=" + title
				+ ", date=" + date + ", brief=" + brief + ", content="
				+ content + "]";
	}

}
