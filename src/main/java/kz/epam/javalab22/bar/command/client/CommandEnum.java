package kz.epam.javalab22.bar.command.client;

import kz.epam.javalab22.bar.command.*;

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
    PAGE {
        {
            this.command = new PageCommand();
        }
    },
    FULL_CATALOG {
        {
            this.command = new FullCatalogCommand();
        }
    },
    LANG {
        {
            this.command = new LangCommand();
        }
    },
    DELETE_USER{
        {
            this.command = new DeleteUserCommand();
        }
    },
    ;

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
