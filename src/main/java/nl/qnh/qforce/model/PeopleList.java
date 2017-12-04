package nl.qnh.qforce.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "count", "next", "previous", "results"})
public class PeopleList implements Serializable{
	
	private static final long serialVersionUID = -5121367799123890328L;

	private int count;
	
	private String next;
	
	private String previous;
	
	private List<PersonImpl> results;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public List<PersonImpl> getResults() {
		return results;
	}

	public void setResults(List<PersonImpl> results) {
		this.results = results;
	}
	
}
