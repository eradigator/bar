package kz.epam.javalab22.bar.logic;

import kz.epam.javalab22.bar.constant.Const;
import kz.epam.javalab22.bar.entity.Component;
import kz.epam.javalab22.bar.entity.ComponentName;
import kz.epam.javalab22.bar.entity.Mix;
import kz.epam.javalab22.bar.exception.GetMixFromRequestException;
import kz.epam.javalab22.bar.manager.MessageManager;
import kz.epam.javalab22.bar.servlet.ReqWrapper;

public class CalculatorLogic {

    private ReqWrapper reqWrapper;

    public CalculatorLogic(ReqWrapper reqWrapper) {
        this.reqWrapper = reqWrapper;
    }

    public Mix getMixFromRequest() throws GetMixFromRequestException {

        String[] components = reqWrapper.getParams(Const.PARAM_INGREDIENT);
        String[] amounts = reqWrapper.getParams(Const.PARAM_AMOUNT_OF_INGREDIENT);
        String[] componentNames = reqWrapper.getParams(Const.PARAM_INGREDIENT_NAME);
        Mix mix = new Mix();

        if (null != components && null != amounts && null != componentNames) {
            for (int i = Const.N_0; i < components.length; i++) {
                ComponentName componentName = new ComponentName(componentNames[i]);
                int componentId = Integer.parseInt(components[i]);
                Component component = new Component(componentId, componentName);
                mix.getMix().put(component, Integer.parseInt(amounts[i]));
            }
        } else {
            MessageManager messageManager = new MessageManager(reqWrapper.getLocale());
            String message = messageManager.getProperty(Const.PROP_NO_COMPONENT_SELECTED);
            reqWrapper.addAttribute(Const.ATTR_ERROR, message);
            throw new GetMixFromRequestException();
        }

        return mix;
    }
}
