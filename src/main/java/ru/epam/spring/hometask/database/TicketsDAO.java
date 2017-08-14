package ru.epam.spring.hometask.database;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.Ticket;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.abstract_layout.service.BookingService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

public class TicketsDAO implements BookingService {


    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        return 0;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {

    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return null;
    }

}
