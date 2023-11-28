package com.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorType {
    COMMENT_NOT_FOUND(5005,"Böyle bir yorum yoktur.",HttpStatus.NOT_FOUND),
    POST_NOT_FOUND(5004,"BÖYLE BİR POST BULUNAMADI.",HttpStatus.NOT_FOUND),
    INVALID_PASSWORD(5003,"Lutfen sifreyi kontrol edin.",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXSIST(5002,"Bu email adresi kullanılıyor",HttpStatus.ALREADY_REPORTED),

    PARAMETRE_EKSIK(4001,"Parametreleri eksik girildi.",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4004,"Böyle bir kullanıcı bulunamadı...", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND(4008,"Böyle bir kategori bulunamadı...", HttpStatus.NOT_FOUND);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
