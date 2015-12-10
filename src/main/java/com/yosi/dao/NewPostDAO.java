package com.yosi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.yosi.model.Post;

public class NewPostDAO {

	private int nextId = 1;
	private Map posts = new HashMap<>();

	public Integer createPost(String title, String content, List categories) {
		Integer id = nextId++;
		Post post = new Post();
		post.setId(id);
		post.setTitle(title);
		post.setContent(content);
		post.setCategories(categories);
		posts.put(id, post);
		return id;
	}

	public List getAllPosts() {
		return (List) posts.keySet().stream().sorted()
				.map((id) -> posts.get(id))
				.collect(Collectors.toList());
	}
}
