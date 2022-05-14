package ui.AllSelenide;

import org.openqa.selenium.By;

public abstract class ByAttribute {

    /**
     * Создает селектор по атрибуту элемента с заданным типом
     *
     * @param elementType             - тип элемента
     * @param attributeName           - название атрибута
     * @param attributeContainedValue - частичное значение заданного атрибута
     */
    public static By byAttributeContaining(String elementType, String attributeName, String attributeContainedValue) {
        return By.xpath(String.format(".//%s[contains(@%s, '%s')]", elementType, attributeName, attributeContainedValue));
    }

    /**
     * Создает селектор по частичному атрибуту class элемента
     *
     * @param classContainedValue - частичное значение поля class
     */

    public static By byClassContaining(String classContainedValue) {
        return byClassContaining("*", classContainedValue);
    }

    /**
     * Создает селектор по частичному атрибуту class элемента с заданным типом
     *
     * @param elementType         - тип элемента
     * @param classContainedValue - частичное значение поля class
     */
    public static By byClassContaining(String elementType, String classContainedValue) {
        return byAttributeContaining(elementType, "class", classContainedValue);
    }

    /**
     * Создает селектор по частичному атрибуту placeholder элемента с заданным типом
     *
     * @param placeholderContainedValue - частичное значение поля placeholder
     */
    public static By byPlaceholderContaining(String placeholderContainedValue) {
        return byAttributeContaining("*", "placeholder", placeholderContainedValue);
    }

    /**
     * Создает селектор по частичному атрибуту placeholder элемента с заданным типом
     *
     * @param text - частичное значение поля placeholder
     */
    public static By byText(String text) {
        return By.xpath(String.format("//*[text()='%s']", text));
    }

    /**
     * @param elementType - тип элемента
     * @param text        - частичное значение заданного атрибута
     */
    public static By byText(String elementType, String text) {
        return By.xpath(String.format("//*[@id=\"root\"]//%s[text()='%s']", elementType, text));
    }

    /**
     * Создает селектор по частичному атрибуту href элемента с заданным типом
     *
     * @param text - частичное значение поля href
     */
    public static By byHref(String text) {
        return byAttributeContaining("a", "href", text);
    }
}

