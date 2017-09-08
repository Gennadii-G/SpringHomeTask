package ru.epam.spring.hometask.database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.epam.spring.hometask.domain.Auditorium;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;

public class AuditoriumDAOTest extends Assert {

    private AuditoriumDAO auditoriumDAO = new AuditoriumDAO();
    private Set<Auditorium> auditoriums;

    @Before
    public void init(){
        auditoriums = new HashSet<>();
        Auditorium aud = new Auditorium();
        aud.setName("small");
        aud.setNumberOfSeats(25L);
        aud.setVipSeats(new String[0]);
        auditoriums.add(aud);

        aud = new Auditorium();
        aud.setName("medium");
        aud.setNumberOfSeats(50L);
        aud.setVipSeats(new String[]{"1","2","3","4","5","6","7","8","9","10"});
        auditoriums.add(aud);

        aud = new Auditorium();
        aud.setName("general");
        aud.setNumberOfSeats(100L);
        aud.setVipSeats(new String[]{"1","2","3","4","5","6","7","8","9","10", "11", "12", "13", "14", "15"});
        auditoriums.add(aud);
        auditoriumDAO.setData(auditoriums);
    }

    @Test
    public void getAllTest(){
        assertThat(auditoriumDAO.getAll(), is(auditoriums));
    }

    @Test
    public void getByName(){
        Auditorium aud = new Auditorium();
        aud.setName("medium");
        aud.setNumberOfSeats(50L);
        aud.setVipSeats(new String[]{"1","2","3","4","5","6","7","8","9","10"});

        assertThat(auditoriumDAO.getByName("medium"), is(aud));
    }

}
