package com.store.util;

public final class InfoResult {
    public static final int OK = 200;
    public static final int NOT_FOUND = 404;
    public static final int FAILED_DOUBLE = 409;
    public static final int FAILED_EXISTS = 405;
    public static final int FAILED_INCOMPATIBILITY = 406;
    public static final int FATAL_ERROR = 500;
    public static final String FAILED_DOUBLE_MESSAGE = "Дублирование записи";
    public static final String FAILED_EXISTS_MESSAGE = "Запись уже существует";
    public static final String FAILED_INCOMPATIBILITY_MESSAGE = "Несоответствие данных";
    public static final String FATAL_ERROR_MESSAGE = "Неизвестная ошибка";
    public static final String UPDATED_MESSAGE = "Запись обновлена";
    public static final String NOT_FOUND_MESSAGE = "Запись не найдена";
}
