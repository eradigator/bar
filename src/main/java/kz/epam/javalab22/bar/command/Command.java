package kz.epam.javalab22.bar.command;

public interface Command {
    ActionCommand getCurrentCommand(String action);
}
