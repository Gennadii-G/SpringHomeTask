package ru.epam.spring.hometask.database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.epam.spring.hometask.domain.Auditorium;
import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.Ticket;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.util.Discount;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static ru.epam.spring.hometask.domain.EventRating.MID;

public class TicketsDAOTest extends Assert {

    private User user;
    private Event event;
    private LocalDateTime time;
    private Auditorium auditorium;
    private long numberOfTickets;
    private Set<Ticket> tickets;
    private Ticket ticket;
    TicketsDAO ticketsDAO = new TicketsDAO(new Discount());

    @Before
    public void init(){
        time = LocalDateTime.parse("2017-08-11T14:00");
        tickets = new HashSet<>();
        numberOfTickets = 20L;

        user = new User();
        user.setFirstName("Anna");
        user.setLastName("Nosova");
        user.setBirthDay("1990-12-12");
        user.setEmail("anna@mail.com");

        auditorium = new Auditorium();
        auditorium.setName("medium");
        auditorium.setNumberOfSeats(50L);
        auditorium.setVipSeats(new String[]{"1","2","3","4","5","6","7","8","9","10"});

        event = new Event();
        event.setName("birth day");
        event.setAirDates(new String[]{"2017-09-03 10:00", "2017-08-11 12:00", "2017-08-11 14:00"});
        event.setBasePrice(150);
        event.setRating(MID);
        event.setAuditorium(auditorium);
        event.refreshAuditoriums();

        for(int i =0; i < 3; i++){
            tickets.add(new Ticket(user, event, time, i+20));
        }
    }


    @Test
    public void getPurchasedTicketsAndBookTicketsTestForEventTest(){
        assertThat(ticketsDAO.getPurchasedTicketsForEvent(event, time).size(), is(0));
        ticketsDAO.bookTickets(tickets);
        assertThat(ticketsDAO.getPurchasedTicketsForEvent(event, time).size(), is(3));

    }

    @Test
    public void getTicketsPriceTest(){
        double price = ticketsDAO.getTicketsPrice(event, time, user, new HashSet<Long>(Arrays.asList(new Long[] { 20L})));
        assertThat(price, is(150D));

        price = ticketsDAO.getTicketsPrice(event, time, user, new HashSet<Long>(Arrays.asList(new Long[] { 5L})));
        assertThat(price, is(300D));

    }
}
