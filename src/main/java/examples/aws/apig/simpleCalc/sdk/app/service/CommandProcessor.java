package examples.aws.apig.simpleCalc.sdk.app.service;

import examples.aws.apig.simpleCalc.sdk.app.util.CommandArgument;
import examples.aws.apig.simpleCalc.sdk.app.util.Command;

import java.util.Locale;

public class CommandProcessor {

    public CommandArgument parseCommand(String line) {
        Command command = findCommand(line);
        String argument = line.replace(command.commandName, "");
        if (isArgumentId(command)) {
            return new CommandArgument(command, argument.trim());
        }
        return new CommandArgument(command, argument);
    }

    private Command findCommand(String line) {
        String[] arguments = line.split( " ");
        if (arguments.length == 0) {
            throw new IllegalArgumentException();
        }
        return Command.valueOf(arguments[0].toUpperCase(Locale.ROOT));
    }

    private boolean isArgumentId(Command command) {
        return command.equals(Command.GET) || command.equals(Command.DELETE);
    }
}
