package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.srp.formatter.XMLCalendarAdapter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.Store;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {

    private final Store store;
    private final Marshaller marshaller;

    public XmlReportEngine(Store store, DateTimeParser<Calendar> parser) throws JAXBException {
        this.store = store;
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        marshaller = context.createMarshaller();
        marshaller.setAdapter(new XMLCalendarAdapter(parser));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml;
        Employees employees = new Employees(store.findBy(filter));
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employees, writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }
}
