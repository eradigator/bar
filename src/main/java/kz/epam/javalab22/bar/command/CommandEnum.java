package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.command.impl.*;
import kz.epam.javalab22.bar.command.impl.page.PageCalcCommand;
import kz.epam.javalab22.bar.command.impl.page.PageCocktailCommand;
import kz.epam.javalab22.bar.command.impl.page.PageComponentCommand;

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
            this.command = new DelUserCommand();
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
    DEL_COMPONENT {
        {
            this.command = new DelComponentCommand();
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
    },
    PAGE_CALC {
        {
            this.command = new PageCalcCommand();
        }
    },
    PAGE_COMPONENT {
        {
            this.command = new PageComponentCommand();
        }
    },
    PAGE_COCKTAIL {
        {
            this.command = new PageCocktailCommand();
        }
    }
    ;

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
