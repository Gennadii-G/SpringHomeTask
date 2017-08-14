package ru.epam.spring.hometask.utils.adapter;

import ru.epam.spring.hometask.abstract_layout.service.AuditoriumService;
import ru.epam.spring.hometask.abstract_layout.service.EventService;
import ru.epam.spring.hometask.domain.Auditorium;
import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.EventRating;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class EventConsoleAdapter {

    private EventService eventService;
    private AuditoriumService audService;

    public EventConsoleAdapter(EventService eventService, AuditoriumService audService) {
        this.eventService = eventService;
        this.audService = audService;
    }

    public String remove(String id){
        return "событие " + eventService.remove(Integer.parseInt(id)).getName() + " удалено";
    }

    public String getAllEvents(){
        StringBuilder strB = new StringBuilder();
        for(Event event : eventService.getAll()){
            strB.append(event.toString() + "\n");
        }
        return strB.toString();
    }

    public String getEvent(String Name){
        return eventService.getByName(Name).toString();
    }

    public String save(Map<String, String> parameters){

        Event event = new Event();
        event.setName(parameters.get("eventName"));
        event.setBasePrice(Double.parseDouble(parameters.get("basePrice")));
        event.setRating(EventRating.valueOf(parameters.get("rating")));

        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        String[] airDateArr = parameters.get("airDates").split(",");
        for(String strAirDate : airDateArr){
            LocalDateTime airDate = LocalDateTime.parse(strAirDate, Event.FORMATTER);
            auditoriums.put(airDate, audService.getByName(parameters.get("auditorium")));
        }
        event.setAuditoriums(auditoriums);

        return "событие добавлено";
    }


}
