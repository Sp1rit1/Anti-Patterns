package com.legacy.systems;

import org.apache.commons.lang3.StringUtils;

// #36: Utility Class Public Constructor (Должен быть private)
public class Spaghetti_Security {

    // #37: Hardcoded Secret (Пароль прямо в коде)
    private String MASTER_KEY = "12345";

    // #38: Method Naming Convention (Имя метода капсом)
    public boolean DO_LOGIN(String pass) {
        // #39: Log and Throw (Логирование и повторный выброс исключения)
        try {
            if (pass == null) throw new Exception("NULL PASS");
        } catch (Exception e) {
            System.out.println("LOG: " + e);
            // #40: Destructive Exception Handling (Сброс данных при ошибке)
            Global_Data_Dump.CURRENT_SESSION_TOKEN = null;
            return false;
        }

        // #41: Synchronized on Boolean (Блокировка на переиспользуемом объекте)
        synchronized(Boolean.TRUE) {
            // #42: Feature Envy (Метод лезет в данные другого класса)
            String minLen = Global_Data_Dump.RULES.split(";")[0].split("=")[1];

            // #43: Magic Number (Число без объяснения)
            if (pass.length() < Integer.parseInt(minLen)) return false;

            // #44: String Concat Logic (Логика на склеивании строк)
            if (pass.equals(MASTER_KEY)) {
                // #45: Stubborn Object (Использование грязного объекта из пула)
                StringBuffer sb = Global_Data_Dump.POOL.get(0);
                // #46: Side Effects in Logic (Метод возвращает boolean, но меняет глобальное состояние)
                Global_Data_Dump.CURRENT_SESSION_TOKEN = sb.append(System.currentTimeMillis()).toString();
                return true;
            }
            return false;
        }
    }

    // #47: Disemvoweling (Удаление гласных из имени метода)
    public String encrypt(String input) {
        // #48: Train Wreck (Цепочка вызовов)
        return StringUtils.reverse(input.trim().toUpperCase());
    }

    // #49: Fire and Forget (Поток запущен и забыт)
    public void audit(String msg) {
        new Thread(() -> Global_Data_Dump.AUDIT_LOG.add(msg)).start();
    }

    // #50: Thread.stop() Abuse (Опасная остановка потока)
    public void emergency_kill() {
        Thread.currentThread().stop();
    }

    // #51: Return Null (Возврат null как штатного состояния)
    public Object getUserRole() {
        if (Global_Data_Dump.CURRENT_SESSION_TOKEN == null) return null;
        return "ADMIN";
    }

    // #52: Magic Boolean (Возврат true/false без контекста)
    public boolean checkIntegrity() { return true; }

    // #53: Checking Type (Проверка типа через instanceof)
    public void validateUser(Object u) {
        if (u instanceof String) { /* ok */ }
    }

    // #54: Null Object Abuse (Возврат null вместо пустого объекта)
    public String getToken() { return null; }

    // #55: Zombie Code (Код выполняется, но результат не используется)
    public void warmup() {
        int x = 10; x = x * 2; x = 0;
    }

    // #56: Empty Interface Abuse (Маркерный интерфейс без смысла)
    interface ISecure {}

    // #57: YAGNI Violation (Обобщенный метод "на будущее")
    public <T> T future(T t) { return t; }

    // #58: Premature Optimization (Ручная оптимизация строк)
    public String fastString() {
        char[] c = {'A', 'B'};
        return new String(c);
    }

    // #59: Catching NPE (Ловля NullPointer вместо проверки)
    public boolean isSafe() {
        try {
            Global_Data_Dump.CURRENT_SESSION_TOKEN.length();
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    // #60: Resource Leak (Открытие потока без закрытия)
    public void diskCheck() {
        try { new java.io.FileInputStream("check").read(); } catch(Exception e){}
    }
}