package api;

import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestApi {

    public String baseURI = RestAssured.baseURI = "http://info.venturepulse.org:8080/service-webapp/api/AllEmployeeResources\n";
    String employees = "employees";
    String employeeWithId = "employee/";
    String create = "create";
    String update = "update/";
    String delete = "delete/";

    @Test @Ignore
    public void getAllEmployees() {
        Response response = given().when().get(employees).then().assertThat().statusCode(200).extract().response();
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());

        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println(jsonPath.get("empName"));
    }
    @Test @Ignore
    public void getOneEmployee() {
        Response response = given().when().get(employeeWithId + 166).then().assertThat().statusCode(200).extract().response();
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());

        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println(jsonPath.get("empName"));
    }

    @Test @Ignore
    public void testPostData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "nusrat sultana");
        jsonObject.put("salary", "10000");
        jsonObject.put("age", "24");

        Response response = given().header("Content-Type", "application/json")
                .body(jsonObject.toString()).when().post(create).then().statusCode(200).extract().response();
        System.out.println(response.asString());
    }

    @Test @Ignore
    public void testPutCall() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "nusrat");
        jsonObject.put("salary", "100000");
        jsonObject.put("age", "24");

        Response response = given().contentType(ContentType.JSON).body(jsonObject.toString()).put(update + 2063);
        System.out.println(response.getStatusCode());

    }
    @Test @Ignore
    public void testDelete() {
        Response response = given().contentType(ContentType.JSON).delete(delete + 2063);
        System.out.println(response.statusCode());
    }
}
