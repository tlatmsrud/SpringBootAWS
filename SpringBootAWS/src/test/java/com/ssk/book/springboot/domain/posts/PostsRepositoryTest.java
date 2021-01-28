package com.ssk.book.springboot.domain.posts;


import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;
	
	/*
	 * @After //Junit�������� �׽�Ʈ�� ���� �� ���� ����Ǵ� �޼ҵ� public void cleanup() {
	 * postsRepository.deleteAll(); }
	 */
	
//	@Test
//	public void openBoard() {
//		
//		String title = "�׽�Ʈ �Խñ�";
//		String content = "�׽�Ʈ ����";
//		
//		//���̺� posts�� insert, update ������ ����. id���� �ִٸ� update, ���ٸ� insert ������ �����.
//		postsRepository.save(Posts.builder()
//				.title(title)
//				.content(content)
//				.author("tlatmsrud@naver.com")
//				.build());
//	
//		List<Posts> postsList = postsRepository.findAll();
//		
//		Posts posts = postsList.get(0);
//		assertThat(posts.getTitle()).isEqualTo(title);
//		assertThat(posts.getContent()).isEqualTo(content);
//	}
	
	@Test
	public void BaseTimeEntity_test() {
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
