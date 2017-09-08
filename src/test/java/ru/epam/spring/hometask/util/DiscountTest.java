package ru.epam.spring.hometask.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.epam.spring.hometask.domain.Auditorium;
import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.Ticket;
import ru.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static ru.epam.spring.hometask.domain.EventRating.MID;

public class DiscountTest extends Assert {

    private User user;
    private Event event;
    private LocalDateTime time;
    private Auditorium auditorium;
    private Discount discount = new Discount();
    private long numberOfTickets;

    @Before
    public void init(){
        time = LocalDateTime.parse("2017-08-11T14:00");

        numberOfTickets = 1L;

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
    }

    @Test
    public void getDiscountTest(){
        byte result = discount.getDiscount(user, event, time, numberOfTickets);
        assertThat(result, is((byte)0));

        this.user.setBirthDay("1990-08-12");
        result = discount.getDiscount(user, event, time, numberOfTickets);
        assertThat(result, is((byte)5));

        for(int i =0; i < 9; i++){
            user.getTickets().add(new Ticket(user, event, time, i+1));
        }
        result = discount.getDiscount(user, event, time, numberOfTickets);
        assertThat(result, is((byte)50));

    }

}
