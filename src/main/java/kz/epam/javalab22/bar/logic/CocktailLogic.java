package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.CocktailNameDao;
import kz.epam.javalab22.bar.dao.MixDao;
import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.connectionpool.ConnectionPool;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import kz.epam.javalab22.bar.util.CalcAlcohol;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.SQLException;

/**
 * Created by vten on 31.08.2017.
 */
public class CocktailLogic {

    private ReqWrapper reqWrapper;

    public CocktailLogic(ReqWrapper reqWrapper) {
        this.reqWrapper = reqWrapper;
    }

    public boolean addCocktail() {

        String nameRu = reqWrapper.getParam("name_ru");
        String nameEn = reqWrapper.getParam("name_en");

        int methodId = Integer.parseInt(reqWrapper.getParam("method"));
        Glass glass = new Glass(Integer.parseInt(reqWrapper.getParam("glass")));
        String[] components = reqWrapper.getRequest().getParameterValues("ingredient");
        String[] amounts = reqWrapper.getRequest().getParameterValues("amountOfIngredient");

        ImageLogic imageLogic = new ImageLogic(reqWrapper);
        Image image = imageLogic.addImage();

        Mix mix = new Mix();
        for (int i = 0; i < components.length; i++) {
            mix.getMix().put(new Component(Integer.parseInt(components[i])), Integer.parseInt(amounts[i]));
        }

        Connection connection = ConnectionPool.getInstance().getConnection();

        try {

            connection.setAutoCommit(false);

            //Cocktail Name
            CocktailName cocktailName = new CocktailName(nameRu, nameEn);
            boolean isCocktailNameWritten = new CocktailNameDao(connection).create(cocktailName);

            //Cocktail
            Cocktail cocktail = new Cocktail(cocktailName, methodId, glass, image);
            boolean isCocktailWritten = new CocktailDao(connection).create(cocktail);

            //Mix
            boolean isMixWritten = new MixDao(connection).add(mix, cocktail.getId());

            //вычисляем и записываем крепость
            double tempStrength = new CalcAlcohol().calcStrength(mix);
            double strength = new BigDecimal(tempStrength).setScale(3, RoundingMode.UP).doubleValue();
            boolean isStrengthWritten = new CocktailDao(connection).setStrength(cocktail.getId(), strength);

            if (isCocktailNameWritten && isCocktailWritten && isMixWritten && isStrengthWritten) {
                connection.commit();
            } else {
                connection.rollback();
            }

            connection.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnectionPool.getInstance().returnConnection(connection);

        return true;
    }


    public boolean deleteCocktail() {

        boolean result = false;
        int cocktailId = Integer.parseInt(reqWrapper.getParam(Const.PARAM_COCKTAIL_ID_TO_DELETE));

        Connection connection = ConnectionPool.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            if (new CocktailNameDao(connection).delete(cocktailId) &&
                    new CocktailDao(connection).delete(cocktailId)) {
                result = true;
                connection.commit();
            } else {
                connection.rollback();
            }

            connection.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnectionPool.getInstance().returnConnection(connection);

        return result;
    }


}
