/**
 * @author David Chu
 * class time 3pm M/T/W/F
 *
 * A simple Integer Array Buffer that will hold a collection of integers
 * in no particular order.
 *
 */

public class IntArrayBuffer{
	protected int[] buffer;			//the buffer
	protected int endPoint;			//a pointer to the next free space

	protected int workDone;			//a counter that counts the amount of work done

	/**
	 * constructor - will set the internal array size to be 100
	 */
	public IntArrayBuffer(){
		this(100);			//set default size to be 100
	}

	/**
	 * constructor - will set the internal array size to be size
	 * @param size - the size of the array
	 */
	public IntArrayBuffer(int size){
		if (size <= 0){
			/* since we do not know exceptions yet,
			 * lets print an error message and quit
			 */
			System.out.println("Initial size: "+size+" has to be > 0");
			System.exit(0);
		}
		buffer = new int[size];
		endPoint = 0;
		workDone = 0;
	}

	/**
	 * The purpose of this method is to append a value to the buffer.
	 * If the buffer is full, the buffer is resized automatically
	 * to be twice as large as the old buffer.
	 * @param value - the value to be appended
	 */
	public void add(int value){
		workDone = 1;

		if (endPoint == buffer.length){				//is the buffer full?
			//resize the array
			int[] temp = new int[buffer.length*2];	//create a new buffer
			//transfer the old data into the new array
			for(int i=0; i<endPoint; i++){			//transfer data over
				temp[i] = buffer[i];
				workDone++;
			}
			buffer = temp;							//set old to be new
		}

		buffer[endPoint] = value;					//add the value
		endPoint++;
	}

	/**
	 * The purpose of this method is to append the contents of an
	 * array to this buffer.  Duplicates are ignored.
	 * @param values - an array of values to be added
	 */
	public void add(int[] values){
		int saveWorkDone = 1;

		for(int i=0; i<values.length; i++){
			add(values[i]);
			saveWorkDone += workDone;
		}
		workDone = saveWorkDone;
	}

	/**
	 * The purpose of this method is to remove a value from
	 * the buffer.
	 * @param value - the value to be added
	 * @return true if the value is successfully removed; false
	 * otherwise.
	 */
	public boolean remove(int value){
		/**
		 1. find the value
		 2. if found, remove the value and return true
		    else return false
		 */

		workDone = 1;
		int pos = search(value);
		if (pos == -1) return false;

		do{
			workDone++;
			for(int i=pos+1; i<endPoint; i++)
				buffer[i-1] = buffer[i];
			endPoint--;
			pos = search(value);
		}while(pos != -1);				//repeat to remove duplicates

		return true;
	}

	/**
	 * The purpose of this method is to remove an array of values
	 * from the buffer.  If value is not found to be removed
	 * it is ignored.
	 * @param values - an array containing the values to remove
	 */
	public void remove(int[] values){
		int saveWorkDone = 1;
		for(int i=0; i<values.length; i++){
			remove(values[i]);
			saveWorkDone += workDone;
		}

		workDone = saveWorkDone;
	}

	/**
	 * The purpose of this method is to find and return the
	 * position of a value.
	 * @param value - the value to find
	 * @return the index in the array where the value is found.
	 * -1 is returned if the value is not found.
	 */
	protected int search(int value){
		int pos=0;
		workDone = 1;
		while(pos<endPoint){
			if(buffer[pos] == value) return pos;
			pos++;
			workDone++;
		}
		return -1;
	}

	/**
	 * The purpose of this method is to find and return the
	 * position of a value;
	 * @param value - the value to find
	 * @return true if the value is found; false otherwise.
	 */
	public boolean find(int value){
		return (search(value) != -1);
	}

	/**
	 * The purpose of this method is to find the number of
	 * elements in the buffer
	 * @return the number of elements in the buffer
	 */
	public int size(){
		return endPoint;
	}

	/**
	 * The purpose of this method is to find the amount of work
	 * that was done by the previously called method.
	 * @return the amount of lines executed by the previous method
	 */
	public int getWorkDone(){
		return workDone;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();

		sb.append("[");
		if (endPoint >= 1){
			for(int i=0; i<endPoint-1; i++){
				sb.append(buffer[i]);
				sb.append(",");
			}
			sb.append(buffer[endPoint-1]);
		}
		sb.append("]");

		return sb.toString();
	}

	public static void main(String[] args){
		IntArrayBuffer iab = new IntArrayBuffer(2);
		iab.add(5);
		System.out.println(iab.toString());
		iab.add(6);
		iab.add(7);
		System.out.println(iab.toString());
		System.out.println(iab.remove(6));
		System.out.println(iab.toString());
		System.out.println(iab.remove(10));

		int[] array1 = new int[] {1,2,3,4,5};
		iab.add(array1);
		System.out.println(iab.toString());

		int[] array2 = new int[] {5,10,3};
		iab.remove(array2);
		System.out.println(iab.toString());
	}
}