package lesson8.server;

import lesson8.client.User;

import java.util.HashMap;
import java.util.Map;

// реализация интерфейса аутентификации
public class AuthServiceImpl implements AuthService {

    // создаём список логинов
    static public Map<String, String> users = new HashMap<>();

    // иницииализируем список логинов
    public AuthServiceImpl() {
        users.put("Иван", "1");
        users.put("Петр", "2");
        users.put("Юля", "3");
    }

    // переопределяем интерфейс
    @Override
    public boolean authUser(User user) {
        // получаем пароль по логину
        String pwd = users.get(user.getLogin());
        // проверяем, что пароль не пустой и совпадает с тем, что в базе
        return pwd != null && pwd.equals(user.getPassword());
    }
}