package com.blog.service;

import com.blog.dto.request.PostRequestDto;
import com.blog.dto.request.PostSaveRequestDto;
import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.BlogException;
import com.blog.exception.ErrorType;
import com.blog.mapper.ICategoryMapper;
import com.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private Category category;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public String createPost(PostRequestDto dto, Long userId) {

        Optional<User> optionalUser = userService.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Post post = Post.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .categoryList(List.of(category = ICategoryMapper.INSTANCE.categoryDtoToCategory(dto.getCategoryDto())))
                    .user(user)
                    .build();

            user.getPostList().add(post);
            Post createdPost = postRepository.save(post);
            return createdPost.getId() + " numaralı post yayınlanmıştır.";
        } else {
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public void update(PostSaveRequestDto dto) {
        Optional<Post> optionalPost = findById(dto.getId());
        Optional<User> optionalUser = userService.findById(dto.getUserId());
        if (optionalPost.isPresent()) {
            Post post = Post.builder()
                    .id(dto.getId())
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .user(optionalUser.get())
                    .build();

            postRepository.save(post);

        } else {
            throw new BlogException(ErrorType.POST_NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteById(Long id) {
        Optional<Post> optionalPost = (findById(id));
        if (optionalPost.isPresent()) {
            postRepository.deleteById(id);
            return ResponseEntity.ok("Silme İşlemi Başarılı");
        } else {
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }
    }


    public List<Post> findAllPostByUserId(Long id) {
        Optional<User>optionalUser=userService.findById(id);
        if (optionalUser.isPresent()){
            return postRepository.findAllPostByUserId(id);
        }else {
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }
    }

    public List<Post> findByTitleIgnoreCase(String title){

        return postRepository.findByTitleIgnoreCase(title);

    }

    public List<Post> findByCategoryList_Id(Long categoryId) {

        return postRepository.findByCategoryList_Id(categoryId);
    }

    public List<Post> findAllByOrderByReleaseDate() {

        return postRepository.findAllByOrderByReleaseDate();
    }
}
