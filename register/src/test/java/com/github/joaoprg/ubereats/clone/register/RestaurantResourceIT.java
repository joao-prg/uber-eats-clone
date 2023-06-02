package com.github.joaoprg.ubereats.clone.register;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.approvaltests.Approvals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.ws.rs.core.Response;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
public class RestaurantResourceIT {


    private static Stream<Arguments> testGetRestaurantsOk() {
        return Stream.of(
                Arguments.of(1, 10),
                Arguments.of(1, 1),
                Arguments.of(2, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("testGetRestaurantsOk")
    @DataSet("get-restaurants.yml")
    public void testGetRestaurantsOk(int page, int perPage) {
        String result = given()
                .with()
                .queryParam("page", page)
                .queryParam("per_page", perPage)
                .when()
                .get("/restaurants")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().asString();
        Approvals.verifyJson(result);
    }

}
