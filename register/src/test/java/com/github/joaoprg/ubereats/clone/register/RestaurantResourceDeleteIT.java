package com.github.joaoprg.ubereats.clone.register;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
public class RestaurantResourceDeleteIT {

    private static final String RESTAURANT_ID = "f67e429c-ddf3-427f-8503-7afee054ae14";
    private static final String DISH_ID = "811d860d-689e-4f3d-b05b-82f2ade3c6f0";


    @Test
    @DataSet("delete-restaurant.yml")
    public void testDeleteRestaurantNoContent() {
        given()
                .contentType(ContentType.JSON)
                .with()
                .pathParam("restaurant_id", RESTAURANT_ID)
                .when()
                .delete("/restaurants/{restaurant_id}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }


    @Test
    @DataSet("delete-dish.yml")
    public void testDeleteDishNoContent() {
        given()
                .contentType(ContentType.JSON)
                .with()
                .pathParam("restaurant_id", RESTAURANT_ID)
                .pathParam("dish_id", DISH_ID)
                .when()
                .delete("/restaurants/{restaurant_id}/dishes/{dish_id}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }
}
