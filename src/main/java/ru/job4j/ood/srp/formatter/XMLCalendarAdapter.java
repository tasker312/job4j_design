package ru.job4j.ood.srp.formatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.text.ParseException;
import java.util.Calendar;

public class XMLCalendarAdapter extends XmlAdapter<String, Calendar> {

    private final DateTimeParser<Calendar> parser;

    public XMLCalendarAdapter(DateTimeParser<Calendar> parser) {
        this.parser = parser;
    }

    @Override
    public String marshal(Calendar v) {
        return parser.parse(v);
    }

    @Override
    public Calendar unmarshal(String v) throws ParseException {
        throw new UnsupportedOperationException();
    }
}
