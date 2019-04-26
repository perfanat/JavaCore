package lesson7.server;

import lesson7.client.User;

// интерфейс аутентификации
public interface AuthService {
    boolean authUser(User user);
}
