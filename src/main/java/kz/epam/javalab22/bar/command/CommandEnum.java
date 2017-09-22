package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.command.impl.*;
import kz.epam.javalab22.bar.command.impl.CalcStrengthCommand;
import kz.epam.javalab22.bar.command.impl.cocktail.AddCocktailCommand;
import kz.epam.javalab22.bar.command.impl.cocktail.PageCocktailsCommand;
import kz.epam.javalab22.bar.command.impl.cocktail.RemoveCocktailCommand;
import kz.epam.javalab22.bar.command.impl.cocktail.ShowCocktailCommand;
import kz.epam.javalab22.bar.command.impl.component.AddComponentCommand;
import kz.epam.javalab22.bar.command.impl.component.DelComponentCommand;
import kz.epam.javalab22.bar.command.impl.favorite.AddToFavoritesCommand;
import kz.epam.javalab22.bar.command.impl.favorite.DelFromFavoritesCommand;
import kz.epam.javalab22.bar.command.impl.page.PageFavoriteCommand;
import kz.epam.javalab22.bar.command.impl.page.*;
import kz.epam.javalab22.bar.command.impl.user.AddUserCommand;
import kz.epam.javalab22.bar.command.impl.user.DelUserCommand;
import kz.epam.javalab22.bar.command.impl.SignUpCommand;

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
    SIGN_UP {
        {
            this.command = new SignUpCommand();
        }
    },
    PAGE_LOGIN {
        {
            this.command = new PageLoginCommand();
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
    PAGE_MAIN {
        {
            this.command = new PageMainCommand();
        }
    },
    PAGE_COCKTAILS {
        {
            this.command = new PageCocktailsCommand();
        }
    },
    PAGE_CALC {
        {
            this.command = new PageCalcCommand();
        }
    },
    PAGE_FAVORITE {
        {
            this.command = new PageFavoriteCommand();
        }
    },
    PAGE_USER_MANAGER {
        {
            this.command = new PageUserManagerCommand();
        }
    },
    PAGE_COMPONENT {
        {
            this.command = new PageComponentManagerCommand();
        }
    },
    PAGE_COCKTAIL {
        {
            this.command = new PageCocktailManagerCommand();
        }
    },
    SHOW_COCKTAIL {
        {
            this.command = new ShowCocktailCommand();
        }
    },
    ADD_TO_FAVORITES {
        {
            this.command = new AddToFavoritesCommand();
        }
    },
    DEL_FROM_FAVORITES {
        {
            this.command = new DelFromFavoritesCommand();
        }
    }
    ;

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
