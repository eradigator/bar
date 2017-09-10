package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.servlet.ReqWrapper;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute(ReqWrapper reqWrapper);
}
