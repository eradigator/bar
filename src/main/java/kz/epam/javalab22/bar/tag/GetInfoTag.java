package kz.epam.javalab22.bar.tag;

import kz.epam.javalab22.bar.dao.CocktailDao;
import kz.epam.javalab22.bar.pool.ConnectionPool;

import java.io.IOException;
import java.sql.Connection;
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
            for (int i=0; i<5; i++) {
                out.write("<img src='/bar/images/logo.png' alt='Это рюмка' style='width:128px;height:128px;'>");
                out.write(cocktailName);
                out.write("<hr/>");
            }

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
