package kz.epam.javalab22.bar.runner;

import kz.epam.javalab22.bar.entity.BuildMethod;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.operation.CalcAlcohol;

/**
 * Created by erad on 10.07.2017.
 */


public class Runner {
    public static void main(String[] args) {

        Cocktail whiteRussian = new Cocktail();
        whiteRussian.setBuildMethod(BuildMethod.BUILD);
        System.out.println(whiteRussian.getBuildMethod());

        CalcAlcohol calc = new CalcAlcohol();
        calc.calcAlcohol();

    }
}
