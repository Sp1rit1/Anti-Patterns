package com.legacy.systems;

import java.util.ArrayList;
import java.util.Vector;
import java.util.HashMap;

// #18: Data Class (Класс без поведения, только данные)
public class Global_Data_Dump {
    // #19: Global State (Глобальное состояние, доступное всем)
    // #20: Object Orgy (Публичные поля без инкапсуляции)
    public static String CURRENT_SESSION_TOKEN = null;

    // #21: Obsolete Collection (Использование Vector вместо ArrayList)
    public static Vector<Object> DB = new Vector<>();

    // #22: Temporary Field (Глобальный итератор для всех циклов)
    public static int iterator_i = 0;

    // #23: Flag Argument (Флаг, управляющий всем циклом программы)
    public static boolean b_Running = true;

    // #24: Static Initializer Abuse (Логика внутри static блока)
    static {
        try {
            // #25: Golden Hammer (String используется для хранения сложной структуры)
            // #26: Primitive Obsession (Хранение данных без объектов User)
            DB.add("0|ADMIN|ROOT_ACCESS");
        } catch (Exception e) {
            // #27: Swallowing Exception (Пустой catch)
        }
    }

    // #28: Magic Container (Map вместо класса конфигурации)
    public static HashMap<String, String> CFG = new HashMap<>();
    static { CFG.put("PATH", "/tmp/db_legacy.txt"); } // Инициализация

    // #29: Soft Coding (Логика вынесена в строку конфигурации)
    public static String RULES = "MIN_LEN=3;MAX_LEN=10";

    // #30: Public Static Mutable Collection (Небезопасная коллекция логов)
    public static ArrayList<String> AUDIT_LOG = new ArrayList<>();

    // #31: Object Cesspool (Пул "грязных" объектов)
    public static ArrayList<StringBuffer> POOL = new ArrayList<>();
    static { POOL.add(new StringBuffer("SALT_")); }

    // #32: Variable Shadowing (Имя поля совпадает с именем класса)
    public static int Global_Data_Dump = 100;

    // #33: Confusing Naming (Имена l и 1, O и 0)
    public static int l = 1;
    public static int O = 0;

    // #34: Double Negative (Двойное отрицание в имени boolean)
    public static boolean isNotDisconnected = true;

    // #35: Refused Bequest (Наследуем Object, но ломаем контракт toString)
    public String toString() { return null; }
}