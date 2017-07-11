package kz.epam.javalab22.bar.runner;

import kz.epam.javalab22.bar.entity.*;
import kz.epam.javalab22.bar.operation.CalcAlcohol;

/**
 * Created by erad on 10.07.2017.
 */


public class Runner {
    public static void main(String[] args) {

        Cocktail whiteRussian = new Cocktail();
        whiteRussian.setBuildMethod(BuildMethod.BUILD);
        whiteRussian.setGlass(Glass.OLD_FASHIONED);
        System.out.println(whiteRussian);

        AlcoholicComponent vodka = new AlcoholicComponent();
        vodka.setStrength(40);
        whiteRussian.addComponent(vodka,30);

        int strengthWhiteRussian = new CalcAlcohol().calcAlcohol(whiteRussian);
        System.out.println("Крепость коктейля: " + strengthWhiteRussian + "%");

    }
}
