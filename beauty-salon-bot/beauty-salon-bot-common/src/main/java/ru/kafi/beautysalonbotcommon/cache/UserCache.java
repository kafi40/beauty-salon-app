package ru.kafi.beautysalonbotcommon.cache;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;
import ru.kafi.beautysalonbotcommon.dto.StateDto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Component
public class UserCache {
    private final Map<Long, NewUserDto> newUserCache = new HashMap<>();
    private final Map<Long, StateDto> statesCache = new HashMap<>();
    private final Set<Long> registeredUsers = new HashSet<>();

    public void addNewUser(long chatId, NewUserDto newUserDto) {
        newUserCache.put(chatId, newUserDto);
    }
    public NewUserDto getNewUser(long chatId) {
        return newUserCache.get(chatId);
    }
    public void addNewState(long chatId, StateDto stateDto) {
        statesCache.put(chatId, stateDto);
    }

    public void addRegistered(long telegramId) {
        registeredUsers.add(telegramId);
    }
    public Boolean isRegistered(Long id) {
        return registeredUsers.contains(id);
    }
    public StateDto getState(long chatId) {
        return statesCache.get(chatId);
    }
}
