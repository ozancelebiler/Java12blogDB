package com.blog.controller;


import com.blog.dto.request.PostRequestDto;
import com.blog.dto.request.PostSaveRequestDto;
import com.blog.entity.Post;

import static com.blog.constant.EndPoints.*;

import com.blog.service.PostService;
import com.blog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequiredArgsConstructor
@RequestMapping(ROOT+POST)
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService,UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping(CREATEBYUSERID)
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto dto, @PathVariable Long userId){
        return ResponseEntity.ok(postService.createPost(dto,userId));
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Post>> getAll(){
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<Optional<Post>> findById(@PathVariable Long id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> update(PostSaveRequestDto dto){
        postService.update(dto);
        return ResponseEntity.ok("Kayıt Başarılı");
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<ResponseEntity<String>> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(postService.deleteById(id));
    }

    @GetMapping(FINDALLPOSTBYUSERID)
    public ResponseEntity<List<Post>> findAllPostByUserId(@PathVariable Long id){
        return ResponseEntity.ok(postService.findAllPostByUserId(id));
    }

    //CategoryId girerek listeleme yapamadık.
//    @GetMapping(FINDALLPOSTBYCATEGORYID)
//    public ResponseEntity<List<Post>> findAllPostByCategoryId(@PathVariable Long id){
//        return ResponseEntity.ok(postService.findAllPostByCategoryId(id));
//    }

    @GetMapping(FINDBYTITLE)

    public ResponseEntity<List<Post>>  findByTitleIgnorcase(@PathVariable String title) {
        return ResponseEntity.ok(postService.findByTitleIgnoreCase(title));

    }

        @GetMapping(FINDALLBYORDERBYRELEASEDATE)
        public ResponseEntity<List<Post>> findAllByOrderByReleaseDate() {
            return ResponseEntity.ok(postService.findAllByOrderByReleaseDate());
        }
        @GetMapping(FINDBYCATEGORYLISTID)
        public ResponseEntity<List<Post>> findByCategoryList_Id(Long categoryId){
            return ResponseEntity.ok(postService.findByCategoryList_Id(categoryId));
        }

    }



