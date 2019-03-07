package org.juon.demowebmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void test_form() throws Exception {
        this.mockMvc.perform(get("/events/write"))
                .andDo(print())
                .andExpect(view().name("events/form"))
                .andExpect(model().attributeExists("event"));
    }

    @Test
    public void test_post() throws Exception {
        this.mockMvc.perform(post("/events/create")
                        .param("name", "juon")
                        .param("limit", "-11"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(model().hasErrors());
    }

    @Test
    public void test_hello() throws Exception {
        this.mockMvc.perform(get("/hello")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test_event() throws Exception {
        this.mockMvc.perform(get("/events/1")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("events :: 1"));

    }

    @Test
    public void test_create_event() throws Exception {
        this.mockMvc.perform(post("/event")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

    }
}