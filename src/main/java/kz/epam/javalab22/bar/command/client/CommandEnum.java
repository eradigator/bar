package kz.epam.javalab22.bar.command.client;

import kz.epam.javalab22.bar.command.ActionCommand;
import kz.epam.javalab22.bar.command.FullCatalogCommand;
import kz.epam.javalab22.bar.command.LoginCommand;
import kz.epam.javalab22.bar.command.LogoutCommand;

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
