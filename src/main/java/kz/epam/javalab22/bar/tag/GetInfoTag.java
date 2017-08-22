package kz.epam.javalab22.bar.tag;

import kz.epam.javalab22.bar.dao.CocktailDao;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class GetInfoTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {

        String cocktailName;
        cocktailName = new CocktailDao().getNameById(1);

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
            for (int i=0; i<5; i++) {
                out.write("<img src='/bar/images/logo.png' style='width:128px;height:128px;'>");
                out.write(cocktailName);
                out.write("<hr/>");
            }

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
