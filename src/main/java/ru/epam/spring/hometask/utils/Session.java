package ru.epam.spring.hometask.utils;

import ru.epam.spring.hometask.domain.User;

public class Session {

    private User activeUser;

    public void setActiveUser(User activeUser){
        if(activeUser != null){
            this.activeUser = activeUser;
        }
    }

    public User getActiveUser(){
        if(activeUser != null) {
            return activeUser;
        }else{
            return new User();
        }
    }
}
