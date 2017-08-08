package kz.epam.javalab22.command.client;

import kz.epam.javalab22.command.ActionCommand;
import kz.epam.javalab22.command.FullCatalogCommand;
import kz.epam.javalab22.command.LoginCommand;
import kz.epam.javalab22.command.LogoutCommand;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    FULL_CATALOG {
        {
            this.command = new FullCatalogCommand();
        }
    }
    ;
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
