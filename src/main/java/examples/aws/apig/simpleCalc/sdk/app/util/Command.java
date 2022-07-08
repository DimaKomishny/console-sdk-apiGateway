package examples.aws.apig.simpleCalc.sdk.app.util;

public enum Command {
    SAVE ("save"),
    DELETE ("delete"),
    GET ("get"),
    EXIT ("exit");

    public final String commandName;

    Command(String command) {
        this.commandName = command;
    }
}
