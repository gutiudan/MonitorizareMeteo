package ro.mta.se.lab.Class;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    Parser parser = null;
    String jsonText = "{\"coord\":{\"lon\":26.1063,\"lat\":44.4323},\"" +
            "weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}]," +
            "\"base\":\"stations\",\"main\":{\"temp\":269.89,\"feels_like\":265.17,\"temp_min\":269.15," +
            "\"temp_max\":271.48,\"pressure\":1017,\"humidity\":68},\"visibility\":10000," +
            "\"wind\":{\"speed\":2.57,\"deg\":250},\"clouds\":{\"all\":20},\"dt\":1610880738," +
            "\"sys\":{\"type\":1,\"id\":6911,\"country\":\"RO\",\"sunrise\":1610862431,\"sunset\":1610895825}," +
            "\"timezone\":7200,\"id\":683506,\"name\":\"Bucharest\",\"cod\":200}";
    @Before
    public void setUp(){
        parser = Parser.getInstance();
    }
    @Test
    public void getValue(){
        assertEquals("683506", parser.getValue("id", "int", jsonText));
        assertEquals("Bucharest", parser.getValue("name", "String", jsonText));
    }

    @Test
    public void getObject_getValue(){
        assertEquals("1610862431", parser.getObject_getValue("sys", "sunrise", "long", jsonText));
        assertEquals("1610895825", parser.getObject_getValue("sys", "sunset", "long", jsonText));
    }

    @Test
    public void getArray_getObject_getValue(){
        assertEquals("few clouds", parser.getArray_getObject_getValue("weather", "description", "String", jsonText));
        assertEquals("02d", parser.getArray_getObject_getValue("weather", "icon", "String", jsonText));
    }
}