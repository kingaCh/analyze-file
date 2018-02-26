package pl.kinga.exercise.file.result;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import static pl.kinga.exercise.Application.FORMATTER;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

	@Override
	public void serialize(LocalDateTime date, JsonGenerator generator, SerializerProvider serializer)
			throws IOException, JsonProcessingException {
		generator.writeString(date.format(FORMATTER));

	}
}
