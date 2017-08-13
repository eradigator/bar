package kz.epam.javalab22.bar.tag;

import kz.epam.javalab22.bar.entity.*;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class GetInfoTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {

        int size = new Integer(new MySet().getSize());
        String str = "Size = <b>(" + size + ")</b>";

        String COCKTAIL_NAME_WHITE_RUSSIAN = "White Russian / Белый русский";
        Cocktail whiteRussian = new Cocktail(COCKTAIL_NAME_WHITE_RUSSIAN);
        whiteRussian.setBuildMethod(BuildMethod.BUILD);
        whiteRussian.setGlass(Glass.OLD_FASHIONED);

        Component vodka = new AlcoholicComponent();
        vodka.setStrength(40);
        vodka.setName("Vodka");
        whiteRussian.addComponent(vodka, 25);

        Component kalua = new AlcoholicComponent();
        kalua.setStrength(20);
        kalua.setName("Kalua");
        whiteRussian.addComponent(kalua, 25);

        Component cream = new NonAlcoholicComponent();
        whiteRussian.addComponent(cream, 30);


        try{
            JspWriter out = pageContext.getOut();
            out.write(str);
            out.write(whiteRussian.toString());
        }catch(IOException e){
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
