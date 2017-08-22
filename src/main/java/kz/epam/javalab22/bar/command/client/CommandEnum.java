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
    LANG {
        {
            this.command = new LangCommand();
        }
    },
    ADD_USER {
        {
            this.command = new AddUserCommand();
        }
    },
    DELETE_USER{
        {
            this.command = new DeleteUserCommand();
        }
    },
    ADD_COCKTAIL {
        {
            this.command = new AddCocktailCommand();
        }
    },
    ;

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
