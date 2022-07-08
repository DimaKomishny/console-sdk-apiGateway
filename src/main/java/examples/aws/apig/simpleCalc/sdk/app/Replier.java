package examples.aws.apig.simpleCalc.sdk.app;

import com.amazonaws.opensdk.config.ConnectionConfiguration;
import com.amazonaws.opensdk.config.TimeoutConfiguration;
import com.google.gson.Gson;
import examples.aws.apig.simpleCalc.sdk.SimpleCalcSdk;
import examples.aws.apig.simpleCalc.sdk.app.service.CommandProcessor;
import examples.aws.apig.simpleCalc.sdk.app.service.ProxyServiceProduct;
import examples.aws.apig.simpleCalc.sdk.app.service.ServiceProduct;
import examples.aws.apig.simpleCalc.sdk.app.util.Command;
import examples.aws.apig.simpleCalc.sdk.app.util.CommandArgument;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Replier {

    private SimpleCalcSdk sdkClient;
    private CommandProcessor commandProcessor = new CommandProcessor();
    private Map<Command, Function<String, String>> mapExecutor = new HashMap<>();
    private ServiceProduct service;

    public Replier() {
        initSdk();
        service = new ProxyServiceProduct(sdkClient, new Gson());
        initCommand();
    }

    private void initSdk() {
        sdkClient = SimpleCalcSdk.builder()
                .connectionConfiguration(
                        new ConnectionConfiguration()
                                .maxConnections(100)
                                .connectionMaxIdleMillis(1000))
                .timeoutConfiguration(
                        new TimeoutConfiguration()
                                .httpRequestTimeout(3000)
                                .totalExecutionTimeout(10000)
                                .socketTimeout(2000))
                .build();
    }

    private void initCommand() {
        mapExecutor.put(Command.SAVE, service::save);
        mapExecutor.put(Command.DELETE, service::delete);
        mapExecutor.put(Command.GET, service::get);
        mapExecutor.put(Command.EXIT, this::shutdown);
    }

    private String shutdown(String stub) {
        sdkClient.shutdown();
        System.exit(0);
        return null;
    }

    public String reply(String line) {
        CommandArgument commandArgument = commandProcessor.parseCommand(line);
        return mapExecutor.get(commandArgument.getCommand()).apply(commandArgument.getArgument());
    }
}
