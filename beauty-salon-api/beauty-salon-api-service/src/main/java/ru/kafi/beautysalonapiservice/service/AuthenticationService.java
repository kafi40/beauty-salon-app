package ru.kafi.beautysalonapiservice.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(NewClientDto newClientDto);

//    JwtAuthenticationResponse signIn(SignInRequest request);

    Authentication getAuthentication(HttpServletRequest request);
}
