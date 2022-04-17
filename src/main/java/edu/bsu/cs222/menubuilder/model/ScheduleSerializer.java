package edu.bsu.cs222.menubuilder.model;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Writer;

public class ScheduleSerializer {

    private final Schedule schedule;

    public ScheduleSerializer(Schedule schedule) {
        this.schedule = schedule;
    }

    public void serialize(Writer writer) {
        JsonFactory factory = new JsonFactory();
        try {
            factory.createGenerator(writer).setCodec(new ObjectMapper()).writePOJO(schedule);
        } catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
        }
    }
}
