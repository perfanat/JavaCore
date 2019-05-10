package lesson8_2.l8Server;

// класс юзера, у которого есть имя и пароль
public class l8User {

    private String login;
    private String password;

    public l8User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
