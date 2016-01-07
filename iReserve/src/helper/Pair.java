package helper;


public class Pair<E1,E2>  {

	private E1 o1; 
	private E2 o2; 
	
	public Pair(E1 o1, E2 o2){
		this.o1 = o1; 
		this.o2 = o2; 
	}
	public E1 getFirst(){
		return o1;
	}
	public E2 getSecond(){
		return o2;
	}
	
	public void modifyFirst(E1 o){
		this.o1 = o;
	}
	public void modifySecond(E2 o){
		this.o2 = o;
	}
}
