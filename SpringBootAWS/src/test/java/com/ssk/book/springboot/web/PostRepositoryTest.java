package com.ssk.book.springboot.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssk.book.springboot.domain.posts.Posts;
import com.ssk.book.springboot.domain.posts.PostsRepository;

@RunWith(SpringRunner.class)
public class PostRepositoryTest {

	@Autowired
	PostsRepository postsRepository;
	
	@Test
	public void BaseTimeEntity() {
		LocalDateTime now = LocalDateTime.of(2019, 6,4,0,0,0);
		postsRepository.save(Posts.builder()
				.title("title")
				.content("content")
				.author("author")
				.build());
	
		List<Posts> postsList = postsRepository.findAll();
		
		Posts posts = postsList.get(0);
	
		System.out.println(">>>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate ="+posts.getModifiedDate());
		
		assertThat(posts.getCreatedDate()).isAfter(now);
		assertThat(posts.getModifiedDate()).isAfter(now);	
		
	}
	
}
