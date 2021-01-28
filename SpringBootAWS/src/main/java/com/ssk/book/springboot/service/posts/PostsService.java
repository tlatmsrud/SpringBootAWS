package com.ssk.book.springboot.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssk.book.springboot.domain.posts.Posts;
import com.ssk.book.springboot.domain.posts.PostsRepository;
import com.ssk.book.springboot.web.dto.PostsListResponseDto;
import com.ssk.book.springboot.web.dto.PostsResponseDto;
import com.ssk.book.springboot.web.dto.PostsSaveRequestDto;
import com.ssk.book.springboot.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {

	private final PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
		
	}
	
	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(" "+ id));
		posts.update(requestDto.getTitle(),requestDto.getContent());
		return id;
	}
	
	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(" "+id));
		
		return new PostsResponseDto(entity);
	}
	
	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc(){
		return postsRepository.findAllDesc().stream()
				.map(PostsListResponseDto::new)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void delete(Long id) {
		Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."+id));
		postsRepository.delete(posts);
	}
}
