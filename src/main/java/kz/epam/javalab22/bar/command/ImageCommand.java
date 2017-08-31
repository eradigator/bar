package kz.epam.javalab22.bar.command;

import kz.epam.javalab22.bar.dao.ImageDao;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vten on 31.08.2017.
 */
public class ImageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        /*byte[] bytes = new ImageDao().getImage(id);*/


        return null;
    }
}
