package lesson7.server;

import lesson7.client.User;

import java.util.HashMap;
import java.util.Map;

// реализация интерфейса аутентификации
public class AuthServiceImpl implements AuthService {

    // создаём список логинов
    public Map<String, String> users = new HashMap<>();

    // иницииализируем список логинов
    public AuthServiceImpl() {
        users.put("иван", "1");
        users.put("петр", "2");
        users.put("юля", "3");
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