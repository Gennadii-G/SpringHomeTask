package ru.epam.spring.hometask.database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.epam.spring.hometask.database.UsersDAO;
import ru.epam.spring.hometask.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class UserDAOTest extends Assert {

    private UsersDAO usersDAO = new UsersDAO();
    private List<User> users;
    private User user = createUser();

    @Before
    public void init(){
        users = new ArrayList<>();
        for(int i=0; i < 5; i++){
            users.add(createUser());
        }
        usersDAO.setData(users);
    }

    @Test
    public void getUserByEmailTest(){
        User user = null;
        user = usersDAO.getUserByEmail("anna@mail.com");
        assertThat(this.user, is(user));
    }

    @Test
    public void saveTest(){
        assertThat(usersDAO.getAll().size(), is(5));
        usersDAO.save(user);
        assertThat(usersDAO.getAll().size(), is(6));
    }

    @Test
    public void removeTest(){
        assertThat(usersDAO.getAll().size(), is(5));
        usersDAO.remove(user);
        assertThat(usersDAO.getAll().size(), is(4));
    }

    @Test
    public void removeByIdTest(){
        assertThat(usersDAO.getAll().size(), is(5));
        usersDAO.remove(1);
        assertThat(usersDAO.getAll().size(), is(4));
    }

    @Test
    public void getByIdTest(){
        assertThat(usersDAO.getById(3), is(user));
    }

    @Test
    public void getAllTest(){
        List<User> usersT;
        usersT = usersDAO.getAll();
        assertThat(usersT, is(users));
    }

    private User createUser(){
        User user = new User();
        user.setFirstName("Anna");
        user.setLastName("Nosova");
        user.setBirthDay("1990-12-12");
        user.setEmail("anna@mail.com");

        return user;
    }
}
