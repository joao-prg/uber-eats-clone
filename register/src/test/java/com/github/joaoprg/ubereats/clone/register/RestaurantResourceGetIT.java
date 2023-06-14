package com.github.joaoprg.ubereats.clone.register;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.keycloak.client.KeycloakTestClient;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.quarkus.test.keycloak.server.KeycloakTestResourceLifecycleManager.getAccessToken;
import static io.restassured.RestAssured.given;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
public class RestaurantResourceGetIT {

    final KeycloakTestClient keycloakClient = new KeycloakTestClient();

    @Test
    @DataSet("get-restaurants.yml")
    public void testGetRestaurantsOk() {
        final Integer[] pageList = {1, 2};
        final Integer[] perPageList = {1, 10};
        CombinationApprovals.verifyAllCombinations((page, perPage) ->
                        given()
                                .auth()
                                .oauth2(getAccessToken("alice"))
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
        final Integer[] pageList = {1, 2};
        final Integer[] perPageList = {1, 10};
        CombinationApprovals.verifyAllCombinations((page, perPage) ->
                        given()
                                .auth()
                                .oauth2(getAccessToken("alice"))
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
        final Integer[] pageList = {1, 2};
        final Integer[] perPageList = {1, 10};
        final String[] restaurantIdList = {"f67e429c-ddf3-427f-8503-7afee054ae14",
                "ea6b2b78-7a0f-4f81-914f-406fadcc53f7"};
        CombinationApprovals.verifyAllCombinations((page, perPage, restaurantId) ->
                        given()
                                .auth()
                                .oauth2(getAccessToken("alice"))
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
