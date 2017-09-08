package ru.epam.spring.hometask.util;

import ru.epam.spring.hometask.domain.User;

public class Session {

    private User currentUser;
    private User guest;

    public Session() {
        guest = new User();
        guest.setFirstName("Guest");
        guest.setEmail("empty@io.com");
        currentUser = guest;
    }

    public boolean isAuth() {
        return !currentUser.getFirstName().equals("Guest");
    }

    public boolean isGuest(){
        return currentUser.getFirstName().equals("Guest");
    }

    public boolean isAdmin() {
        return currentUser != null && currentUser.isPower();
    }

    public boolean logout(){
        currentUser = guest;
            return true;
    }

    public void setCurrentUser(User currentUser){
            this.currentUser = currentUser;
    }

    public User getCurrentUser(){
        return currentUser;
    }
}
