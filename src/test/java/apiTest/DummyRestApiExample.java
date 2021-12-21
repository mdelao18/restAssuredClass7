package apiTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import helpers.DataCountry;
import helpers.DataHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.testng.annotations.*;
import dataEntities.Employee;
import dataEntities.Country;


public class DummyRestApiExample {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Employee employee;
    Country country;

    @BeforeTest
    public void setUp(){
        requestSpecification = new RequestSpecBuilder().
                //setBaseUri("http://dummy.restapiexample.com/api/v1").build();
        //setBaseUri("http://dummy.restapiexample.com/api/v1").setContentType(ContentType.JSON).build();
        setBaseUri("http://www.zippopotam.us/us/90210").setContentType(ContentType.JSON).build();
        responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void GetallEmployessReturns200Code(){
        //initial structure before refactor with specifications
        /*given().
                when().get("http://dummy.restapiexample.com/api/v1/employees").
                then().assertThat().
                statusCode(200).log().body();*/
        //refactor to use specficications
        given().spec(requestSpecification).
                get("employees").
                then().spec(responseSpecification).
                log().body();
    }

    @Test
    public void GetOneEmpReturnsCorrectDataAndStatusCode200(){
        //initial structure before refactor with specifications
        /*given().
                when().get("http://dummy.restapiexample.com/api/v1/employee/24").
                then().assertThat().
                body("data.employee_name", equalTo("Doris Wilder")).
                and().
                statusCode(200).
                log().body();*/
        //refactor to use specficications
        given().
                spec(requestSpecification).
                get("employee/24").
                then().
                spec(responseSpecification).
                assertThat().body("data.employee_name", equalTo("Doris Wilder")).
                log().body();
    }


    @Test
    public void CreateOneEmployee(){
        initEmployee();
        given().
                spec(requestSpecification.body(employee)).
                //spec(requestSpecification.body("{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}")).
                post("create").then().spec(responseSpecification).log().body();
    }

    private void initEmployee(){
        employee = new Employee(DataHelper.generateName(), DataHelper.generateRandomSalary(), DataHelper.generateRandomAge());
    }

    @Test
    public void DeleteAnEmployeeRecord(){
        given().
                spec(requestSpecification).delete("delete/15").
                then().
                spec(responseSpecification).
                assertThat().body("message", equalTo("Successfully! Record has been deleted")).log().body();
    }

    @Test
    public void UpdateOneEmployee(){
        initEmployee();
        given().spec(requestSpecification.body(employee)).
                put("update/1").then().spec(responseSpecification).log().body();

    }

    //Quiz # 4
    //Agregar 2 pruebas para el API de zippopotamus.

    String places[] = {"placeName:Heredia", "longitude:23", "state:SanRafael", "stateabbreviation:SR", "latitude: 22"};

    private void initCountry(){
        country = new Country("3000", "CostaRica", "CR", places );
    }

    //Get

    @Test
    public void GetCountry() {

        given().spec(requestSpecification).
                get("country").
                then().spec(responseSpecification).
                log().body();
    }

    // Post
        @Test
        public void CreateCountry(){
            initCountry();
            given().
                    spec(requestSpecification.body(country)).
                            post("create").then().spec(responseSpecification).log().body();
        }
}