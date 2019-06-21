package integration;

import application.DTO.FoodTruckDTO;
import application.context.Configuration;
import application.context.Context;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import application.Main;

@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(Main.class)

public class IntegrationTest {
    @LocalServerPort
    private int port;


    private TestRestTemplate restTemplate = new TestRestTemplate();


    @org.springframework.context.annotation.Configuration
    static class AppConfig {

        @Bean
        public Context getContext() {
            Configuration configurationManager = new Configuration("","",3,2);
            ArrayList<FoodTruckDTO> list = new ArrayList<>();
            list.add(new FoodTruckDTO("FoodTruck0","fooditem0",1,1));
            list.add(new FoodTruckDTO("FoodTruck01","fooditem01",-1,1));
            list.add(new FoodTruckDTO("FoodTruck1","fooditem1",-1,-1));
            list.add(new FoodTruckDTO("FoodTruck2","fooditem2",1,-1));
            return new Context(configurationManager,list);
        }
    }

    @Test
    public void contextLoads()  {

    }

    @Test
    public void testAutocompletion()  {

        String s = getFoodTrucksForAutocompletion("Foo");// = this.restTemplate.getForObject("http://localhost:" + port + "/search?searchString=Foo",                String.class);

        int length = JsonPath.read(s, "$.listOfFoodTrucks.length()");
        int status = JsonPath.read(s, "$.status");

        assert(length == 2);
        assert(status == 0);
    }

    @Test
    public void testMapWithThreeFoodTrucks()  {

        String requestedString =  getFoodTrucksForMap(createJson(2,2,0,-2));

        int length = JsonPath.read(requestedString, "$.listOfFoodTrucks.length()");
        int status = JsonPath.read(requestedString, "$.status");

        assert(length == 2);
        assert(status == 0);
    }

    @Test
    public void testMapWithNoFoodTrucks()  {

        String requestedString =  getFoodTrucksForMap(createJson(10,2,8,-2));

        int status = JsonPath.read(requestedString, "$.status");

        assert(status == 1);
    }

    @Test
    public void testMapTooMany()  {

        String requestedString =  getFoodTrucksForMap(createJson(2,2,-2,-2));

        int status = JsonPath.read(requestedString, "$.status");

        assert(status == 2);
    }

    private String createJson(double north, double east, double south, double west){
        return "{\"northBorder\":"+north+",\"eastBorder\":"+east+",\"southBorder\":"+south+",\"westBorder\":"+west+"}";
    }

    private String getFoodTrucksForMap(String requestJson){
        String url = "http://localhost:" + port + "/api/foodTruckView";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        String result = restTemplate.postForObject(url, entity, String.class);

        return result;
    }

    /**
     * @param searchString
     * @return
     */
    private String getFoodTrucksForAutocompletion(String searchString){
        String url = "http://localhost:" + port + "/api/search?searchString="+searchString;

        String result = this.restTemplate.getForObject(url,
                String.class);

        return result;
    }
}