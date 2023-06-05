package com.github.joaoprg.ubereats.clone.register;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
public class RestaurantResourceGetIT {


    @Test
    @DataSet("get-restaurants.yml")
    public void testGetRestaurantsOk() {
        Integer[] pageList = {1, 2};
        Integer[] perPageList = {1, 10};
        CombinationApprovals.verifyAllCombinations((page, perPage) ->
                        given()
                                .with()
                                .queryParam("page", page)
                                .queryParam("per_page", perPage)
                                .when()
                                .get("/restaurants")
                                .then()
                                .statusCode(Response.Status.OK.getStatusCode())
                                .extract().asPrettyString()
                , pageList, perPageList);
    }

    @Test
    @DataSet("get-dishes.yml")
    public void testGetDishesOk() {
        Integer[] pageList = {1, 2};
        Integer[] perPageList = {1, 10};
        CombinationApprovals.verifyAllCombinations((page, perPage) ->
                        given()
                                .with()
                                .queryParam("page", page)
                                .queryParam("per_page", perPage)
                                .when()
                                .get("/restaurants/dishes")
                                .then()
                                .statusCode(Response.Status.OK.getStatusCode())
                                .extract().asPrettyString()
                , pageList, perPageList);
    }

    @Test
    @DataSet("get-dishes-by-restaurant.yml")
    public void testGetDishesByRestaurantOk() {
        Integer[] pageList = {1, 2};
        Integer[] perPageList = {1, 10};
        String[] restaurantIdList = {"f67e429c-ddf3-427f-8503-7afee054ae14", "ea6b2b78-7a0f-4f81-914f-406fadcc53f7"};
        CombinationApprovals.verifyAllCombinations((page, perPage, restaurantId) ->
                        given()
                                .with()
                                .pathParam("restaurant_id", restaurantId)
                                .queryParam("page", page)
                                .queryParam("per_page", perPage)
                                .when()
                                .get("/restaurants/{restaurant_id}/dishes")
                                .then()
                                .statusCode(Response.Status.OK.getStatusCode())
                                .extract().asPrettyString()
                , pageList, perPageList, restaurantIdList);
    }

}
