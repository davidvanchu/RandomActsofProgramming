
abstract class HuffTree implements Comparable<Object>{
	int frequency;

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public int compareTo(Object obj){
	    HuffTree theTree = (HuffTree)obj;
	    if (frequency == theTree.frequency){
	      //The objects are in a tie based on the frequency
	      // value.  Return a tiebreaker value based on the
	      // relative hashCode values of the two objects.
	      return (hashCode() - theTree.hashCode());
	    }else{
	      //Return negative or positive as this frequency is
	      // less than or greater than the frequency value of
	      // the object referred to by the parameter.
	      return frequency - theTree.frequency;
	    }
	  }
}
