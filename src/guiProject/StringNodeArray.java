package guiProject;

/**
 * @author Dan Gavin	
 * 
 * This is an Array class for storing the ADT String Nodes into a list. 
 * 
 */
public class StringNodeArray {
	
	private StringNode[] list; //array of StringNode objects, create two for undo/redo
	private int count;
	
	
	public StringNodeArray(){
		
		list = new StringNode[8]; //unargumented constructor creates array of size 8
		
	}
	
	public StringNodeArray(int size){
		
		list = new StringNode[size]; //create array of any size if need be
		
	}
	
	public String getNode(int pos){
		
		return list[pos].toString();
	}
	
	public void addNode(StringNode in){//add node, node constructed in tester class
		
		if (count == list.length){
			//shift down
			this.shift(0);
		}
		list[count] = new StringNode(in);
		
		if (count < list.length){//do not add to count if array is full
			count++;
			
		}
	}
	
	public void addNode(int pos, String data){ //add node, node constructed in this class from data provided from the Tester
		
		if (count == list.length){
			//shift down, removed oldest node
			this.shift(0);
		}
		list[count] = new StringNode(pos, data);
		
		if (count < list.length){//do not add to count if array is full
			count++;
			
		}
	}
	
	public StringNode takeNode(int pos){
		
		StringNode temp = new StringNode(list[pos]); //copy node to be returned
		
		this.shift(pos); //shift array positions left from removed node, overwrites removed
		count--; //lower count after shift
		
		return temp;
		
		
	}
	
	public void shift(int pos){
		
		for (int i = pos; i < count-1; i++){ //not necessary to overwrite last node in array, will be ignored by count in removal or overwritten when shift is added. 
			
			list[i] = list[i+1];
		}
		
	}
	
	public String toString(){
		
		String fullList;
		
		fullList = new String("Listed Nodes: \n");
		
		for (int i = 0; i < count; i++){
			
			fullList += "Node " + i + ": " + list[i].toString() + "\n";
		}
		
		return fullList;
	}
}