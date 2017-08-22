package kz.epam.javalab22.bar.tag;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.BuildMethod;
import kz.epam.javalab22.bar.entity.Glass;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;


public class CocktailManager extends TagSupport {

    @Override
    public int doStartTag() throws JspException {

        try {
            JspWriter out = pageContext.getOut();

            out.write(Const.H5_OPN + "Добавление коктейля:" + Const.H5_CLS);

            out.write("<form name='addCocktail' method='post' action='/bar/jsp/controller'>");
            out.write("<input type='hidden' name='command' value='add_cocktail'>");

            out.write(Const.P_OPN);
            out.write("Название" + Const.BR);
            out.write("<input type='text' name='name' value='' required>" + Const.BR);
            out.write(Const.P_CLS);

            out.write(Const.P_OPN);
            out.write("Компоненты" + Const.BR);
            out.write("<input type='text' name='component' value='' required>" + Const.BR);
            out.write(Const.P_CLS);

            out.write(Const.P_OPN);
            out.write("Метод" + Const.BR);
            out.write("<select name='buildMethod' title='buildMethod'>");
            for (BuildMethod buildMethod : BuildMethod.values()) {
                out.write("<option value='" + buildMethod + "'>" + buildMethod + "</option>");
            }
            out.write("</select>");
            out.write(Const.P_CLS);

            out.write(Const.P_OPN);
            out.write("Стакан" + Const.BR);
            out.write("<select name='glass' title='glass'>");
            for (Glass glass : Glass.values()) {
                out.write("<option value='" + glass + "'>" + glass + "</option>");
            }
            out.write("</select>");
            out.write(Const.P_CLS);

            out.write("<input type='submit' value='Добавить'/>");

            out.write("</form>");

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }

}
