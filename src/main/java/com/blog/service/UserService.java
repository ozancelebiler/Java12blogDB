package com.blog.service;

import com.blog.dto.request.UserLoginRequestDto;
import com.blog.dto.request.UserRegisterRequestDto;
import com.blog.dto.request.UserSaveRequestDto;
import com.blog.dto.response.UserLoginResponseDto;
import com.blog.dto.response.UserRegisterResponseDto;
import com.blog.entity.User;

import com.blog.exception.BlogException;
import com.blog.exception.ErrorType;
import com.blog.mapper.IUserMapper;
import com.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserRegisterResponseDto register(UserRegisterRequestDto dto) {
        User user = null;
        if(!userRepository.existsByEmail(dto.getEmail())){
            if(dto.getPassword().equals(dto.getRePassword())){

                user = IUserMapper.INSTANCE.dtoToUser(dto);
                userRepository.save(user)
                ;
            }else {
                throw new BlogException(ErrorType.INVALID_PASSWORD);
            }
        }else {
            throw new BlogException(ErrorType.EMAIL_ALREADY_EXSIST);
        }
        return IUserMapper.INSTANCE.userToDto(user);
    }

    public UserLoginResponseDto login(UserLoginRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if(optionalUser.isPresent()){
            return IUserMapper.INSTANCE.userToLoginDto(optionalUser.get());
        }else {
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void update(UserSaveRequestDto dto){
        Optional<User> optionalUser = findById(dto.getId());
        if(optionalUser.isPresent()){
            User user = User.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .surname(dto.getSurname())
                    .email(dto.getEmail())
                    .password(dto.getPassword())
                    .build();

            userRepository.save(user);
        }else{
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }
    }

    public void deleteById(Long id){
        Optional<User> optionalUser = findById(id);
        if(optionalUser.isPresent()){
            userRepository.deleteById(id);
        } else{
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }
    }

    public List<User> findByName(String name) {
        Boolean varMi = userRepository.existsByName(name);
        if(varMi){
            return userRepository.findByName(name);
        }else{
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }

    }

}
