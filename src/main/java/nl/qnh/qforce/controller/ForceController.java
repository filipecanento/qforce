package nl.qnh.qforce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.service.AnalyticsService;
import nl.qnh.qforce.service.PersonService;

@Controller
public class ForceController {

	private static final String APPLICATION_JSON = "application/json";

	@Autowired
	PersonService personService;

	@Autowired
	AnalyticsService analyticsService;
	
	@GetMapping(value="/persons/{id}", produces = APPLICATION_JSON)
	public ModelAndView get(@PathVariable long id) {
		try {
			analyticsService.register("get", id);
			Optional<Person> result = personService.get(id);
			if(result.isPresent()) {
				return new ModelAndView("result", HttpStatus.OK).addObject("result", getPrettyPrint(result.get()));
			}
		}catch (HttpClientErrorException e) {
			// log
		}
		return new ModelAndView("result", HttpStatus.NOT_FOUND).addObject("result", "404 - This is not the resource you are looking for...");
	}

	@GetMapping(value="/persons", produces = APPLICATION_JSON)
	public ModelAndView search(@RequestParam("q") String q) {
		analyticsService.register("search", q);
		return new ModelAndView("result", HttpStatus.OK).addObject("result", getPrettyPrint(personService.search(q)));
	}
	
	private String getPrettyPrint(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			return "error";
		}
	}

}
