package ru.kafi.beautysalonapigateway.client.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;
import ru.kafi.beautysalonapicommon.dto.user.UpdateUserDto;
import ru.kafi.beautysalonapigateway.client.UserClient;

import java.util.List;

@Service
public class UserClientImpl extends Client implements UserClient {
    protected UserClientImpl(RestClient restClient) {
        super(restClient);
    }

    @Override
    public ResponseEntity<?> get(HttpServletRequest request) {
        return super.get(request.getRequestURI().substring(4));
    }

    @Override
    public List<ResponseEntity<?>> getAll(HttpServletRequest request) {
        return super.getAll(request.getRequestURI().substring(4), request);
    }

    @Override
    public ResponseEntity<?> create(HttpServletRequest request, NewUserDto newUser) {
        return super.post(request.getRequestURI().substring(4), newUser);
    }

    @Override
    public ResponseEntity<?> update(HttpServletRequest request, UpdateUserDto updateUser) {
        return super.patch(request.getRequestURI().substring(4), updateUser);
    }

    @Override
    public void delete(HttpServletRequest request) {
        super.delete(request.getRequestURI().substring(4));
    }
}
