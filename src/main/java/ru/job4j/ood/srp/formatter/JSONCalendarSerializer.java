package ru.job4j.ood.srp.formatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Calendar;

public class JSONCalendarSerializer implements JsonSerializer<Calendar> {

    private final DateTimeParser<Calendar> parser;

    public JSONCalendarSerializer(DateTimeParser<Calendar> parser) {
        this.parser = parser;
    }

    @Override
    public JsonElement serialize(Calendar src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(parser.parse(src));
    }
}
