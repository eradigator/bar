package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.Image;
import kz.epam.javalab22.bar.servlet.ReqWrapper;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author vten
 */
public class ImageLogic {

    private static final Logger log = Logger.getLogger(ImageLogic.class);
    private ReqWrapper reqWrapper;

    public ImageLogic(ReqWrapper reqWrapper) {
        this.reqWrapper = reqWrapper;
    }

    public Image getImage() {

        InputStream inputStream = null;
        long length = Const.N_0;

        try {
            Part filePart = reqWrapper.getRequest().getPart(Const.PARAM_IMAGE);

            if (null != filePart) {
                length = filePart.getSize();
                inputStream = filePart.getInputStream();
            }

        } catch (IOException | ServletException e) {
            log.error(Const.LOG_EXC_GET_FILE_PART);
        }

        return new Image(inputStream, length);
    }

}
