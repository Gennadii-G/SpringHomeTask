package ru.epam.spring.hometask.database;

import ru.epam.spring.hometask.abstract_layout.service.DiscountService;
import ru.epam.spring.hometask.domain.*;
import ru.epam.spring.hometask.abstract_layout.service.BookingService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TicketsDAO implements BookingService {

    private DiscountService ds;
    private ArrayList<Ticket> data = new ArrayList<>();



    public TicketsDAO(DiscountService ds) {
        this.ds = ds;
    }


        @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        double summPrice = 0;
        Long numberOfTickets = (long) seats.size();
        double userDiscount = ds.getDiscount(user, event, dateTime, numberOfTickets);
        EventRating rating = event.getRating();
        Auditorium auditorium = event.getAuditoriums().get(dateTime);

        for (Long seat : seats) {
            double seatPrice = event.getBasePrice() * rating.getCoefficient() * vipSetCoeff(seat, auditorium);
            seatPrice -= (userDiscount / 100) * seatPrice;
            summPrice += seatPrice;
        }

        return summPrice;
    }

    private double vipSetCoeff(Long seat, Auditorium auditorium) {

        return (auditorium.getVipSeats().contains(seat)) ? 2.0 : 1;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        data.addAll(tickets);

    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        HashSet<Ticket> tickets = new HashSet<>();
        for (Ticket ticket : data){
            if (ticket.getDateTime().equals(dateTime) && ticket.getEvent().equals(event)) tickets.add(ticket);
        }
        return tickets;
    }
}
