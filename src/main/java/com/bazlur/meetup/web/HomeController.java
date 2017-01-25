package com.bazlur.meetup.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/26/17.
 */
@Controller
public class HomeController {

	@GetMapping("/")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String index() {

		return "Hello World";
	}
}
