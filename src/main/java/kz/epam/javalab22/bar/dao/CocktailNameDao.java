package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.CocktailName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CocktailNameDao extends AbstractDao<CocktailName> {

    private Connection connection;
    private static final String SQL_DELETE_BY_ID = "UPDATE cocktail_name AS cn " +
            "SET deleted = TRUE " +
            "FROM cocktail AS c " +
            "WHERE cn.id = c.name_id AND c.id = ?";

    private static final String SQL_CREATE = "INSERT INTO cocktail_name (name_en,name_ru) VALUES (?,?)";

    private static final String SQL_GET_LIST = "SELECT c.id,cn.name_ru AS nameRu, cn.name_en AS nameEn " +
            "FROM cocktail c " +
            "INNER JOIN cocktail_name cn ON c.name_id = cn.id " +
            "WHERE c.deleted IS NOT TRUE ";


    public CocktailNameDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CocktailName update(CocktailName entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(CocktailName entity) {
        return false;
    }

    public boolean delete(int cocktailId) {

        boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            preparedStatement.setInt(Const.SQL_PARAM_INDEX_1, cocktailId);
            if (preparedStatement.executeUpdate() > Const.N_0) {
                success = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public boolean create(CocktailName entity) {

        Boolean success = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getNameEn());
            preparedStatement.setString(2, entity.getNameRu());

            if (preparedStatement.executeUpdate() > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()) {
                    entity.setId(resultSet.getInt("id"));
                }
                success = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public List<CocktailName> getList() {

        List<CocktailName> cocktailNames = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_LIST);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameRu = resultSet.getString("nameRu");
                String nameEn = resultSet.getString("nameEn");
                cocktailNames.add(new CocktailName(id, nameRu, nameEn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cocktailNames;
    }

}
