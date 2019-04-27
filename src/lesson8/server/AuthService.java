package lesson8.server;

import lesson8.client.User;

// интерфейс аутентификации
public interface AuthService {
    boolean authUser(User user);
}
