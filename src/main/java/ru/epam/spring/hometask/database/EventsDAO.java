package ru.epam.spring.hometask.database;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class EventsDAO implements EventService {
    private List<Event> data = new ArrayList<>();

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        for(Event event : data){
            if(event.getName().equals(name))
                return event;
        }
        return null;
    }

    @Override
    public boolean save(@Nonnull Event event) {
        return data.add(event);
    }

    @Override
    public boolean remove(@Nonnull Event event) {
        return data.remove(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        for(Event event : data){
            if(event.getId().equals(id))
                return event;
        }
        return null;
    }

    @Nonnull
    @Override
    public List<Event> getAll() {
        List<Event> allEvents = new ArrayList<>();
        allEvents.addAll(data);
        return allEvents;
    }
}
