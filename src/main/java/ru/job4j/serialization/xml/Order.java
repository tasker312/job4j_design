package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    @XmlElement
    private double price;

    @XmlElement
    private String comment;

    @XmlElement
    private Person client;

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private Product[] products;

    @XmlElement
    private boolean isFinished;

    public Order() {
    }

    public Order(String comment, Person client, Product[] products) {
        this.comment = comment;
        this.client = client;
        this.products = products;
        Arrays.stream(products).forEach(product -> price += product.getPrice());
        isFinished = false;
    }

    @Override
    public String toString() {
        return "Order{"
                + "price=" + price
                + ", comment='" + comment + '\''
                + ", client=" + client
                + ", products=" + Arrays.toString(products)
                + ", isFinished=" + isFinished
                + '}';
    }

    public static void main(String[] args) throws Exception {
        Order order = new Order("hello", new Person("Ivan", "88888888888"),
                new Product[]{new Product("Milk", 20.5), new Product("Chocolate", 40)});
        System.out.println(order);
        JAXBContext context = JAXBContext.newInstance(Order.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(order, writer);
            xml = writer.getBuffer().toString();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Order result = (Order) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
        System.out.println(xml);
    }
}
