package com.github.joaoprg.ubereats.clone.register;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
public class RestaurantResourceIT {

    @Test
    @DataSet("get-restaurants.yml")
    public void testBuscarRestaurantes() {
        String result = given()
                .when().get("/restaurants")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().asString();
        Approvals.verifyJson(result);
    }

}
