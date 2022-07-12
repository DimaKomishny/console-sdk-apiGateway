package examples.aws.apig.simpleCalc.sdk.app.service;

import com.amazonaws.SdkClientException;
import com.google.gson.Gson;
import examples.aws.apig.simpleCalc.sdk.SimpleCalcSdk;
import examples.aws.apig.simpleCalc.sdk.model.SimpleCalcSdkException;

public class ProxyServiceProduct implements ServiceProduct {

    ServiceProduct serviceProduct;

    public ProxyServiceProduct(SimpleCalcSdk sdkClient, Gson gson) {
        serviceProduct = new ServiceProductImpl(sdkClient, gson);
    }

    @Override
    public String save(String productJson) {
        try {
            return serviceProduct.save(productJson);
        } catch (SdkClientException e) {
            return "invalid JSON";
        }
    }

    @Override
    public String delete(String id) {
        try {
            return serviceProduct.delete(id);
        } catch (SimpleCalcSdkException e) {
            return "invalid id";
        }
    }

    @Override
    public String get(String id) {
        try {
            return serviceProduct.get(id);
        } catch (SdkClientException e) {
            return "invalid id";
        }
    }
}
