package nl.qnh.qforce.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import nl.qnh.qforce.domain.Gender;
import nl.qnh.qforce.domain.Movie;
import nl.qnh.qforce.domain.Person;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "name", "birthYear", "gender", "height", "weight", "movies"})
public class PersonImpl implements Person, Serializable{
	
	private static final long serialVersionUID = -7841396682059629585L;
	
	private long id;
	private String name;
	private String birthYear;
	@JsonSerialize(using = GenderSerializer.class)
	@JsonDeserialize(using = GenderDeserializer.class)
	private Gender gender;
	private int height;
	private int weight;
	private List<String> films;
	private List<Movie> movies;
	private String url;

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	@JsonProperty("birth_year")
	public String getBirthYear() {
		return this.birthYear;
	}

	public Gender getGender() {
		return this.gender;
	}

	public Integer getHeight() {
		return this.height;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public List<Movie> getMovies() {
		return this.movies;
	}

	@JsonIgnore
	public List<String> getFilms() {
		return films;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("birth_year")
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@JsonProperty
	public void setFilms(List<String> films) {
		this.films = films;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@JsonIgnore
	public String getUrl() {
		return url;
	}
	
	@JsonProperty
	public void setUrl(String url) {
		this.url = url;
	}

}
