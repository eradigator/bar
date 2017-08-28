package kz.epam.javalab22.bar.tag;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.ComponentDao;
import kz.epam.javalab22.bar.dao.ComponentNameDao;
import kz.epam.javalab22.bar.entity.BuildMethod;
import kz.epam.javalab22.bar.entity.Cocktail;
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

            /*out.write(Const.H5_OPN + "Добавление коктейля:" + Const.H5_CLS);

            out.write("<form name='addCocktail' method='post' action='/bar/jsp/controller'>");
            out.write("<input type='hidden' name='command' value='add_cocktail'>");

            out.write(Const.P_OPN);
            out.write("Название" + Const.BR);
            out.write("<input type='text' name='name' value='' required>" + Const.BR);
            out.write(Const.P_CLS);
*/
  /*          out.write(Const.P_OPN);
            out.write("Компонент" + Const.BR);
            out.write("<select name='component' title='component'>");

            for (String componentName : new ComponentDao().getList()) {
                out.write("<option value='" + componentName + "'>" + componentName + "</option>");
            }
            out.write("</select>");
            out.write("Количество");
            out.write("<input type='text' name='amount' value='' />");
            out.write("<input type='button' value='Добавить компонент'/>");
            out.write(Const.P_CLS);*/


            /*out.write(Const.P_OPN);
            out.write("Метод" + Const.BR);
            out.write("<select name='buildMethod' title='buildMethod'>");
            for (BuildMethod buildMethod : BuildMethod.values()) {
                out.write("<option value='" + buildMethod + "'>" + buildMethod + "</option>");
            }
            out.write("</select>");
            out.write(Const.P_CLS);*/

           /* out.write(Const.P_OPN);
            out.write("Стакан" + Const.BR);
            out.write("<select name='glass' title='glass'>");
            for (Glass glass : Glass.values()) {
                out.write("<option value='" + glass + "'>" + glass + "</option>");
            }
            out.write("</select>");
            out.write(Const.P_CLS);*/

            /*out.write(Const.P_OPN);
            out.write("Изображение" + Const.BR);
            out.write("<input type='file' />" + Const.BR);
            out.write(Const.P_CLS);*/

            /*out.write("<input type='submit' value='Добавить'/>");

            out.write("</form>");*/


            out.write(Const.H5_OPN + "Удаление коктейля:" + Const.H5_CLS);

            out.write("<form name='removeCocktail' method='post' action='/bar/jsp/controller'>");
            out.write("<input type='hidden' name='command' value='remove_cocktail'>");

            out.write(Const.P_OPN);
            out.write("Название коктейля" + Const.BR);
            out.write("<select name='cocktailToDelete' title='cocktailToDelete'>");

            List<Cocktail> cocktailList = new CocktailDao().getCocktailsList();
            for (Cocktail cocktail : cocktailList) {
                out.write("<option value='" + cocktail.getName() + "'>" + cocktail.getName() + "</option>");
            }

            out.write("</select>");
            out.write(Const.P_CLS);

            out.write("<input type='submit' value='Удалить'/>");

            out.write("</form>");

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }

}
