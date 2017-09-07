package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.MixDao;
import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.pool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import kz.epam.javalab22.bar.util.CalcAlcohol;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vten on 31.08.2017.
 */
public class CocktailLogic {

    private ReqWrapper reqWrapper;

    public CocktailLogic(ReqWrapper reqWrapper) {
        this.reqWrapper = reqWrapper;
    }

    public boolean addCocktail() {

        Connection connection = ConnectionPool.getInstance().getConnection();

        String name = reqWrapper.getParam("name");
        int methodId = Integer.parseInt(reqWrapper.getParam("method"));
        Glass glass = new Glass(Integer.parseInt(reqWrapper.getParam("glass")));
        String[] components = reqWrapper.getRequest().getParameterValues("ingredient");
        String[] amounts = reqWrapper.getRequest().getParameterValues("amountOfIngredient");

        ImageLogic imageLogic = new ImageLogic(reqWrapper);
        Image image = imageLogic.addImage();

        Mix mix = new Mix();
        for (int i = 0; i < components.length; i++) {
            mix.getMix().put(new Component(Integer.parseInt(components[i])),Integer.parseInt(amounts[i]));
        }

        Cocktail cocktail = new Cocktail(name, methodId, glass, image);
        new CocktailDao().create(cocktail);

        int cocktailId = new CocktailDao(connection).getId(name);

        new MixDao(connection).add(mix, cocktailId);

        //вычисляем и записываем крепость
        double tempStrength = new CalcAlcohol().calcStrength(mix);
        double strength = new BigDecimal(tempStrength).setScale(3, RoundingMode.UP).doubleValue();
        new CocktailDao(connection).setStrength(cocktailId,strength);

        ConnectionPool.getInstance().returnConnection(connection);

        return true;
    }

    public boolean deleteCocktail() {

        int cocktailId = Integer.parseInt(reqWrapper.getParam("cocktailIdToDelete"));
        Connection connection = ConnectionPool.getInstance().getConnection();

        //тут еще надо удалить cocktailName

        Boolean result = new CocktailDao(connection).deleteById(cocktailId);
        ConnectionPool.getInstance().returnConnection(connection);

        return result;
    }


}
