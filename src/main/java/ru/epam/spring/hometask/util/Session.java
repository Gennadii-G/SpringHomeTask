package ru.epam.spring.hometask.util;

import ru.epam.spring.hometask.domain.User;

public class Session {

    private User activeUser;
    private User guest;

    public Session() {
        guest = new User();
        guest.setFirstName("guest");
        activeUser = guest;
    }

    public Boolean isAuth() {
        if (activeUser.getFirstName() != "guest") return true;
        return false;
    }

    public Boolean isAdmin() {
        if (activeUser != null && activeUser.isPower()){
            return true;
        }else {
            return false;
        }
    }

    public boolean logout(){
        activeUser = guest;
            return true;
    }

    public void setActiveUser(User activeUser){
            this.activeUser = activeUser;
    }

    public User getActiveUser(){
        return activeUser;
    }
}
