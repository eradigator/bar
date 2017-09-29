package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.Favorite;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author vten
 */

public class FavoriteDao extends AbstractDao<Favorite> {

    private static final Logger log = Logger.getLogger(CocktailNameDao.class);

    private Connection connection;

    private static final String SQL_GET_LIST = "SELECT cocktail_id FROM favorite f " +
            "INNER JOIN cocktail c ON c.id = f.cocktail_id " +
            "WHERE user_id=? AND c.deleted IS NOT TRUE";

    private static final String SQL_CREATE = "INSERT INTO favorite (user_id,cocktail_id) VALUES (?,?)";
    private static final String SQL_DELETE = "DELETE FROM favorite WHERE user_id=? AND cocktail_id=?";



    public FavoriteDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Favorite update(Favorite entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Favorite entity) {
        boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, entity.getUserId());
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_2, entity.getCocktailIds().get(Const.INDEX_0));

            if (preparedStatement.executeUpdate() > Const.N_0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.info(Const.LOG_EXC_SQL);
        }

        return success;
    }

    @Override
    public boolean create(Favorite entity) {

        boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, entity.getUserId());
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_2, entity.getCocktailIds().get(Const.INDEX_0));

            if (preparedStatement.executeUpdate() > Const.N_0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.info(Const.LOG_EXC_SQL);
        }

        return success;
    }

    public Favorite getList(int userId) {

        Favorite favorite = new Favorite();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_LIST)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int cocktailId = resultSet.getInt(Const.COLUMN_LABEL_COCKTAIL_ID);
                favorite.getCocktailIds().add(cocktailId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.info(Const.LOG_EXC_SQL);
        }

        return favorite;
    }
}
