package kz.epam.javalab22.bar.runner;

import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.operation.CalcAlcohol;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ResourceBundle;

/**
 * Created by erad on 10.07.2017.
 */


public class Runner {

    private static final String COCKTAIL_NAME_WHITE_RUSSIAN = "Белый Русский";


    public static void main(String[] args) {

        /*final String FILENAME = "config";
        final ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME);

        String page = resourceBundle.getString("path.page.main");

        System.out.println(page);

        Cocktail whiteRussian = new Cocktail(COCKTAIL_NAME_WHITE_RUSSIAN);
        whiteRussian.setBuildMethod(BuildMethod.BUILD);
        whiteRussian.setGlass(Glass.OLD_FASHIONED);

        Component vodka = new AlcoholicComponent();
        vodka.setStrength(40);
        whiteRussian.addComponent(vodka,25);

        Component kalua = new AlcoholicComponent();
        kalua.setStrength(20);
        whiteRussian.addComponent(kalua,25);

        Component cream = new NonAlcoholicComponent();
        whiteRussian.addComponent(cream,30);

        System.out.println(whiteRussian);

        int strengthWhiteRussian = new CalcAlcohol().calcAlcohol(whiteRussian);
        System.out.println("Крепость коктейля: " + strengthWhiteRussian + "%");*/

        String s = DigestUtils.md5Hex("asdabskdbaskdbaskbhdsbad");
        System.out.println(s);


    }
}
