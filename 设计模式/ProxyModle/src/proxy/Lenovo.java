package proxy;

/*
真实类
 */
public class Lenovo implements SaleComputer{
    @Override
    public String sale(double money) {
        System.out.println("花了"+money+"元买了一台电脑");

        return "Lenovo Computer";
    }

    @Override
    public void show() {
        System.out.println("show Lenovo Computer");

    }
}
