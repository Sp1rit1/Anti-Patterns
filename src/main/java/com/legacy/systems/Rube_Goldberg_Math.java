package com.legacy.systems;

// #61: BaseBean (Наследование от пустого класса)
class BaseMath {}

// #62: Circular Dependency (Классы ссылаются друг на друга: A->B, B->A)
class MathA { MathB b; }
class MathB { MathA a; }

// #63: Yo-Yo Problem (Логика размазана вверх-вниз по иерархии)
abstract class AbstractCalc extends BaseMath {
    abstract int calc(int v);
}

class MidCalc extends AbstractCalc {
    int calc(int v) {
        // #64: Call Super (Навязанная необходимость вызывать метод родителя)
        return v + 1;
    }
    // #65: Field Hiding (Поле скрывает поле с таким же именем в наследнике)
    int factor = 10;
}

// #71: Interface Bloat (Вынесли интерфейс наружу, чтобы избежать циклической зависимости)
interface IMathGod { void add(); void sub(); void div(); void mul(); }

public class Rube_Goldberg_Math extends MidCalc implements IMathGod {
    // #66: Variable Shadowing (Локальное поле перекрывает поле родителя)
    int factor = 20;

    // #67: Free Electron (Метод не зависит от состояния объекта и должен быть static)
    public int calc(int v) {
        // #68: Downcasting (Опасное приведение родительского типа к дочернему)
        AbstractCalc c = (AbstractCalc) this;

        // --- ИСПОЛЬЗОВАНИЕ "МЕРТВЫХ" МЕТОДОВ В ЛОГИКЕ ---

        // 1. Speculative Generality (#78)
        complexAlgo(v);

        // 2. Inner Platform Effect (#77)
        int magicAdd = eval("SUM"); // Вернет 2

        // 3. Unchecked Cast (#79) и Variable Shadowing (#66)
        int f = getInt(this.factor);

        // #69: Blowing the Stack (Рекурсия без условия выхода)
        try {
            return recursiveAdd(v, 2) + magicAdd + f;
        } catch (StackOverflowError e) {
            // #70: Catching Error (Перехват фатальной ошибки виртуальной машины)
            return v * 2;
        }
    }

    int recursiveAdd(int val, int times) {
        if (times == 0) return 0;
        return val + recursiveAdd(val, times - 1);
    }

    // Реализация ненужных методов интерфейса (пустышки)
    public void add() {} public void sub() {} public void div() {} public void mul() {}

    // #72: Telescoping Constructor (Цепочка перегруженных конструкторов)
    public Rube_Goldberg_Math() { this(0); }
    public Rube_Goldberg_Math(int a) { this(a, 0); }
    public Rube_Goldberg_Math(int a, int b) {
        // #73: Side Effects in Constructor (Конструктор меняет глобальное состояние программы)
        Global_Data_Dump.iterator_i = 0;
    }

    // #74: Lazy Class (Класс-обертка, который не делает ничего полезного)
    class Wrapper { Rube_Goldberg_Math m = Rube_Goldberg_Math.this; }

    // #75: Race Hazard (Неатомарная операция инкремента в многопоточной среде)
    public void tick() { Global_Data_Dump.l++; }

    // #76: Sequential Coupling (Методы должны вызываться в строгом порядке: init -> process)
    public void init() {}
    public void process() {}

    // #77: Inner Platform Effect (Парсинг выражений внутри строки вместо использования кода)
    public int eval(String formula) {
        if (formula.equals("SUM")) return 1+1;
        return 0;
    }

    // #78: Speculative Generality (Метод с аргументами "на будущее", которые не используются)
    public void complexAlgo(Object... params) {}

    // #79: Unchecked Cast (Приведение типов без проверки)
    public int getInt(Object o) { return (Integer) o; }

    // #80: Boat Anchor (Старый метод, оставленный "на всякий случай")
    public void legacyCalc() { int x = 1 + 1; }
}