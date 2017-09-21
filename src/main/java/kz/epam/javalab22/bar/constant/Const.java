package kz.epam.javalab22.bar.constant;

/**
 * @author vten
 */
public final class Const {

    /*Dividers*/
    public static final String DIV_SPACE=" ";

    /*Alcohol Amount*/
    public static final double ALC_AMOUNT_0 = 0;
    public static final double ALC_AMOUNT_0_1 = 0.1;
    public static final double ALC_AMOUNT_10 = 10;
    public static final double ALC_AMOUNT_20 = 20;
    public static final double ALC_AMOUNT_100 = 100;


    /*Pages*/
    public static final String PAGE_INDEX = "path.page.index";
    public static final String PAGE_LOGIN = "path.page.login";
    public static final String PAGE_REFFERER = "path.page.refferer";

    /*Attributes*/
    public static final String ATTR_CONTENT = "content";
    public static final String ATTR_ADD_COCKTAIL_RESULT = "addCocktailResult";
    public static final String ATTR_DEL_COCKTAIL_RESULT = "removeCocktailResult";
    public static final String ATTR_COCKTAIL = "cocktail";
    public static final String ATTR_RESULT = "result";
    public static final String ATTR_ERROR = "error";
    public static final String ATTR_COCKTAIL_LIST = "cocktailList";
    public static final String ATTR_UI_TEXT = "uiText";
    public static final String ATTR_COMPONENTS = "components";
    public static final String ATTR_COMPONENT_NAMES = "componentNames";
    public static final String ATTR_DEL_COMPONENT_MESSAGE = "delComponentMessage";
    public static final String ATTR_USER = "user";
    public static final String ATTR_USERS = "users";
    public static final String ATTR_COCKTAIL_NAMES = "cocktailNames";
    public static final String ATTR_METHODS = "methods";
    public static final String ATTR_GLASSES = "glasses";
    public static final String ATTR_COMPONENT_TYPES = "componentTypes";
    public static final String ATTR_ADD_USER_RESULT = "addUserResult";
    public static final String ATTR_DEL_USER_RESULT = "deleteUserResult";
    public static final String ATTR_LOCALE = "locale";
    public static final String ATTR_ERROR_LOGIN_PASS_MESSAGE = "errorLoginPassMessage";
    public static final String ATTR_COCKTAIL_LIST_INDEX = "cocktailListIndex";
    public static final String ATTR_STRENGTH = "strength";
    public static final String ATTR_AMOUNT = "amount";
    public static final String ATTR_COST = "cost";
    public static final String ATTR_CALC_RESULT = "calcResult";
    public static final String ATTR_FILTER_CHECKED_ID = "filter_checked_id";
    public static final String ATTR_SORT_CHECKED_INDEX = "sort_checked_index";

    /*Exceptions*/

    public static final String EXC_NO_ID_OBTAINED = "failed, no ID obtained.";

    /*Indexes*/
    public static final int INDEX_0 = 0;
    public static final int INDEX_1 = 1;

    /*Log messages*/
    public static final String LOG_FORBIDDEN_PAGE = "Attempt to load a forbidden page";
    public static final String LOG_WRONG_ACTION = "Wrong Action";
    public static final String LOG_EMPTY_COMMAND = "Empty Command";
    public static final String LOG_COCKTAIL = "cocktail";
    public static final String LOG_HAS_BEEN_ADDED = "has been added";
    public static final String LOG_HAS_BEEN_DELETED= "has been deleted";
    public static final String LOG_COMPONENT = "component";
    public static final String LOG_USER = "user";
    public static final String LOG_LOGGED_OUT = "has logged out";
    public static final String LOG_LOGGED_IN = "has logged in";
    public static final String LOG_UNSUCCESSED_LOG_IN = "unsuccessed log in";
    public static final String LOG_CONN_POOL_INITIALIZED = "Connection Pool has been initialized";
    public static final String LOG_EXC_CREATE_NEW_CONNECTION = "create new connection method exception";
    public static final String LOG_EXC_CLOSE_CONNECTION = "close connection method exception";
    public static final String LOG_EXC_SQL = "SQL exception";
    public static final String LOG_EXC_IMG = "add image exception";
    public static final String LOG_EXC_IMG_CLOSE_INPUTSTREAM = "close image inputstream exception";
    public static final String LOG_EXC_GET_FILE_PART = "image get file part exception";


    /*Numbers*/
    public static final int N_0 = 0;
    public static final int N_100 = 100;

    /*Parameters*/
    public static final String PARAM_REQUEST_ENCODING = "requestEncoding";
    public static final String PARAM_COMMAND = "command";
    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_ROLE = "role";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_ID = "id";
    public static final String PARAM_COCKTAIL_ID_TO_DELETE = "cocktailIdToDelete";
    public static final String PARAM_COCKTAIL_ID = "cocktailId";
    public static final String PARAM_COMPONENT_TO_DEL = "componentToDel";
    public static final String PARAM_CHOSEN = "chosen";
    public static final String PARAM_CHECKED_NAME = "checkedName";
    public static final String PARAM_IMAGE = "image";
    public static final String PARAM_INGREDIENT = "ingredient";
    public static final String PARAM_AMOUNT_OF_INGREDIENT = "amountOfIngredient";
    public static final String PARAM_INGREDIENT_NAME = "ingredientName";
    public static final String PARAM_NAME_RU = "name_RU";
    public static final String PARAM_NAME_EN = "name_EN";
    public static final String PARAM_COMPONENT_TYPE = "componentType";
    public static final String PARAM_STRENGTH = "strength";
    public static final String PARAM_PRICE = "price";
    public static final String PARAM_METHOD = "method";
    public static final String PARAM_GLASS = "glass";
    public static final String PARAM_COCKTAIL_LIST_INDEX = "cocktailListIndex";
    public static final String PARAM_FILTER = "filter";
    public static final String PARAM_SORT = "sort";



