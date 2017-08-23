package kz.epam.javalab22.bar.tag;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.entity.Cocktail;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class Alcoholic extends TagSupport {
    @Override
    public int doStartTag() throws JspException {

        List<Cocktail> cocktailList = new CocktailDao().getCocktailsList();

        try {
            JspWriter out = pageContext.getOut();

            out.write("<p>\n" +
                    "            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the\n" +
                    "            industry's\n" +
                    "            standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it\n" +
                    "            to make\n" +
                    "            a\n" +
                    "            type specimen book. It has survived not only five centuries, but also the leap into electronic\n" +
                    "            typesetting,\n" +
                    "            remaining\n" +
                    "            essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing\n" +
                    "            Lorem\n" +
                    "            Ipsum\n" +
                    "            passages, and more recently with desktop publishing software like Aldus PageMaker including versions of\n" +
                    "            Lorem\n" +
                    "            Ipsum.\n" +
                    "        </p>");


            for (Cocktail cocktail : cocktailList) {

                out.write("<div class='cocktail' >");
                /*out.write("<a href='/bar/images/cocktails/white_russian.jpg' target='_blank'>");*/
                out.write("<a href='" + cocktail.getImgPath() + "' target='_blank'>");
                out.write("<img class='cocktail_image' src='" + cocktail.getImgPath() + "'>");
                out.write("</a>");
                out.write("Name: " + cocktail.getName() + Const.BR);
                out.write("Build Method: " + cocktail.getBuildMethod().toString() + Const.BR);
                out.write("Glass: " + cocktail.getGlass().toString() + Const.BR);
                out.write("</div>");

                out.write(Const.BR);
            }

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
