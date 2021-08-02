*****************************饿汉式1**********************************
class Bank{
	
	//1.私化类的构造器
	private Bank(){
		
	}
	
	//2.内部创建类的对象
	//4.要求此对象也必须声明为静态的
	private static Bank instance = new Bank();
	
	//3.提供公共的静态的方法，返回类的对象
	public static Bank getInstance(){
		return instance;
	}
}

*****************************饿汉式2**********************************
class Order{
	
	//1.私化类的构造器
	private Order(){
		
	}
	
	//2.声明当前类对象，没初始化
	//4.此对象也必须声明为static的
	private static Order instance = null;

	static{
		instance = new Order();
 }
	
	//3.声明public、static的返回当前类对象的方法
	public static Order getInstance(){
		return instance;
	}
}
*****************************饿汉式3**********************************
class Order{
	
	//1.私化类的构造器
	private Order(){
		
	}
	
	public static final Order instance = new Order();	
}


*****************************懒汉式**********************************
class Order{
	
	//1.私化类的构造器
	private Order(){
		
	}
	
	//2.声明当前类对象，没初始化
	//4.此对象也必须声明为static的
	private static Order instance = null;
	
	//3.声明public、static的返回当前类对象的方法
	public static Order getInstance(){
		
		if(instance == null){
			
			instance = new Order();
		}
		return instance;
	}
}