package cn.bmilk.asm;

public class InstrumentTest {
    public static void main(String[] args) throws InterruptedException {
        BaseClass baseClass = new BaseClass();
        while (true){
            System.out.println("======start========");
            baseClass.method();
            System.out.println("======end========");

            Thread.sleep(5000);
        }
    }
}
