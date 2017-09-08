package ru.epam.spring.hometask.adapter;

import ru.epam.spring.hometask.abstract_layout.service.AuditoriumService;
import ru.epam.spring.hometask.abstract_layout.service.EventService;
import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.EventRating;

import java.util.Map;

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
        String eventName = eventService.getByName(Name).getName();
        if(eventName == null) return "пусто";
        return eventName;
    }

    public String save(Map<String, String> parameters){

        Event event = new Event();
        event.setName(parameters.get("eventName"));
        event.setBasePrice(Double.parseDouble(parameters.get("basePrice")));
        event.setRating(EventRating.valueOf(parameters.get("rating")));
        event.setAirDates(parameters.get("airDates").split(","));
        event.refreshAuditoriums();

        eventService.save(event);

        return "событие добавлено";
    }


}
