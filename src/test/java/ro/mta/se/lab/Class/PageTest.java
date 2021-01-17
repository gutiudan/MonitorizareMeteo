package ro.mta.se.lab.Class;

import org.junit.Before;
import org.junit.Test;

import static  org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class PageTest {
    private Page pageTest = null;
    private Parser parserTest = null;
    @Before
    public void setUp(){
        pageTest = new Page();
        pageTest.Read("http://api.openweathermap.org/data/2.5/weather?q=Bucharest&appid=98de787066a4c24216f834be520b4726");
        parserTest = mock(Parser.class);
    }
    @Test
    public void getCityId(){
        when(parserTest.getValue("id", "int", pageTest.getResponseBody())).thenReturn("683506");
        assertEquals("683506", parserTest.getValue("id", "int", pageTest.getResponseBody()));
    }

    @Test
    public void getCityName(){
        when(parserTest.getValue("name", "String", pageTest.getResponseBody())).thenReturn("Bucharest");
        assertEquals("Bucharest", parserTest.getValue("name", "String", pageTest.getResponseBody()));
    }

    @Test
    public void getCityLatitude(){
        when(parserTest.getObject_getValue("coord", "lat", "double", pageTest.getResponseBody())).thenReturn("44.4323");
        assertEquals("44.4323", parserTest.getObject_getValue("coord", "lat", "double", pageTest.getResponseBody()));
    }

    @Test
    public void getCityLongitude(){
        when(parserTest.getObject_getValue("coord", "lon", "double", pageTest.getResponseBody())).thenReturn("26.1063");
        assertEquals("26.1063", parserTest.getObject_getValue("coord", "lon", "double", pageTest.getResponseBody()));
    }

    @Test
    public void getCountryCode(){
        when(parserTest.getObject_getValue("sys", "country", "String", pageTest.getResponseBody())).thenReturn("RO");
        assertEquals("RO", parserTest.getObject_getValue("sys", "country", "String", pageTest.getResponseBody()));
    }

}