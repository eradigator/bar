package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.MixDao;
import kz.epam.javalab22.bar.entity.BuildMethod;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.Glass;
import kz.epam.javalab22.bar.entity.Mix;
import kz.epam.javalab22.bar.manager.ConfigurationManager;
import kz.epam.javalab22.bar.runner.Runner;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AddCocktailCommand implements ActionCommand {

    private static final Logger log = Logger.getLogger(AddCocktailCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;
        Cocktail cocktail;

        String name = request.getParameter("name");
        BuildMethod buildMethod = BuildMethod.valueOf(request.getParameter("buildMethod"));
        Glass glass = Glass.valueOf(request.getParameter("glass"));

        String[] components = request.getParameterValues("ingredient");
        String[] amounts = request.getParameterValues("amountOfIngredient");

       /* DataInputStream in = null;
        try {
            in = new DataInputStream(request.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //we are taking the length of Content type data
        int formDataLength = request.getContentLength();
        byte dataBytes[] = new byte[formDataLength];
        int byteRead = 0;
        int totalBytesRead = 0;

        //this loop converting the uploaded file into byte code
        while (totalBytesRead < formDataLength) {
            try {
                byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
            } catch (IOException e) {
                e.printStackTrace();
            }
            totalBytesRead += byteRead;
        }

        String file = new String(dataBytes);
        //for saving the file name
        Runner.getImg(dataBytes);*/


        Map<Integer, Double> mix = new LinkedHashMap<>();
        for (int i = 0; i < components.length; i++) {
            mix.put(Integer.parseInt(components[i]), Double.parseDouble(amounts[i]));
        }

        cocktail = new Cocktail(name, buildMethod, glass);
        new CocktailDao().create(cocktail);

        int cocktailId = new CocktailDao().getId(name);

        Mix cocktailMix = new Mix(mix);
        new MixDao().add(cocktailMix, cocktailId);

        request.setAttribute("addCocktailResult", "Коктейль добавлен");
        log.info("Коктейль: " + name + " добавлен");

        /*} else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
        }*/

        page = ConfigurationManager.getProperty(Const.PAGE_COCKTAIL_MANAGER);
        return page;
    }
}
