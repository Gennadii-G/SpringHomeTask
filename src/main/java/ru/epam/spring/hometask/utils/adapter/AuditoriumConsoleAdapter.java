package ru.epam.spring.hometask.utils.adapter;

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
        for (Auditorium ad : audSet ){
            strB.append(ad.toString() + "\n");
        }
        return strB.toString();

    }

    public String getByName(String name) {
        Auditorium ad = audService.getByName(name);
        return (ad!=null)?"not found":ad.toString();

    }
}
