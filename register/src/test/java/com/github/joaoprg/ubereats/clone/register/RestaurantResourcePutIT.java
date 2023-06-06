package com.github.joaoprg.ubereats.clone.register;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.joaoprg.ubereats.clone.register.model.AddressRead;
import com.github.joaoprg.ubereats.clone.register.model.DishUpdate;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantUpdate;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
public class RestaurantResourcePutIT {

    private static final String RESTAURANT_ID = "f67e429c-ddf3-427f-8503-7afee054ae14";
    private static final String DISH_ID = "811d860d-689e-4f3d-b05b-82f2ade3c6f0";


    @Test
    @DataSet("put-restaurant.yml")
    public void testPutRestaurantOk() {
        final RestaurantUpdate restaurantUpdate = RestaurantUpdate.
                builder()
                .owner("Ted's Grill")
                .name("Ted")
                .address(AddressRead
                        .builder()
                        .latitude(35.2312)
                        .longitude(5.1393)
                        .build())
                .build();
        final String result = given()
                .contentType(ContentType.JSON)
                .with()
                .pathParam("restaurant_id", RESTAURANT_ID)
                .body(restaurantUpdate)
                .when()
                .put("/restaurants/{restaurant_id}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().asString();
        Approvals.verifyJson(result);
    }


    @Test
    @DataSet("put-dish.yml")
    public void testPutDishOk() {
        final DishUpdate dishUpdate = DishUpdate
                .builder()
                .price(8.0)
                .build();
        final String result = given()
                .contentType(ContentType.JSON)
                .with()
                .pathParam("restaurant_id", RESTAURANT_ID)
                .pathParam("dish_id", DISH_ID)
                .body(dishUpdate)
                .when()
                .put("/restaurants/{restaurant_id}/dishes/{dish_id}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().asString();
        Approvals.verifyJson(result);
    }
}
