package ru.epam.spring.hometask.utils.console_handlers;

import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class UserAdapter {
    private UserService userService;

    public UserAdapter(UserService userService) {
        this.userService = userService;
    }

    public String userRegistration(Map<String, String> userInfo){
        User user = new User();
        try {
            user.setFirstName(userInfo.get("firstName"));
            user.setLastName(userInfo.get("lastName"));
//            user.setBirthDay(LocalDate.parse("birthDate"));
            user.setEmail(userInfo.get("email"));

            userService.save(user);
        }catch(DateTimeParseException e){
            return "ошибка регистрации, неверно указана дата. Пример даты: \"2000-01-02\"";
        }
        return "регистрация прошла успешна, пользователь: "
                + user.getFirstName() + " " + user.getLastName() + " добавлен";
    }

    public String delUser(int id) {
        return "удален пользователь " + userService.remove(id) ;
    }

    public String getUserById(int id) {
        User user = userService.getById(id);
        if(user == null){
            return "пользователь с id: " + id + " не найден";
        }else {
            return "ползователь(" + id + ")" + user;
        }
    }

    public String getUserByEmail(String email) {
        User user = userService.getUserByEmail(email);
        return (user != null) ? user.toString() : "user not found";
    }

    public String getAll(){
        StringBuilder strB = new StringBuilder();
        for(User user : userService.getAll()){
            strB.append(user.toString()).append("\n");
        }
        return strB.toString();
    }

    public String authorization(String email){

    }


//    isAuth
//    isAdmin
}
