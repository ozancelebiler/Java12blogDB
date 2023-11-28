package com.blog.controller;


import com.blog.dto.request.UserLoginRequestDto;
import com.blog.dto.request.UserRegisterRequestDto;
import com.blog.dto.request.UserSaveRequestDto;
import com.blog.dto.response.UserLoginResponseDto;
import com.blog.dto.response.UserRegisterResponseDto;
import com.blog.entity.User;
import com.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import static com.blog.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ROOT+USER)
public class UserController {

    private final UserService userService;

    @PostMapping(REGISTER)
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody @Validated UserRegisterRequestDto dto){
        return new ResponseEntity(userService.register(dto), HttpStatus.CREATED);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto dto) {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.login(dto));
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> update(UserSaveRequestDto dto){
        userService.update(dto);
        return ResponseEntity.ok("Kayıt Başarılı");
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("Silme işlemi tamamlanmıştır.");
    }

    @GetMapping(FINDBYNAME)
    public ResponseEntity<List<User>> findByName(String name){
        return ResponseEntity.ok(userService.findByName(name));
    }

}
