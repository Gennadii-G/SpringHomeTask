package ru.epam.spring.hometask.database;

import ru.epam.spring.hometask.domain.Auditorium;
import ru.epam.spring.hometask.abstract_layout.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class AuditoriumDAO implements AuditoriumService {

    private static Set<Auditorium> data = new HashSet<>();

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        Set<Auditorium> allAud = new HashSet<>();
        allAud.addAll(data);
        return allAud;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        for (Auditorium auditorium : data){
            if (auditorium.getName().equals(name)) return auditorium;
        }
        return null;
    }
}
