package org.juon.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.servlet.http.PushBuilder;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
@SessionAttributes("event")
public class EventController {

    @HelloGetMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping(value = "/events/{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String eventsId(@PathVariable Integer id) {
        return "events :: " + id;
    }

    @PostJsonMapping(value = "/event")
    @ResponseBody
    public String createEvent(PushBuilder builder) {
        return "createEvent";
    }

    @GetMapping("/events/write")
    public String formWrite(Model model) {
        model.addAttribute("event", new Event());

        return "events/form-name";
    }

    @PostMapping("/events/add/name")
    public String createEventName(@Valid @ModelAttribute Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "events/form-name";
        }

        return "redirect:/events/write/limit";
    }

    @GetMapping("/events/write/limit")
    public String formWriteLimit(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        return "events/form-limit";
    }

    @PostMapping("/events/add/limit")
    public String createEventLimit(@Valid @ModelAttribute Event event, BindingResult bindingResult,
                                   SessionStatus sessionStatus,
                                   RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return "events/form-limit";
        }

        sessionStatus.setComplete();

// query parameter로 전달
//        attributes.addAttribute("name", event.getName());
//        attributes.addAttribute("limit", event.getLimit());
        // session에 일회용 어트리뷰트로 담긴다. 즉 꺼내면 사라진다
        attributes.addFlashAttribute("newEvent", event);

        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String goList(@ModelAttribute("newEvent") Event event, HttpSession session, Model model,
                         @SessionAttribute LocalDateTime visitTime) {

        System.out.println(visitTime.toString());

        List<Event> eventList = new ArrayList<>();

        eventList.add(event);
        model.addAttribute("eventList", eventList);

        return "events/list";
    }

    @ExceptionHandler
    public String eventErrorHandler(EventException eventException, Model model) {
        model.addAttribute("message", "runtime exception ::: " + eventException.getMessage());

        return "error";
    }

    @GetMapping("/events/error")
    public String getError() {
        throw new EventException("EventException");
    }
}
