
public class SortedIntArrayBuffer extends IntArrayBuffer {

	public void add(int value) {
		
		if (endPoint >= buffer.length){				//is the buffer full?
			//resize the array
			int[] temp = new int[buffer.length * 2];	//create a new buffer
			//transfer the old data into the new array
			for(int i=0; i < buffer.length; i++){			//transfer data over
				temp[i] = buffer[i];
				workDone++;
			}
			buffer = temp;							//set old to be new
		}
		
		int pointToInsert = 0;
		for (int i = 0; i < endPoint; i++) { //check where to insert new value
			if (value > buffer[i]) {
			pointToInsert = i + 1;
			}
		}
		for (int i = endPoint; i > pointToInsert; i--) { //move existing values to the right until but not including pointToInsert
			buffer[i] = buffer[i - 1];
		}
		buffer[pointToInsert] = value; //place new value into correct spot
		
		endPoint++;
		
		/*int[] temp = new int[buffer.length]; //original solution
		for (int i = 0; i <= endPoint; i++) {
			if (i < pointToInsert)
				temp[i] = buffer[i];
			else if (i == pointToInsert)
				temp[i] = value;
			else if (i > pointToInsert)
				temp[i] = buffer[i - 1];	
		}
		buffer = temp;
		*/
	}
	
	public void add(int[] values) {
		/*for (int intToAdd : values) {          //original solution
			this.add(intToAdd);
		}*/
		
		
		//below ensures that values is in order. Not necessary if user inputs values in order, but nice to have
		boolean isSorted = true; 
		for (int i = 0; i < values.length - 1; i++) {  //check to see if it's sorted
			if (values[i] > values [i + 1]) {
				isSorted = false;
				break;
			}
		}
		if (!isSorted) {
			int[] tempValues = new int[values.length];
			int valuesEndPoint = 0;
			for (int k = 0; k < values.length; k++) {
				int pointToInsert = 0;
				for (int i = 0; i < valuesEndPoint; i++) { //check where to insert new value
					if (values[k] > tempValues[i]) {
					pointToInsert = i + 1;
					}
				}
				
				for (int i = valuesEndPoint; i > pointToInsert; i--) { //move existing values to the right until but not including pointToInsert
					tempValues[i] = tempValues[i - 1];
				}
				tempValues[pointToInsert] = values[k]; //place new value into correct spot
				valuesEndPoint++;
			}
			values = tempValues;
		}
		//above: ensures values is in order
		
		int[] sorted = new int[values.length + endPoint];
		int[] tempBuffer = buffer;
		int i, j, k;
		i = j = k = 0;
		int tempEndPoint = endPoint;
		
		while (i < values.length && j < tempEndPoint) { //compare and add to sorted
			if (values[i] < tempBuffer[j]) {
				sorted[k] = values[i];
				k++;
				i++;
			}
			//else if (values[i] > tempBuffer[j]) {
			else {
				sorted[k] = tempBuffer[j];
				k++;
				j++;
			}
		/*	else if (values[i] == tempBuffer[j]) {
				sorted[k] = values[i];
				k++;
				i++;
				sorted[k] = tempBuffer[j];
				k++;
				j++;
			}
		*/
		}
		
		
		//remaining values
		if (i == values.length) {
			while (j < tempEndPoint) {
				sorted[k] = tempBuffer[j++];
				k++;
			}
		}
		else if (j == endPoint) {
			while (i < values.length) {
				sorted[k] = values[i++];
				k++;
			}
		}
		endPoint += values.length;
		buffer = sorted;
	}
	
	public static void main(String[] args) {
		SortedIntArrayBuffer sab = new SortedIntArrayBuffer();
		int[] arr = new int[]{3,8,6};
		sab.add(1);
		sab.add(4);
		sab.add(2);

		sab.add(arr);
		System.out.println(sab.toString());
		System.out.println(sab.endPoint);
	}
}
