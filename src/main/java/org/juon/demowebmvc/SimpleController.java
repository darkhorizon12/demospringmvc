package org.juon.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class SimpleController {

    @GetMapping("/simples/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable Long id, @MatrixVariable String name) {
        Event e = new Event();
        e.setId(id);
        e.setName(name);

        return e;
    }

    @PostMapping("/simples/add")
    @ResponseBody
    public Event createEvent(@RequestParam Map<String, String> paramMap) {
        Event event = new Event();
        event.setName(paramMap.get("name"));

        return event;
    }
}
