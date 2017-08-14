package ru.epam.spring.hometask.database;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.abstract_layout.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class DiscountDAO implements DiscountService {

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        byte discount = 0;
        return 0;
    }
}
