package ru.kafi.beautysalonbotcommon.util;

public enum Menu {


    PRICE_LIST("прайс-лист"),
    SUBSCRIBE("подписаться"),
    GALLERY("галерея"),
    MAKE_APOINTMENT("записаться"),
    MASTERS("вывести мастеров"),
    REGISTER("зарегистрироваться"),
    ACCOUNT("личный кабинет"),
    STOP("завершить сеанс"),
    INFO("Контакты\\связаться"),
    BACK("<-Назад"),
    APOINTMENTS("ваши записи"),
    ACCOUNT_INFO("информация об аккаунте"),
    UPDATE_ACCOUNT("изменить данные");


    private final String text;

    Menu(final String textVal) {
        this.text = textVal;
    }

    public String getText() {
        return text;
    }

}

