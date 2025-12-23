package com.legacy.systems;

// #13: Constant Interface (Интерфейс используется как свалка констант)
// #14: Screaming Case Naming (Имя файла капсом с подчеркиванием)
public interface I_Enterprise_Const {
    // #15: Hungarian Notation (Префиксы типов i_ в именах)
    public static final int i_ZERO = 0;

    // #16: Magic String Constant (Строка меню зашита в константу)
    public static final String STR_OPTS = "1. AUTH | 2. CALC | 3. SAVE | 0. EXIT";

    // #17: Hardcoded Path (Жесткий путь к файлу, зависящий от ОС)
    public static final String PATH_WIN = "C:\\Windows\\Temp\\db.log";
}