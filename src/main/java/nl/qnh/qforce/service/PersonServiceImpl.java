package nl.qnh.qforce.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import nl.qnh.qforce.domain.Movie;
import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.model.MovieImpl;
import nl.qnh.qforce.model.PeopleList;
import nl.qnh.qforce.model.PersonImpl;

@Service
public class PersonServiceImpl implements PersonService{

	private static final String PARAMETERS = "parameters";

	private static final String USER_AGENT = "User-Agent";

	@Value("${user.agent}")
	private String userAgent;
	
	@Value("${api.url}")
	private String apiURL;

	@Value("${api.url.people.get}")
	private String peopleAPIURL;

	@Value("${api.url.people.search}")
	private String searchPeopleAPIURL;

	private RestTemplate restTemplate = new RestTemplate();
	
	private HttpEntity<String> entity;

	@PostConstruct
	public void init() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add(USER_AGENT, userAgent);
		entity = new HttpEntity<String>(PARAMETERS, headers);
	}

	public List<Person> search(String query) {

		List<Person> people = new LinkedList<>();

		ResponseEntity<PeopleList> result = restTemplate.exchange(searchPeopleAPIURL+query, HttpMethod.GET, entity, PeopleList.class);

		if(HttpStatus.OK == result.getStatusCode()) {
			for(PersonImpl person : result.getBody().getResults()) {
				getMoviesFromFilmURLs(person);
				people.add(person);
			}
		}

		return people;
	}

	private void getMoviesFromFilmURLs(PersonImpl person) {
		person.setId(getIdFromURL(person.getUrl()));
		List<Movie> movies = new ArrayList<>();
		ResponseEntity<MovieImpl> movieResult;
		for(String film : person.getFilms()) {
			movieResult = restTemplate.exchange(film, HttpMethod.GET, entity, MovieImpl.class);
			if(HttpStatus.OK == movieResult.getStatusCode()) {
				movies.add(movieResult.getBody());
			}
		}
		if(!movies.isEmpty()) {
			person.setMovies(movies);
		}
	}

	private long getIdFromURL(String url) {
		return Integer.valueOf(url.replaceAll("\\D+", ""));
	}

	public Optional<Person> get(long id) {
		ResponseEntity<PersonImpl> result = restTemplate.exchange(peopleAPIURL+id, HttpMethod.GET, entity, PersonImpl.class);
		PersonImpl person = result.getBody();
		if(HttpStatus.OK == result.getStatusCode()) {
			getMoviesFromFilmURLs(person);
		}

		return Optional.ofNullable((Person)person);
	}
	
}
