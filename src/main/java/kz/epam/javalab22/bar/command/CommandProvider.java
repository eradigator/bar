package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.command.impl.*;
import kz.epam.javalab22.bar.command.impl.cocktail.AddCocktailCommand;
import kz.epam.javalab22.bar.command.impl.cocktail.PageCocktailsCommand;
import kz.epam.javalab22.bar.command.impl.cocktail.RemoveCocktailCommand;
import kz.epam.javalab22.bar.command.impl.cocktail.ShowCocktailCommand;
import kz.epam.javalab22.bar.command.impl.component.AddComponentCommand;
import kz.epam.javalab22.bar.command.impl.component.DelComponentCommand;
import kz.epam.javalab22.bar.command.impl.favorite.AddToFavoritesCommand;
import kz.epam.javalab22.bar.command.impl.favorite.DelFromFavoritesCommand;
import kz.epam.javalab22.bar.command.impl.page.*;
import kz.epam.javalab22.bar.command.impl.user.AddUserCommand;
import kz.epam.javalab22.bar.command.impl.user.DelUserCommand;

import static kz.epam.javalab22.bar.constant.CommandName.*;

public class CommandProvider implements Command {
    private ActionCommand command = new EmptyCommand();

    @Override
    public ActionCommand getCurrentCommand(String action) {

        switch (action) {
            case LOGIN:
                command = new LoginCommand();
                break;
            case LOGOUT:
                command = new LogoutCommand();
                break;
            case SIGN_UP:
                command = new SignUpCommand();
                break;
            case PAGE_LOGIN:
                command = new PageLoginCommand();
                break;
            case LANG:
                command = new LangCommand();
                break;
            case ADD_USER:
                command = new AddUserCommand();
                break;
            case DELETE_USER:
                command = new DelUserCommand();
                break;
            case ADD_COCKTAIL:
                command = new AddCocktailCommand();
                break;
            case REMOVE_COCKTAIL:
                command = new RemoveCocktailCommand();
                break;
            case ADD_COMPONENT:
                command = new AddComponentCommand();
                break;
            case DEL_COMPONENT:
                command = new DelComponentCommand();
                break;
            case CALCULATOR:
                command = new CalcStrengthCommand();
                break;
            case PAGE_MAIN:
                command = new PageMainCommand();
                break;
            case PAGE_COCKTAILS:
                command = new PageCocktailsCommand();
                break;
            case PAGE_CALC:
                command = new PageCalcCommand();
                break;
            case PAGE_FAVORITE:
                command = new PageFavoriteCommand();
                break;
            case PAGE_USER_MANAGER:
                command = new PageUserManagerCommand();
                break;
            case PAGE_COMPONENT:
                command = new PageComponentManagerCommand();
                break;
            case PAGE_COCKTAIL:
                command = new PageCocktailManagerCommand();
                break;
            case SHOW_COCKTAIL:
                command = new ShowCocktailCommand();
                break;
            case ADD_TO_FAVORITES:
                command = new AddToFavoritesCommand();
                break;
            case DEL_FROM_FAVORITES:
                command = new DelFromFavoritesCommand();
                break;
        }
        return command;
    }
}
