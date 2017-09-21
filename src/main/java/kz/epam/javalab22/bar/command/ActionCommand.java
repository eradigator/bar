package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.servlet.ReqWrapper;

public interface ActionCommand {
    String execute(ReqWrapper reqWrapper);
}
