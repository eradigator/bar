package kz.epam.javalab22.bar.tag;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.dao.ComponentTypeDao;
import kz.epam.javalab22.bar.entity.BuildMethod;
import kz.epam.javalab22.bar.entity.Cocktail;
import kz.epam.javalab22.bar.entity.Glass;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class ComponentManager extends TagSupport {

    @Override
    public int doStartTag() throws JspException {

        try {
            JspWriter out = pageContext.getOut();

            out.write(Const.H5_OPN + "Добавление компоненты:" + Const.H5_CLS);

            out.write("<form name='addComponent' method='post' action='/bar/jsp/controller'>");
            out.write("<input type='hidden' name='command' value='add_component'>");

            out.write(Const.P_OPN);
            out.write("Название РУС" + Const.BR);
            out.write("<input type='text' name='name_RU' value='' required>" + Const.BR);
            out.write(Const.P_CLS);
            out.write(Const.P_OPN);
            out.write("Название EN" + Const.BR);
            out.write("<input type='text' name='name_EN' value='' required>" + Const.BR);
            out.write(Const.P_CLS);

            out.write(Const.P_OPN);
            out.write("Тип" + Const.BR);
            out.write("<select name='componentType' title='componentType'>");

            Map<Integer,String > componentTypes = new ComponentTypeDao().getComponentTypes();
            for (Map.Entry<Integer,String> pair: componentTypes.entrySet()) {
                out.write("<option value='" + pair.getKey() + "'>" +
                        pair.getValue() + "</option>");
            }

            out.write("</select>");
            out.write(Const.P_CLS);

            out.write(Const.P_OPN);
            out.write("Крепость (%)" + Const.BR);
            out.write("<input type='text' name='strength' value='' required>" + Const.BR);
            out.write(Const.P_CLS);

            out.write(Const.P_OPN);
            out.write("Цена (за мл)" + Const.BR);
            out.write("<input type='text' name='price' value='' required>" + Const.BR);
            out.write(Const.P_CLS);

            out.write("<input type='submit' value='Добавить'/>");
            out.write("</form>");

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }

}
