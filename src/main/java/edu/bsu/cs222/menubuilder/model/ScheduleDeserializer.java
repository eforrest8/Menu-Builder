package edu.bsu.cs222.menubuilder.model;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;

public class ScheduleDeserializer {
    public Schedule deserialize(Reader reader) {
        try {
            JsonParser parser = JsonFactory.builder()
                    .build()
                    .createParser(reader);
            parser.setCodec(new ObjectMapper());
            return parser.readValueAs(Schedule.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