    /*Property names*/
    public static final String PROP_UI_TEXT_FOR_ALCOHOLIC_PAGE = "uiTextIdForAlcoholicPage";
    public static final String PROP_UI_TEXT_FOR_MAIN_PAGE = "uiTextIdForMainPage";
    public static final String PROP_LOGIN_ERROR = "loginError";
    public static final String PROP_USER_EXIST = "userExist";
    public static final String PROP_USER_ADDED = "userAdded";
    public static final String PROP_ERROR = "error";
    public static final String PROP_USER_DELETED = "userDeleted";
    public static final String PROP_NO_COMPONENT_SELECTED = "noComponentSelected";
    public static final String PROP_COMPONENT_ADDED = "componentAdded";
    public static final String PROP_COMPONENT_DELETED = "componentDeleted";
    public static final String PROP_COMPONENT_EXIST = "componentExist";
    public static final String PROP_COCKTAIL_ADDED = "cocktailAdded";
    public static final String PROP_COCKTAIL_DELETED = "cocktailDeleted";
    public static final String PROP_COCKTAIL_EXIST = "cocktailExist";

    /*Parameter Values*/
    public static final String VAL_MAIN = "main";
    public static final String VAL_COCKTAIL = "cocktail";
    public static final String VAL_COCKTAILS = "cocktails";
    public static final String VAL_CALCULATOR = "calculator";
    public static final String VAL_COCKTAIL_MANAGER = "cocktailManager";
    public static final String VAL_COMPONENT_MANAGER = "componentManager";
    public static final String VAL_USER_MANAGER = "userManager";
    public static final String VAL_FAVORITE = "favorite";
    public static final String VAL_ALL = "all";
    public static final String VAL_NON_ALCO = "nonalco";
    public static final String VAL_LOW = "low";
    public static final String VAL_MIDDLE = "middle";
    public static final String VAL_STRONG = "strong";
    public static final String VAL_BY_NAME = "by_name";
    public static final String VAL_BY_STRENGTH = "by_strength";

    /*Servlet Parameters*/
    public static final String RESPONSE_CONTENT_TYPE_IMAGE = "image/jpeg";

    /*Strings*/
    public static final String STR_EN = "EN";
    public static final String STR_RU = "RU";
    public static final String STR_EMPTY = "";

    /*Locales & Encoding*/
    public static final String LOC_UTF8 = "UTF-8";
    public static final String LOC_RU_RU = "ru_RU";
    public static final String LOC_EN_US = "en_US";


    /*SQL*/
    public static final int SQL_PARAM_INDEX_1 = 1;
    public static final int SQL_PARAM_INDEX_2 = 2;
    public static final int SQL_PARAM_INDEX_3 = 3;
    public static final int SQL_PARAM_INDEX_4 = 4;
    public static final String COLUMN_LABEL_PASSWORD = "password";
    public static final String COLUMN_LABEL_ID = "id";
    public static final String COLUMN_LABEL_ROLE = "role";
    public static final String COLUMN_LABEL_EMAIL = "email";
    public static final String COLUMN_LABEL_NAME = "name";
    public static final String COLUMN_LABEL_TEXT_RU = "text_ru";
    public static final String COLUMN_LABEL_TEXT_EN = "text_en";
    public static final String COLUMN_LABEL_AMOUNT = "amount";
    public static final String COLUMN_LABEL_NAMERU = "nameRu";
    public static final String COLUMN_LABEL_NAMEEN = "nameEn";
    public static final String COLUMN_LABEL_NAME_RU = "name_ru";
    public static final String COLUMN_LABEL_NAME_EN = "name_en";
    public static final String COLUMN_LABEL_BYTES = "bytes";
    public static final String COLUMN_LABEL_RU = "ru";
    public static final String COLUMN_LABEL_EN = "en";
    public static final String COLUMN_LABEL_STRENGTH = "strength";
    public static final String COLUMN_LABEL_PRICE = "price";
    public static final String COLUMN_LABEL_COCKTAIL_NAME_ID = "cocktailNameId";
    public static final String COLUMN_LABEL_COCKTAIL_NAME_NAME_RU = "cocktailNameNameRu";
    public static final String COLUMN_LABEL_COCKTAIL_NAME_NAME_EN = "cocktailNameNameEn";
    public static final String COLUMN_LABEL_METHOD_ID = "methodId";
    public static final String COLUMN_LABEL_METHOD_NAME_RU = "methodNameRu";
    public static final String COLUMN_LABEL_METHOD_NAME_EN = "methodNameEn";
    public static final String COLUMN_LABEL_GLASS_ID = "glassId";
    public static final String COLUMN_LABEL_GLASS_NAME_RU = "glassNameRu";
    public static final String COLUMN_LABEL_GLASS_NAME_EN = "glassNameEn";
    public static final String COLUMN_LABEL_IMAGE_ID = "image_id";
    public static final String COLUMN_LABEL_TYPE_ID = "type_id";
    public static final String COLUMN_LABEL_TYPE_NAME_RU = "typeNameRu";
    public static final String COLUMN_LABEL_TYPE_NAME_EN = "typeNameEn";
    public static final String COLUMN_LABEL_COCKTAIL_ID = "cocktail_id";

}
