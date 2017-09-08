package ru.epam.spring.hometask.util;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.abstract_layout.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Discount implements DiscountService {


    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        byte discount = 0;
        List<Byte> discounts = new ArrayList<>();

        discounts.add(checkBirthDay(user, airDateTime));
        discounts.add(checkAmountBoughtTickets(numberOfTickets, user));

        for(byte b : discounts){
            if(b > discount) discount = b;
        }
        return discount;
    }

    private byte checkAmountBoughtTickets(long numberOfTickets, User user){
       byte disc = (numberOfTickets % 10 == 0 || user.getTickets().size() % 10 == 9)? (byte)50 : 0;
       return disc;
    }

    private byte checkBirthDay(User user, LocalDateTime airDateTime){
        byte disc = 0;
        LocalDate userBirthDay = user.getBirthDay();
        LocalDate eventAirDate = airDateTime.toLocalDate();

        if (userBirthDay.getMonthValue() == eventAirDate.getMonthValue()){
            if(Math.abs(userBirthDay.getDayOfMonth() - eventAirDate.getDayOfYear()) >= 5)
                disc = 5;
        }
        return disc;
    }
}
