package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.CocktailNameDao;
import kz.epam.javalab22.bar.dao.ImageDao;
import kz.epam.javalab22.bar.dao.MixDao;
import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import kz.epam.javalab22.bar.util.CalcAlcohol;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author vten
 */
public class CocktailLogic {

    private static final Logger log = Logger.getLogger(CocktailLogic.class);

    private ReqWrapper reqWrapper;
    private Connection connection;
    private MessageManager messageManager;

    public CocktailLogic(ReqWrapper reqWrapper, Connection connection) {
        this.reqWrapper = reqWrapper;
        this.connection = connection;
        messageManager = new MessageManager(reqWrapper.getLocale());
    }

    public boolean addCocktail() {

        boolean success = false;

        String nameRu = reqWrapper.getParam(Const.PARAM_NAME_RU);
        String nameEn = reqWrapper.getParam(Const.PARAM_NAME_EN);

        int methodId = Integer.parseInt(reqWrapper.getParam(Const.PARAM_METHOD));
        Glass glass = new Glass(Integer.parseInt(reqWrapper.getParam(Const.PARAM_GLASS)));
        String[] components = reqWrapper.getParams(Const.PARAM_INGREDIENT);
        String[] amounts = reqWrapper.getParams(Const.PARAM_AMOUNT_OF_INGREDIENT);

        Mix mix = new Mix();
        for (int i = Const.N_0; i < components.length; i++) {
            Component component = new Component(Integer.parseInt(components[i]));
            mix.getMix().put(component, Integer.parseInt(amounts[i]));
        }

        try {
            connection.setAutoCommit(false);

            //Write Image
            ImageLogic imageLogic = new ImageLogic(reqWrapper);
            Image image = imageLogic.getImage();
            boolean isImageWritten = new ImageDao(connection).create(image);

            //Write Cocktail Name
            CocktailName cocktailName = new CocktailName(nameRu, nameEn);
            boolean isCocktailNameWritten = new CocktailNameDao(connection).create(cocktailName);

            //Write Cocktail
            Cocktail cocktail = new Cocktail(cocktailName, methodId, glass, image);
            boolean isCocktailWritten = new CocktailDao(connection).create(cocktail);

            //Write Mix
            boolean isMixWritten = new MixDao(connection).add(mix, cocktail.getId());

            //Write Strength
            double nonRoundedStrength = new CalcAlcohol().calcStrength(mix);
            double strength = new BigDecimal(nonRoundedStrength).setScale(Const.MATH_ROUND_SCALE_3, RoundingMode.UP).doubleValue();
            boolean isStrengthWritten = new CocktailDao(connection).setStrength(cocktail.getId(), strength);

            if (isImageWritten && isCocktailNameWritten && isCocktailWritten && isMixWritten && isStrengthWritten) {
                connection.commit();
                success = true;
            } else {
                connection.rollback();
            }

            connection.setAutoCommit(true);

        } catch (SQLException e) {
            log.error(Const.LOG_EXC_SQL);
        }

        return success;
    }

    public boolean deleteCocktail() {

        boolean success = false;
        int cocktailId = Integer.parseInt(reqWrapper.getParam(Const.PARAM_COCKTAIL_ID_TO_DELETE));

        try {
            connection.setAutoCommit(false);

            if (new CocktailNameDao(connection).delete(cocktailId) &&
                    new CocktailDao(connection).delete(cocktailId)) {
                connection.commit();
                success = true;
            } else {
                connection.rollback();
            }

            connection.setAutoCommit(true);

        } catch (SQLException e) {
            log.error(Const.LOG_EXC_SQL);
        }

        return success;
    }

    public boolean validate() {

        boolean isEverythingOk = false;

        String errorMessage = Const.STR_EMPTY;

        if (checkForExistence()) {
            errorMessage = messageManager.getProperty(Const.PROP_COCKTAIL_EXIST);
        } else if (!checkSelectedComponent()) {
            errorMessage = messageManager.getProperty(Const.PROP_NO_COMPONENT_SELECTED);
        } else if (!checkImageSizeAcceptable()) {
            errorMessage = messageManager.getProperty(Const.PROP_TOO_BIG_IMAGE_SIZE);
        } else {
            isEverythingOk = true;
        }

        if (!errorMessage.isEmpty()) {
            reqWrapper.addAttribute(Const.ATTR_ERROR, errorMessage);
        }

        return isEverythingOk;
    }

    private boolean checkForExistence() {

        boolean isCocktailNameExist = false;

        String nameRu = reqWrapper.getParam(Const.PARAM_NAME_RU);
        String nameEn = reqWrapper.getParam(Const.PARAM_NAME_EN);

        CocktailName cocktailName = new CocktailName(nameRu, nameEn);
        List<CocktailName> cocktailNameList = new CocktailNameDao(connection).getList();

        for (CocktailName entity : cocktailNameList) {
            if (entity.getNameRu().equals(cocktailName.getNameRu()) ||
                    entity.getNameEn().equals(cocktailName.getNameEn())) {
                isCocktailNameExist = true;
            }
        }

        return isCocktailNameExist;
    }

    private boolean checkSelectedComponent() {

        boolean isSelectedComponentExist = true;

        String[] components = reqWrapper.getParams(Const.PARAM_INGREDIENT);
        String[] amounts = reqWrapper.getParams(Const.PARAM_AMOUNT_OF_INGREDIENT);

        if (null == components || null == amounts) {
            isSelectedComponentExist = false;
        }

        return isSelectedComponentExist;
    }

    private boolean checkImageSizeAcceptable() {
        ImageLogic imageLogic = new ImageLogic(reqWrapper);
        Image image = imageLogic.getImage();
        return image.getInputStreamLength() < Const.N_1MB;
    }


}
