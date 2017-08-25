package kz.epam.javalab22.bar.runner;

import kz.epam.javalab22.bar.dao.*;
import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.operation.CalcAlcohol;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by erad on 10.07.2017.
 */


public class Runner {

    private static final String COCKTAIL_NAME_WHITE_RUSSIAN = "Белый Русский";


    public static void main(String[] args) {


        /*Cocktail whiteRussian = new Cocktail(COCKTAIL_NAME_WHITE_RUSSIAN);
        whiteRussian.setBuildMethod(BuildMethod.BUILD);
        whiteRussian.setGlass(Glass.OLD_FASHIONED);

        Component vodka = new AlcoholicComponent();
        vodka.setStrength(40);
        whiteRussian.addComponent(vodka, 25);

        Component kalua = new AlcoholicComponent();
        kalua.setStrength(20);
        whiteRussian.addComponent(kalua, 25);

        Component cream = new NonAlcoholicComponent();
        whiteRussian.addComponent(cream, 30);

        System.out.println(whiteRussian);

        int strengthWhiteRussian = new CalcAlcohol().calcAlcohol(whiteRussian);
        System.out.println("Крепость коктейля: " + strengthWhiteRussian + "%");*/

        //System.out.println(new CocktailDao().update("Черный русский"));

        /*Map<String,Integer> map = new MixDao().getMix(8);

        for (Map.Entry<String,Integer> pair: map.entrySet()) {
            System.out.println(pair.getKey() + ": "+ pair.getValue());
        }*/

        /*List<String> list = new ComponentTypeDao().getComponentTypeList();
        for (String s : list) {
            System.out.println(s);
        }*/

        for (String s : new ComponentNameDao().getComponentNameList()) {
            System.out.println(s);
        }

    }
}
