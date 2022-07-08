package examples.aws.apig.simpleCalc.sdk.app.util;

public class CommandArgument {

    private Command command;
    private String argument;

    public CommandArgument(Command command, String argument) {
        this.command = command;
        this.argument = argument;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
}
