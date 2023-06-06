package com.github.joaoprg.ubereats.clone.register;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.joaoprg.ubereats.clone.register.model.AddressCreate;
import com.github.joaoprg.ubereats.clone.register.model.DishCreate;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantCreate;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonObject;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
public class RestaurantResourcePostIT {

    private static final String RESTAURANT_ID = "f67e429c-ddf3-427f-8503-7afee054ae14";

    @Test
    @DataSet("post-restaurant.yml")
    public void testPostRestaurantOk() {
        final RestaurantCreate restaurantCreate = RestaurantCreate
                .builder()
                .owner("Haru")
                .name("Tokyo Sushi")
                .address(AddressCreate
                        .builder()
                        .latitude(40.7223)
                        .longitude(11.1393)
                        .build())
                .build();
        final String result = given()
                .contentType(ContentType.JSON)
                .with()
                .body(restaurantCreate)
                .when()
                .post("/restaurants")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().asString();
        final JsonObject resultJson = new JsonObject(result);
        Assertions.assertTrue(resultJson.containsKey("id") && resultJson.containsKey("created_at"));
        resultJson.remove("id");
        resultJson.remove("created_at");
        Approvals.verifyJson(resultJson.toString());
    }


    @Test
    @DataSet("post-dish.yml")
    public void testPostDishOk() {
        final DishCreate dishCreate = DishCreate
                .builder()
                .name("Curry chicken")
                .description("Curry chicken with mashed potatoes.")
                .price(7.5)
                .build();
        final String result = given()
                .contentType(ContentType.JSON)
                .with()
                .pathParam("restaurant_id", RESTAURANT_ID)
                .body(dishCreate)
                .when()
                .post("/restaurants/{restaurant_id}/dishes")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().asString();
        final JsonObject resultJson = new JsonObject(result);
        Assertions.assertTrue(resultJson.containsKey("id"));
        resultJson.remove("id");
        Approvals.verifyJson(resultJson.toString());
    }
}
