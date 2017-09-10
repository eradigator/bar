package kz.epam.javalab22.bar.dao;

import kz.epam.javalab22.bar.entity.CocktailName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vten on 24.08.2017.
 */

public class CocktailNameDao extends AbstractDao<CocktailName> {

    private Connection connection;

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

    @Override
    public boolean create(CocktailName entity) {

        final String QUERY = "INSERT INTO cocktail_name (en,ru) VALUES (?,?)";

        Boolean success = false;

        try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
            ps.setString(1, entity.getNameEn());
            ps.setString(2, entity.getNameRu());
            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

     public List<CocktailName> getList() {

        List<CocktailName> cocktailNames = new ArrayList<>();

        try {
            final String QUERY = "SELECT c.id,cn.name_ru AS nameRu, cn.name_en AS nameEn " +
                    "FROM cocktail c " +
                    "INNER JOIN cocktail_name cn ON c.name_id = cn.id " +
                    "WHERE c.deleted IS NOT TRUE ";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameRu = resultSet.getString("nameRu");
                String nameEn = resultSet.getString("nameEn");
                cocktailNames.add(new CocktailName(id,nameRu,nameEn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cocktailNames;
    }

}
