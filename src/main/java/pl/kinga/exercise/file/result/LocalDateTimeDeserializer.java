package pl.kinga.exercise.file.result;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import static pl.kinga.exercise.Application.FORMATTER;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext context)
			throws IOException, JsonProcessingException {

		return LocalDateTime.parse(p.getValueAsString(), FORMATTER);
	}

}
