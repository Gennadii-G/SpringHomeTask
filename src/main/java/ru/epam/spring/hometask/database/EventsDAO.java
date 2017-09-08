package ru.epam.spring.hometask.database;

import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.abstract_layout.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class EventsDAO implements EventService {
    private List<Event> data;
    private static int tempId = 0;

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
        if(data == null)data = new ArrayList<>();
        event.setId(data.size()+1);
//        event.assignAuditorium();
        return data.add(event);
    }

    @Override
    public boolean remove(@Nonnull Event event) {
        return data.remove(event);
    }

    @Override
    public Event remove(@Nonnull int id) {
        return data.remove(id-1);
    }

    @Override
    public Event getById(@Nonnull int id) {
        Event e = data.get(id-1);
        return e;
    }

    @Nonnull
    @Override
    public List<Event> getAll() {
        List<Event> allEvents = new ArrayList<>();
        allEvents.addAll(data);
        return allEvents;
    }

    public void setData(List<Event> data) {
        this.data = data;
    }

    public static int getTempId() {
        return tempId;
    }

    public static void setTempId(int tempId) {
        EventsDAO.tempId = tempId;
    }
}
