//package ru.kafi.beautysalonbothandler.client;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.Page;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestClient;
//import ru.kafi.beautysalonapigateway.client.impl.Client;
//
//@Component
//public class UserTgClient extends Client {
//    protected UserTgClient(@Value("${api-service.url}") String url) {
//        super(RestClient.builder()
//                .baseUrl(url)
//                .build());
//    }
//
//    @Override
//    public ResponseEntity<?> get(String path) {
//        return super.get(path);
//    }
//
//    @Override
//    public Page<?> getAll(String path, HttpServletRequest request) {
//        return super.getAll(path, request);
//    }
//
//    @Override
//    public ResponseEntity<?> post(String path, Object body) {
//        return super.post(path, body);
//    }
//
//    @Override
//    public ResponseEntity<?> patch(String path, Object body) {
//        return super.patch(path, body);
//    }
//
//    @Override
//    public ResponseEntity<?> delete(String path) {
//        return super.delete(path);
//    }
//
//}
