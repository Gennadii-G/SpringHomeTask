package ru.epam.spring.hometask.util.adapter;

import ru.epam.spring.hometask.abstract_layout.service.AuditoriumService;
import ru.epam.spring.hometask.domain.Auditorium;

import java.util.HashSet;
import java.util.Set;

public class AuditoriumConsoleAdapter {

    AuditoriumService audService;

    public AuditoriumConsoleAdapter(AuditoriumService audService) {
        this.audService = audService;
    }

    public String getAll() {
        Set<Auditorium> audSet = (HashSet<Auditorium>) audService.getAll();
        StringBuilder strB = new StringBuilder();
        for (Auditorium aud : audSet ){
            strB.append(aud.toString() + "\n");
        }
        return strB.toString();

    }

    public String getByName(String name) {
        Auditorium aud = audService.getByName(name);
        return (aud!=null)?"not found":aud.toString();

    }
}
