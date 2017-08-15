package ru.epam.spring.hometask.util.adapter;

import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.abstract_layout.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class UserConsoleAdapter {
    private UserService userService;

    public UserConsoleAdapter(UserService userService) {
        this.userService = userService;
    }

    public String userRegistration(Map<String, String> userInfo){
        User user = new User();
        try {
            user.setFirstName(userInfo.get("firstName"));
            user.setLastName(userInfo.get("lastName"));
            user.setBirthDay(LocalDate.parse("birthDate"));
            user.setEmail(userInfo.get("email"));

            userService.save(user);
        }catch(DateTimeParseException e){
            return "ошибка регистрации, неверно указана дата. Пример даты: \"2000-01-02\"";
        }
        return "регистрация прошла успешна, пользователь: "
                + user.getFirstName() + " " + user.getLastName() + " добавлен";
    }

    public String delUser(int id) {
        return "удален пользователь " + userService.remove(id);
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
        if(user == null){
            return "пользователь с таким ящиком не найден";
        }else {
            return user.toString();
        }
    }

    public User loginByEmail(String email) {
         return userService.getUserByEmail(email);
    }

    public String getAll(){
        StringBuilder strB = new StringBuilder();
        for(User user : userService.getAll()){
            strB.append(user.toString()).append("\n");
        }
        return strB.toString();
    }

    public String authorization(String email){
        User user = userService.getUserByEmail(email);
        if(user == null){
            return "пользователь не найден";
        }else{
            return "пользователь " + user.getFirstName() + " авторизирован";
        }
    }
}
