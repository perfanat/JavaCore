package lesson8_2.l8Server;

import java.util.HashMap;
import java.util.Map;

public class l8AuthService {
    // создаём список логинов
    static public Map<String, String> users = new HashMap<>();

    // иницииализируем список логинов
    public l8AuthService() {
        users.put("Иван", "1");
        users.put("Петр", "2");
        users.put("Юля", "3");
    }

    public static boolean authUser(l8User user) {
        // получаем пароль по логину
        String pwd = users.get(user.getLogin());
        // проверяем, что пароль не пустой и совпадает с тем, что в базе
        return pwd != null && pwd.equals(user.getPassword());
    }
}
