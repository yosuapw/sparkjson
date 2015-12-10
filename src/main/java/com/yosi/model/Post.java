package com.yosi.model;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
public class Post implements Validable {

	private Integer id;
	private String title;
	private List categories = new LinkedList<>();
	private String content;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List getCategories() {
		return categories;
	}

	public void setCategories(List categories) {
		this.categories = categories;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isValid() {
		return title != null && !title.isEmpty()
				&& !categories.isEmpty();
	}
}
