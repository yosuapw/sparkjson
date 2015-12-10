package com.yosi.main;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yosi.dao.NewPostDAO;
import com.yosi.model.Post;
 
public class BlogService {
	
	private static final int HTTP_BAD_REQUEST = 400;
	
	public static String dataToJson(Object data) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			StringWriter sw = new StringWriter();
			mapper.writeValue(sw, data);
			return sw.toString();
		} catch (IOException e) {
			throw new RuntimeException("IOException from a StringWriter");
		}
	}

	public static void main( String[] args) {
    	
    	NewPostDAO postDAO = new NewPostDAO();
    	

        
        post("/posts", (req, res) -> {
        	ObjectMapper mapper = new ObjectMapper();
        	Post creation = mapper.readValue(req.body(), Post.class);
        	if (!creation.isValid()) {
        		res.status(HTTP_BAD_REQUEST);
        		return "";
        	}
					int id = postDAO.createPost(creation.getTitle(),
							creation.getContent(), creation.getCategories());
					res.status(200);
					res.type("application/json");
					return id;
        });
        
        get("/posts", (req, res) -> {
        	res.status(200);
        	res.type("application/json");
			return dataToJson(postDAO.getAllPosts());
        	
        });
    }
}