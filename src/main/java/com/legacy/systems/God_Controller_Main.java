package com.legacy.systems;

import java.util.Scanner;

// #81: God Object (Класс берет на себя слишком много: UI, логика, данные, управление)
public class God_Controller_Main implements I_Enterprise_Const {

    // #82: Throws in Main (Main не обрабатывает исключения, а выбрасывает их)
    public static void main(String[] args) throws Exception {
        Spaghetti_Security security = new Spaghetti_Security();

        // #58: Используем Premature Optimization при старте
        System.out.println("BOOT: " + security.fastString());

        // #83: Busy Waiting (Цикл ожидания, потребляющий процессор впустую)
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start < 100) {}

        Scanner sc = new Scanner(System.in);

        // #84: Infinite Loop (Бесконечный цикл)
        // ИСПОЛЬЗОВАНИЕ: Проверяем двойное отрицание isNotDisconnected (#34)
        while (Global_Data_Dump.b_Running && Global_Data_Dump.isNotDisconnected) {

            // #85: Spaghetti Code (UI код перемешан с бизнес-логикой)
            System.out.println(STR_OPTS);
            System.out.print("CMD > ");
            String cmd = sc.nextLine();

            // #86: Switch Statement (Использование switch вместо полиморфизма)
            switch (cmd) {
                case "1":
                    // #87: Static Method Abuse (Злоупотребление статическими методами)
                    do_AUTH(sc, security); break;
                case "2":
                    do_CALC(sc); break;
                case "3":
                    do_SAVE(); break;
                case "0":
                    // ИСПОЛЬЗОВАНИЕ: Вызываем shutdown перед выходом (#100)
                    shutdown();
                    // #88: Exception for Flow Control (Использование исключения для выхода из цикла)
                    throw new RuntimeException("EXIT");
                default:
                    // #89: Magic String Usage (Использование строкового литерала вместо константы)
                    System.out.println("UNKNOWN");
            }
            // #90: Manual GC (Ручной вызов сборщика мусора)
            System.gc();
        }
    }

    public static void do_AUTH(Scanner s, Spaghetti_Security sec) {
        // Используем #52 Magic Boolean
        if (!sec.checkIntegrity()) return;

        // Используем #49 Fire and Forget
        sec.audit("Login attempt");

        System.out.print("PASS (12345): ");
        // #91: Single Letter Naming (Имя переменной из одной буквы)
        String p = s.nextLine();

        // ИСПОЛЬЗОВАНИЕ: Выводим переменную, имя которой совпадает с классом (#32 Variable Shadowing)
        System.out.println("Debug Val: " + Global_Data_Dump.Global_Data_Dump);

        // Используем #53 Checking Type
        sec.validateUser(p);

        // #92: Blind Faith (Отсутствие валидации ввода на null)
        if (sec.DO_LOGIN(p)) {
            System.out.println("OK. Token: " + Global_Data_Dump.CURRENT_SESSION_TOKEN);
            sec.warmup(); // #55 Zombie Code
            sec.isSafe(); // #59 Catching NPE
        } else {
            System.out.println("FAIL");
        }
    }

    public static void do_CALC(Scanner s) {
        // Используем #51 Return Null и #54 Null Object Abuse
        if (new Spaghetti_Security().getUserRole() == null) {
            // getToken всегда null, но мы его вызываем для галочки
            new Spaghetti_Security().getToken();
            System.out.println("LOGIN FIRST!"); return;
        }

        System.out.print("STUDENT NAME: ");
        String name = s.nextLine();
        // Используем #47 Disemvoweling (encrypt)
        String encName = new Spaghetti_Security().encrypt(name);

        System.out.print("VAL: ");
        // #93: Pokemon Exception Handling (Ловля общего Exception без разбора)
        try {
            int v = Integer.parseInt(s.nextLine());

            // #94: Shotgun Surgery (Создание сложного объекта для простого действия)
            Rube_Goldberg_Math math = new Rube_Goldberg_Math();
            math.init(); // #76 Sequential Coupling
            math.process();

            int res = math.calc(v); // Здесь рекурсия, Inner Platform и прочее
            System.out.println("RES: " + res);

            // #95: String Concat Logic (Склеивание данных для БД)
            String dbRow = System.currentTimeMillis() + "|" + encName + "|" + res;

            Global_Data_Dump.DB.add(dbRow);

            math.legacyCalc(); // #80 Boat Anchor
            math.tick(); // #75 Race Hazard

        } catch (Exception e) {
            System.out.println("ERR");
        }
    }

    public static void do_SAVE() {
        // #96: Long Method / Arrow Code (Глубокая вложенность кода)
        if (Global_Data_Dump.DB != null) {

            // Читаем из Magic Container (#28)
            String configuredPath = Global_Data_Dump.CFG.get("PATH");
            System.out.println("Configured path: " + configuredPath);

            // ИСПОЛЬЗОВАНИЕ: i_ZERO (#15) и O (#33) в цикле
            int startIdx = I_Enterprise_Const.i_ZERO + Global_Data_Dump.O;

            // #97: Global Index Loop (Использование глобального счетчика цикла)
            for(Global_Data_Dump.iterator_i = startIdx; Global_Data_Dump.iterator_i < Global_Data_Dump.DB.size(); Global_Data_Dump.iterator_i++) {
                // #98: Unchecked Cast (Каст без проверки)
                String row = (String) Global_Data_Dump.DB.get(Global_Data_Dump.iterator_i);
                System.out.println("SAVED TO " + PATH_WIN + ": " + row);
            }
        }
        // #99: Commented Out Code (Закомментированный код)
        // saveToDisk();
    }

    // #100: Big Ball of Mud (Вся программа - это один большой ком грязи)
    public static void shutdown() {
        System.out.println("SHUTTING DOWN SYSTEM...");
        Global_Data_Dump.b_Running = false;

        Spaghetti_Security sec = new Spaghetti_Security();
        sec.diskCheck(); // #60 Resource Leak

        // Используем #50 Thread.stop (Emergency kill)
        try { sec.emergency_kill(); } catch (Throwable t) {}
    }
}