package nl.qnh.qforce.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import nl.qnh.qforce.domain.Movie;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "title", "episode", "director", "releaseDate"})
public class MovieImpl implements Movie, Serializable{
	
	private static final long serialVersionUID = 2140775147676140914L;

	private String title;
	
	private Integer episode;
	
	private String director;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate releaseDate;

	public String getTitle() {
		return this.title;
	}

	@JsonProperty("episode")
	public Integer getEpisode() {
		return this.episode;
	}
	
	@JsonProperty("episode_id")
	public void setEpisode(Integer episode) {
		this.episode = episode;
	}

	public String getDirector() {
		return this.director;
	}

	@JsonProperty("release_date")
	public LocalDate getReleaseDate() {
		return this.releaseDate;
	}

}
