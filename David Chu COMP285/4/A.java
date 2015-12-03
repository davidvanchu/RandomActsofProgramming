
public class A {
	public void foo() {
		System.out.println("A");
	}
}

class B extends A {
	public void foo() {
		System.out.println("B");
	}
}

class C extends B {
	public void foo() {
		System.out.println("C");
	}
}
