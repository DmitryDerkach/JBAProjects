/**
 * Порядок инициализации таков:
 * [1]. Статические поля базового класса;
 * [2]. Статический блок инициализации базового класса;
 * [3]. Статические поля производного класса;
 * [4]. Статический блок инициализации производного класса;
 * [5]. Поля (Глобальные переменные) базового класса;
 * [6]. Нестатический блок инициализации базового класса;
 * [7]. Конструктор базового класса [если у родителя нет конструктора без аргументов, то код приведёт к ошибке компиляции];
 * [8]. Поля (Глобальные переменные)  производного класса;
 * [9]. Нестатический блок инициализации производного класса;
 * [10]. Конструктор производного класса;
 */

class Base {

    //[1] transient переменные и статические поля не сериализуются
    private static Base instance = new Base("))(("); //BASE = 0 //
    public static int _baseStaticInsect = 111; //сокрыто, доступ Base._baseStaticBase
    private static final int DELTA = 2;
    private static int BASE = 3;

    //[2]
    static {
        System.out.println("[static instance class initializer] " + Inherit.class.getSimpleName());
    }

    //[5]
    private int i = 5;
    protected int j; //mod:protected-internal
    int x;
    int internal; //mod:package
    public int iInc;

    //[6]
    {
        System.out.println("[instance class initializer] " + this.getClass().getSimpleName());
    }


    //[7]
    //private недоступен  из вне, нельзя создать без рефлексии
    //если у нет конструктора без аргументов или он приватный, то код приведёт к ошибке компиляции];
    public Base() {
        System.out.println("[constructor] " + this.getClass().getSimpleName());
        x = BASE + DELTA;
    }

    //[7]
    public Base(String data) {
        this();
        System.out.println("[constructor] (String)" + this.getClass().getSimpleName());
        System.out.println(data);

    }

    static int printInit(String s) {
        return -1;
    }
}

public class Inherit extends Base {

    //[3]
    public static int _baseStaticBase = 222;
    public static int _baseStaticInherit = 4;

    //[4]
    static {
        System.out.println("[static class initializer] " + Inherit.class.getSimpleName());
    }

    //[8]
    private int internal;

    public int Integnal() { //getInternal
        return this.internal;
    }

    private boolean internalFlag;

    public boolean isInternalFlag() {
        return internalFlag;
    }

    //[9]
    {
        System.out.println("[instance class initializer] Inherit " + this.getClass().getSimpleName());
    }

    //[10]
    public Inherit() {
        this("start");
        System.out.println("Inherit constructor");
    }

    //[10]
    public Inherit(String data) {
        System.out.println("Inherit constructor data");
    }

    public static void main(String[] args) {
    	//System.out.println("Test");
        //Base base = new Base();
        //Inherit inherit = new Inherit();
    }

}