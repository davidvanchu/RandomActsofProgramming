import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.TreeSet;

public class HuffmanCompressor {
	String inputData = "";
	String compressedData = "";
	//char[] letters;
	BinaryTreeNode<Character> freeNodes;
	
	Hashtable<Character, Integer> frequencyTable = new Hashtable<Character, Integer>();
	Hashtable<Character, String> encodingTable;
	Hashtable<String, Byte> encodingBitMap = new Hashtable<String, Byte>();

	ArrayList<Byte> binaryCompressedData = new ArrayList<Byte>();
	
	TreeSet <HuffTree> tree = new TreeSet<HuffTree>();
	StringBuffer code = new StringBuffer();
	
	
	public HuffmanCompressor(){
		//reads the file in and converts it into a String
		/*
		File file = new File("resources/gettys.txt");
		FileReader reader = new FileReader(file);
		Scanner scanner = new Scanner(reader);
		while (scanner.hasNextLine()) {
			inputData = inputData + (scanner.nextLine());
		}
		scanner.close();
		*/
		//inputData = "Hello World";
		
		//letters = inputData.toCharArray(); //convert resulting String into an array of chars
	}

	public ArrayList<Byte> compress(String inputData, Hashtable<Character, String> encodingTable) {
		this.inputData = inputData;
		this.encodingTable = encodingTable;
		createFrequencyTable();
		createLeaves();
		createHuffmanTree();
		createBitCodes(tree.first());
		encodeToString();
		buildEncodingMap();
		encodeStringtoBits();
		return binaryCompressedData;
	}
	private void buildEncodingMap() {
		for (int i = 0; i <= 255; i++) {
			StringBuffer temp = new StringBuffer();
			if ((i & 128) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 64) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 32) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 16) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 8) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 4) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 2) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 1) > 0)
				temp.append("1");
			else
				temp.append("0");
			encodingBitMap.put(temp.toString(), (byte)i);
		}
	}

	private void encodeStringtoBits() {
		
		
		//CHECK COMPRESSEDDATA VS STRINGENCODEDDATA
		
		int remainder = compressedData.length() % 8;
		for (int i = 0; i < (8 - remainder); i++) {
			compressedData += "0";
		}
		for (int i = 0; i < compressedData.length(); i += 8) {
			String bits = compressedData.substring(i, i + 8);
			byte bitsToAdd = encodingBitMap.get(bits);
			binaryCompressedData.add(bitsToAdd);
		}
	}

	private void encodeToString() {
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < inputData.length(); i++) {
			temp.append(encodingTable.get(inputData.charAt(i)));
		}
		compressedData = temp.toString();
	}

	private void createBitCodes(HuffTree tree) {
		if (tree instanceof HuffmanNode) {
			//HuffmanNode node = (HuffmanNode)tree;
			HuffTree left = ((HuffmanNode) tree).getLeft();
			HuffTree right = ((HuffmanNode) tree).getRight();
			
			code.append("0");
			createBitCodes(left);
			
			code.deleteCharAt(code.length() - 1);
			
			code.append("1");
			createBitCodes(right);
			
			code.deleteCharAt(code.length() - 1);
		}
		else {
			HuffLeaf leaf = (HuffLeaf)tree;
			encodingTable.put((char)leaf.getValue(), code.toString());
		}
	}

	private void createHuffmanTree() {
		while (tree.size() > 1) {
			HuffTree left = tree.first();
			tree.remove(left);
			
			HuffTree right = tree.first();
			tree.remove(right);

			HuffmanNode temp = new HuffmanNode(left.getFrequency() + right.getFrequency(), left, right);
			tree.add(temp);
		}
		
	}

	/**
	 * creates leaf nodes which are then added to the main tree
	 */
	private void createLeaves() {
		Enumeration<Character> enumerator = frequencyTable.keys();
		
		while (enumerator.hasMoreElements()) {
			Character nextKey = enumerator.nextElement();
			tree.add(new HuffLeaf(nextKey, frequencyTable.get(nextKey)));
		}
	}

	public void createFrequencyTable() {
		
		for (int i = 0; i < inputData.length(); i++) {
			char key = inputData.charAt(i);
			if (frequencyTable.contains(key)) {
				int newValue = frequencyTable.get(key);
				newValue++;
				frequencyTable.put(key, newValue);
			}
			else
				frequencyTable.put(key, 1);
		}
		
		/*for (char c : letters) {
			if (!frequencyTable.containsKey(c)) {
				frequencyTable.put(c, 1);
			}
			else {
				frequencyTable.put(c, frequencyTable.get(c) + 1);
			}
		}
		*/
	}
}
