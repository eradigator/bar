package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.command.impl.*;

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
    REMOVE_COCKTAIL {
        {
            this.command = new RemoveCocktailCommand();
        }
    },
    ADD_COMPONENT {
        {
            this.command = new AddComponentCommand();
        }
    },
    CALCULATOR {
        {
            this.command = new CalcStrengthCommand();
        }
    },
    IMAGE {
        {
            this.command = new ImageCommand();
        }
    }
    ;

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}