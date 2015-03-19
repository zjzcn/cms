package com.zjzcn.util;

public class Test {
	static void test1()
	{
		System.out.println("test1");
	}
	public static void main(String[] args) {
		((Test)null).test1();
		
		 String s=null;

	     System.out.println("s=" + s);
	     
	     char[] ch = { 'a', 'b', 'c' };
	     
	     System.out.println(ch);
	     
	     new Child("child");
	}

}

class People {
    String name;

    public People() {
        System.out.print(1);
    }

    public People(String name) {
        System.out.print(2);
        this.name = name;
    }
}

class Child extends People {
    People father;

    public Child(String name) {
//    	super(name);
        System.out.print(3);
        this.name = name;
        father = new People(name + ":F");
    }

    public Child() {
        System.out.print(4);
    }
    
}
