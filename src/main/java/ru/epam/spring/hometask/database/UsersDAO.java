package ru.epam.spring.hometask.database;

import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UsersDAO implements UserService {

    private static List<User> data = new ArrayList<>();
    private static int id;

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        for(User user : data){
            if(user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    @Override
    public boolean save(@Nonnull User user) {
        user.setId(data.size() + 1);
        return data.add(user);
    }

    @Override
    public boolean remove(@Nonnull User user) {
        return data.remove(user);
    }

    @Override
    public User remove(@Nonnull int id) {
        return data.remove(id - 1);
    }

    @Override
    public User getById(@Nonnull int id) {
        return data.get(id - 1);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.addAll(data);
        return allUsers;
    }
}
