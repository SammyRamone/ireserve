package marc_playground;

public class Triple<E1, E2, E3> {
	private E1 o1;
	private E2 o2;
	private E3 o3; 
	
	public Triple(){
		this.o1 = null;
		this.o2 = null;
		this.o3 = null;
	}
	public Triple(E1 o1, E2 o2,E3 o3){
		this.o1 = o1;
		this.o2 = o2;
		this.o3 = o3;
	}
	
	public E1 getFirst(){
		return o1;
	}
	public E2 getSecond(){
		return o2;
	}
	public E3 getThird(){
		return o3;
	}
	public void modifyFirst(E1 o){
		this.o1 = o;
	}
	public void modifySecond(E2 o){
		this.o2 = o;
	}
	public void modifyThird(E3 o){
		this.o3 = o;
	}
}
