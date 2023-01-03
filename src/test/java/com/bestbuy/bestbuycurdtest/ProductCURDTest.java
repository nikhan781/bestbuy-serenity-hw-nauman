package com.bestbuy.bestbuycurdtest;

import com.bestbuy.bestbuyinfo.ProductSteps;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SerenityRunner.class)
public class ProductCURDTest extends TestBase {
    static int id;
    static String name = "Duracell - AAA Batteries (12-Pack)";
    static String type = "HardGood";
    static double price = 10.99;
    static String upc = "041333424043";
    static int shipping = 0;
    static String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 12-pack";
    static String manufacturer = "Duracell";
    static String model = "MN21200B4Z";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-12-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static String createdAt = "2018-10-17T17:58:03.298Z";


    @Steps
    ProductSteps productSteps;

    @Test
    public void test001() {
        productSteps.getAllRecords();
    }


    @Test
    public void test002() {
        ValidatableResponse response = productSteps.createAProduct(id, name, type, price, upc, shipping, description, manufacturer, model, url, image);
        response.log().all().body("name", equalTo(name));
        id = response.extract().path("id");
    }

    @Test
    public void test003() {
        ValidatableResponse response = productSteps.getSingleRecord(id).statusCode(200);
        response.log().all().body("id", equalTo(id));
    }

    @Test
    public void test004() {
        name = "Duracell Plus - AAA Batteries (12-Pack)";
        ValidatableResponse response = productSteps.updateARecord(id, name, type, price, upc, shipping, description, manufacturer, model, url, image);
        response.log().all().body("name", equalTo(name));
    }

    @Test
    public void test005() {
        productSteps.deleteAProduct(id);
        productSteps.getSingleRecord(id).statusCode(404);
    }
}
