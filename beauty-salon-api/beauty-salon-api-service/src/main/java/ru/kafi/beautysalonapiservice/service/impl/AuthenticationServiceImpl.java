package ru.kafi.beautysalonapiservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapiservice.security.JwtService;
import ru.kafi.beautysalonapiservice.service.AuthenticationService;
import ru.kafi.beautysalonapiservice.service.entity.User;
import ru.kafi.beautysalonapiservice.service.mapper.UserMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
//    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
//    private static final String AUTH_TOKEN = "test";

    @Override
    public JwtAuthenticationResponse signUp(NewClientDto newClientDto) {
        User user = userMapper.toEntity(newClientDto);

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

//    @Override
//    public JwtAuthenticationResponse signIn(SignInRequest request) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                request.getUsername(),
//                request.getPassword()
//        ));
//
//        var user = userService
//                .userDetailsService()
//                .loadUserByUsername(request.getUsername());
//
//        var jwt = jwtService.generateToken(user);
//        return new JwtAuthenticationResponse(jwt);
//    }

//    public static Authentication getAuthentication(HttpServletRequest request) {
//        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
//        if (apiKey == null || !apiKey.equals(AUTH_TOKEN)) {
//            throw new BadCredentialsException("Invalid API Key");
//        }
//        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
//    }
}
