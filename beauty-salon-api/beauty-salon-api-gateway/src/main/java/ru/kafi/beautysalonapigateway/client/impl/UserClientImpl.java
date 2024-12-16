package ru.kafi.beautysalonapigateway.client.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;
import ru.kafi.beautysalonapicommon.dto.user.UpdateUserDto;
import ru.kafi.beautysalonapigateway.client.UserClient;
import ru.kafi.beautysalonapigateway.config.ClientConfig;

@Service
public class UserClientImpl extends Client implements UserClient {
    protected UserClientImpl(ClientConfig clientConfig) {
        super(clientConfig);
    }

    @Override
    public ResponseEntity<?> get(HttpServletRequest request) {
        return super.get(request);
    }

    @Override
    public Page<?> getPage(HttpServletRequest request) {
        return super.getPage(request);
    }

    @Override
    public ResponseEntity<?> create(HttpServletRequest request, NewUserDto newUser) {
        return super.post(request, newUser);
    }

    @Override
    public ResponseEntity<?> update(HttpServletRequest request, UpdateUserDto updateUser) {
        return super.patch(request, updateUser);
    }

    @Override
    public ResponseEntity<?> delete(HttpServletRequest request) {
        return super.delete(request);
    }
}
