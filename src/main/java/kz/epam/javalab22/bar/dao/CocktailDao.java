package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CocktailDao extends AbstractDao<Cocktail> {

    private static final Logger log = Logger.getLogger(CocktailDao.class);
    private Connection connection;

    private static final String SQL_DELETE = "UPDATE cocktail SET deleted = TRUE WHERE id = ?";
    private static final String SQL_CREATE = "INSERT INTO cocktail(name_id,method,glass_id,image_id) " +
            "VALUES(?,?,?,?)";
    private static final String SQL_GET = "SELECT c.id,c.image_id," +
            "cn.id AS cocktailNameId, " +
            "cn.name_ru AS cocktailNameNameRu, " +
            "cn.name_en AS cocktailNameNameEn, " +
            "b.id AS methodId, " +
            "b.name_ru AS methodNameRu, " +
            "b.name_en AS methodNameEn, " +
            "g.id AS glassId, " +
            "g.name_ru AS glassNameRu, " +
            "g.name_en AS glassNameEn, " +
            "c.strength AS strength " +
            "FROM cocktail c " +
            "INNER JOIN cocktail_name cn ON c.name_id = cn.id " +
            "INNER JOIN build_method b ON c.method = b.id " +
            "INNER JOIN glass g ON c.glass_id = g.id " +
            "AND c.deleted IS NOT TRUE " +
            "AND c.id=?";

    private static final String SQL_GET_COCKTAIL_LIST = "SELECT c.id,c.image_id," +
            "cn.id AS cocktailNameId, " +
            "cn.name_ru AS cocktailNameNameRu, " +
            "cn.name_en AS cocktailNameNameEn, " +
            "b.id AS methodId, " +
            "b.name_ru AS methodNameRu, " +
            "b.name_en AS methodNameEn, " +
            "g.id AS glassId, " +
            "g.name_ru AS glassNameRu, " +
            "g.name_en AS glassNameEn, " +
            "c.strength AS strength " +
            "FROM cocktail c " +
            "INNER JOIN cocktail_name cn ON c.name_id = cn.id " +
            "INNER JOIN build_method b ON c.method = b.id " +
            "INNER JOIN glass g ON c.glass_id = g.id " +
            "AND c.deleted IS NOT TRUE";

    private static final String SQL_SET_STRENGTH = "UPDATE cocktail SET strength=? WHERE id=?";

    public CocktailDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Cocktail update(Cocktail entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Cocktail entity) {
        throw new UnsupportedOperationException();
    }

    public boolean delete(int id) {
        Boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, id);
            if (preparedStatement.executeUpdate() > Const.N_0) {
                success = true;
            }
        } catch (SQLException e) {
            log.info(Const.LOG_EXC_SQL);
        }

        return success;
    }

    @Override
    public boolean create(Cocktail entity) {

        Boolean success = false;

        try (PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(Const.SQL_PARAM_INDEX_1, entity.getCocktailName().getId());
            ps.setInt(Const.SQL_PARAM_INDEX_2, entity.getMethodId());
            ps.setInt(Const.SQL_PARAM_INDEX_3, entity.getGlass().getId());
            ps.setInt(Const.SQL_PARAM_INDEX_4, entity.getImage().getId());

            if (ps.executeUpdate() > Const.N_0) {
                ResultSet resultSet = ps.getGeneratedKeys();
                while (resultSet.next()) {
                    entity.setId(resultSet.getInt(Const.COLUMN_LABEL_ID));
                }
                success = true;
            }

        } catch (SQLException e) {
            log.info(Const.LOG_EXC_SQL);
        }

        return success;
    }

    public Cocktail get(int cocktailId) {

        Cocktail cocktail = new Cocktail();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, cocktailId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                CocktailName cocktailName;
                {
                    int cocktailNameId = resultSet.getInt(Const.COLUMN_LABEL_COCKTAIL_NAME_ID);
                    String cocktailNameNameRu = resultSet.getString(Const.COLUMN_LABEL_COCKTAIL_NAME_NAME_RU);
                    String cocktailNameNameEn = resultSet.getString(Const.COLUMN_LABEL_COCKTAIL_NAME_NAME_EN);
                    cocktailName = new CocktailName(cocktailNameId, cocktailNameNameRu, cocktailNameNameEn);
                }

                Method method;
                {
                    int methodId = resultSet.getInt(Const.COLUMN_LABEL_METHOD_ID);
                    String methodNameRU = resultSet.getString(Const.COLUMN_LABEL_METHOD_NAME_RU);
                    String methodNameEn = resultSet.getString(Const.COLUMN_LABEL_METHOD_NAME_EN);
                    method = new Method(methodId, methodNameRU, methodNameEn);
                }

                Glass glass;
                {
                    int glassId = resultSet.getInt(Const.COLUMN_LABEL_GLASS_ID);
                    String glassNameRu = resultSet.getString(Const.COLUMN_LABEL_GLASS_NAME_RU);
                    String glassNameEn = resultSet.getString(Const.COLUMN_LABEL_GLASS_NAME_EN);
                    glass = new Glass(glassId, glassNameRu, glassNameEn);
                }

                double strength = resultSet.getDouble(Const.COLUMN_LABEL_STRENGTH);
                int imageId = resultSet.getInt(Const.COLUMN_LABEL_IMAGE_ID);
                Mix mix = new MixDao(connection).getMix(cocktailId);

                cocktail = new Cocktail(cocktailId, cocktailName, mix, method, glass, strength, imageId);
            }
        } catch (SQLException e) {
            log.info(Const.LOG_EXC_SQL);
        }

        return cocktail;
    }

    public List<Cocktail> getCocktailsList() {

        List<Cocktail> cocktailList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COCKTAIL_LIST)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt(Const.COLUMN_LABEL_ID);

                CocktailName cocktailName;
                {
                    int cocktailNameId = resultSet.getInt(Const.COLUMN_LABEL_COCKTAIL_NAME_ID);
                    String cocktailNameNameRu = resultSet.getString(Const.COLUMN_LABEL_COCKTAIL_NAME_NAME_RU);
                    String cocktailNameNameEn = resultSet.getString(Const.COLUMN_LABEL_COCKTAIL_NAME_NAME_EN);
                    cocktailName = new CocktailName(cocktailNameId, cocktailNameNameRu, cocktailNameNameEn);
                }

                Method method;
                {
                    int methodId = resultSet.getInt(Const.COLUMN_LABEL_METHOD_ID);
                    String methodNameRU = resultSet.getString(Const.COLUMN_LABEL_METHOD_NAME_RU);
                    String methodNameEn = resultSet.getString(Const.COLUMN_LABEL_METHOD_NAME_EN);
                    method = new Method(methodId, methodNameRU, methodNameEn);
                }

                Glass glass;
                {
                    int glassId = resultSet.getInt(Const.COLUMN_LABEL_GLASS_ID);
                    String glassNameRu = resultSet.getString(Const.COLUMN_LABEL_GLASS_NAME_RU);
                    String glassNameEn = resultSet.getString(Const.COLUMN_LABEL_GLASS_NAME_EN);
                    glass = new Glass(glassId, glassNameRu, glassNameEn);
                }

                double strength = resultSet.getDouble(Const.COLUMN_LABEL_STRENGTH);
                int imageId = resultSet.getInt(Const.COLUMN_LABEL_IMAGE_ID);
                Mix mix = new MixDao(connection).getMix(id);

                cocktailList.add(new Cocktail(id, cocktailName, mix, method, glass, strength, imageId));
            }
        } catch (SQLException e) {
            log.info(Const.LOG_EXC_SQL);
        }

        return cocktailList;
    }

    public boolean setStrength(int cocktailId, Double strength) {

        Boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_STRENGTH)) {
            preparedStatement.setDouble(Const.SQL_PARAM_INDEX_1, strength);
            preparedStatement.setDouble(Const.SQL_PARAM_INDEX_2, cocktailId);

            if (preparedStatement.executeUpdate() > Const.N_0) {
                success = true;
            }
        } catch (SQLException e) {
            log.info(Const.LOG_EXC_SQL);
        }

        return success;
    }

}
