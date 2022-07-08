package examples.aws.apig.simpleCalc.sdk.app.service;

import com.google.gson.Gson;
import examples.aws.apig.simpleCalc.sdk.SimpleCalcSdk;
import examples.aws.apig.simpleCalc.sdk.model.DeleteProductRequest;
import examples.aws.apig.simpleCalc.sdk.model.GetProductRequest;
import examples.aws.apig.simpleCalc.sdk.model.Product;
import examples.aws.apig.simpleCalc.sdk.model.SaveProductRequest;
import examples.aws.apig.simpleCalc.sdk.model.SaveProductResult;

public class ServiceProductImpl implements ServiceProduct {

    private SimpleCalcSdk sdkClient;
    private Gson gson;

    public ServiceProductImpl(SimpleCalcSdk sdkClient, Gson gson) {
        this.sdkClient = sdkClient;
        this.gson = gson;
    }


    @Override
    public String save(String productJson) {
        SaveProductResult result = sdkClient.saveProduct(new SaveProductRequest().product(gson.fromJson(productJson, Product.class)));
        return result.getResponse().getResponse();
    }

    @Override
    public String delete(String id) {
        sdkClient.deleteProduct(new DeleteProductRequest().id(id));
        return "deleted";
    }

    @Override
    public String get(String id) {
        Product product = sdkClient.getProduct(new GetProductRequest().id(id)).getProduct();
        return gson.toJson(product);
    }
}
