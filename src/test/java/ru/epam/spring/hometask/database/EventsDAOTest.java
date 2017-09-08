package ru.epam.spring.hometask.database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.epam.spring.hometask.domain.Auditorium;
import ru.epam.spring.hometask.domain.Event;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static ru.epam.spring.hometask.domain.EventRating.*;

public class EventsDAOTest extends Assert {

    private EventsDAO eventsDAO = new EventsDAO();
    private List<Event> events;

    @Before
    public void init(){
        events = new ArrayList<>();

        Event event = new Event();
        event.setName("children's party");
        event.setAirDates(new String[]{"2017-08-03 10:00", "2017-08-11 12:00", "2017-08-11 14:00"});
        event.setBasePrice(100);
        event.setRating(LOW);
        event.setAuditorium(new Auditorium());
        eventsDAO.save(event);
        events.add(event);

        event = new Event();
        event.setName("birth day");
        event.setAirDates(new String[]{"2017-09-03 10:00", "2017-08-11 12:00", "2017-08-11 14:00"});
        event.setBasePrice(150);
        event.setRating(MID);
        event.setAuditorium(new Auditorium());
        events.add(event);
        eventsDAO.save(event);

        event = new Event();
        event.setName("concert");
        event.setAirDates(new String[]{"2017-10-03 10:00", "2017-08-11 12:00", "2017-08-11 14:00"});
        event.setBasePrice(200);
        event.setRating(HIGH);
        event.setAuditorium(new Auditorium());
        events.add(event);
        eventsDAO.save(event);

    }

    @Test
    public void getByNameTest(){
        Event event = getTestEvent();
        assertThat(eventsDAO.getByName("children's party"), is(event));
    }

    @Test
    public void saveTest(){
        assertThat(eventsDAO.getAll().size(), is(3));
        eventsDAO.save(new Event());
        assertThat(eventsDAO.getAll().size(), is(4));
    }

    @Test
    public void removeTest(){
        Event event = getTestEvent();

        assertThat(eventsDAO.getAll().size(), is(3));
        eventsDAO.remove(event);
        assertThat(eventsDAO.getAll().size(), is(2));
    }

    @Test
    public void removeByIdTest(){
        assertThat(eventsDAO.getAll().size(), is(3));
        eventsDAO.remove(2);
        assertThat(eventsDAO.getAll().size(), is(2));
    }

    @Test
    public void getById(){
        Event event = getTestEvent();
        assertThat(eventsDAO.getById(1), is(event));
    }

    @Test
    public void getAll(){
        assertThat(eventsDAO.getAll(), is(events));
    }

    private Event getTestEvent(){
        Event event = new Event();
        event.setName("children's party");
        event.setAirDates(new String[]{"2017-08-03 10:00", "2017-08-11 12:00", "2017-08-11 14:00"});
        event.setBasePrice(100);
        event.setRating(LOW);
        event.setAuditorium(new Auditorium());
        event.setId(1);
        return event;
    }
}
