package org.juon.demowebmvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(value = "/hello", method = RequestMethod.GET)
public @interface HelloGetMapping {
    String[] consumes() default {};
}
