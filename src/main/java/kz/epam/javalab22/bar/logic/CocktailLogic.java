package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.MixDao;
import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqHandler;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vten on 31.08.2017.
 */
public class CocktailLogic {

    private ReqHandler reqHandler;

    public CocktailLogic(ReqHandler reqHandler) {
        this.reqHandler = reqHandler;
    }

    public boolean addCocktail() {

        Connection connection = ConnectionPool.getInstance().getConnection();

        String name = reqHandler.getParam("name");
        BuildMethod buildMethod = BuildMethod.valueOf(reqHandler.getParam("buildMethod"));
        Glass glass = Glass.valueOf(reqHandler.getParam("glass"));
        String[] components = reqHandler.getRequest().getParameterValues("ingredient");
        String[] amounts = reqHandler.getRequest().getParameterValues("amountOfIngredient");

        ImageLogic imageLogic = new ImageLogic(reqHandler);
        Image image = imageLogic.addImage();

        Map<Integer, Double> mix = new LinkedHashMap<>();
        for (int i = 0; i < components.length; i++) {
            mix.put(Integer.parseInt(components[i]), Double.parseDouble(amounts[i]));
        }

        Cocktail cocktail = new Cocktail(name, buildMethod, glass, image);
        new CocktailDao().create(cocktail);

        int cocktailId = new CocktailDao(connection).getId(name);

        Mix cocktailMix = new Mix(mix);
        new MixDao(connection).add(cocktailMix, cocktailId);

        ConnectionPool.getInstance().returnConnection(connection);

        return true;
    }

    public boolean deleteCocktail() {

        String name = reqHandler.getParam("cocktailToDelete");
        Connection connection = ConnectionPool.getInstance().getConnection();
        Boolean result = new CocktailDao(connection).deleteByName(name);
        ConnectionPool.getInstance().returnConnection(connection);

        return result;
    }


}
