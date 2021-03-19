package com.willowary.practicespringboot.service.posts;

import com.willowary.practicespringboot.domain.posts.Posts;
import com.willowary.practicespringboot.domain.posts.PostsRepository;
import com.willowary.practicespringboot.web.dto.PostsResponseDto;
import com.willowary.practicespringboot.web.dto.PostsSaveRequestDto;
import com.willowary.practicespringboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("there is no user. id=" + id)
        );

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("there is no user. id=" + id)
        );

        return new PostsResponseDto(entity);
    }
}