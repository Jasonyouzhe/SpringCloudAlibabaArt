package test1;

public class V3 extends V2{
    private String name;

    V3(String name){
//        this();
//        super();
        System.out.println("v3 name");
    }

    V3(){
        this("123");
//        super("123");
        System.out.println("v3");
    }

    public static void main(String[] args) {
        final String s = "a";
        new V3();
    }
}
