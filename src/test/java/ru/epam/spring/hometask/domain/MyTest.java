package ru.epam.spring.hometask.domain;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static ru.epam.spring.hometask.domain.Event.FORMATTER;

public class MyTest {

    @Test
    public void test(){
        LocalDateTime dt = LocalDateTime.parse("2017-08-03 10:00", FORMATTER);
        System.out.println(dt);

        String bd = "2007-12-03";
        System.out.println(LocalDate.parse(bd));
    }
}
