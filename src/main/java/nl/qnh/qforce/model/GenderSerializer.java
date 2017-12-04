package nl.qnh.qforce.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import nl.qnh.qforce.domain.Gender;

public class GenderSerializer extends StdSerializer<Gender> {

	private static final long serialVersionUID = 7406019505472190490L;

	public GenderSerializer() { 
		this(null);
	}
	
	public GenderSerializer(Class<Gender> t) {
		super(t);
	}

	@Override
	public void serialize(Gender gender, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(gender.toString().toLowerCase());
	}

}
