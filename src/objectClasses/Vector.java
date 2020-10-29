package objectClasses;

public class Vector {
	
	private int x;
	private int y;
	private int z;
	
	
	public Vector(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int[] getVector(){
		return new int[]{x,y,z};
	}
	
	public void setVector(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
