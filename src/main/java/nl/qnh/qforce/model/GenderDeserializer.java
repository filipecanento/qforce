package nl.qnh.qforce.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import nl.qnh.qforce.domain.Gender;

public class GenderDeserializer extends StdDeserializer<Gender>{
	
	private static final long serialVersionUID = 4466754968568103625L;

	public GenderDeserializer() { 
		this(null);
	}
	
	public GenderDeserializer(Class<Gender> t) {
		super(t);
	}

	@Override
	public Gender deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		switch(p.getText().toUpperCase()) {
		case "MALE":
			return Gender.MALE;
		case "FEMALE":
			return Gender.FEMALE;
		case "N/A":
			return Gender.NOT_APPLICABLE;
		default:
			return Gender.UNKNOWN;
		}
	}
}
