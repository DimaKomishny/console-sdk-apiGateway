package examples.aws.apig.simpleCalc.sdk.app.service;

public interface ServiceProduct {
    String save(String productJson);

    String delete(String id);

    String get(String id);
}
