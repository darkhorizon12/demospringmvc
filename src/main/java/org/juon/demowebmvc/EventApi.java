package org.juon.demowebmvc;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventApi {

    @PostMapping("/add")
    public Event createEvent(HttpEntity<Event> event) {
        HttpHeaders headers = event.getHeaders();
        System.out.println(headers);
        return event.getBody();
    }
}
