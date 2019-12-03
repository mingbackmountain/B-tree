class functionNode {
	public static final int ORDER = 3;
	private int numItems;
	private functionNode parent;
	private functionNode childArray[] = new functionNode[ORDER];
	private DataItem itemArray[] = new DataItem[ORDER - 1];

	public void connectChild(int childNum, functionNode child) {
		childArray[childNum] = child;
		if (child != null)
			child.parent = this;
	}// to check child connect or not?

	public functionNode disconnectChild(int childNum) {
		functionNode tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}// to find which child is not connect

	public functionNode getChild(int childNum) {
		return childArray[childNum];
	}

	public functionNode getParent() {
		return parent;
	}

	public boolean isLeaf() {
		return (childArray[0] == null) ? true : false;
	}

	public int getNumItems() {
		return numItems;
	}

	public DataItem getItem(int index) {
		return itemArray[index];
	} // get DataItem at index

	public boolean isFull() {
		return (numItems == ORDER - 1) ? true : false;
	}

	public int findItem(long key) // return index of item (within node)
	{
		for (int j = 0; j < ORDER - 1; j++) { // this is loop that run to found index will break
			if (itemArray[j] == null) // at that point otherwise if not found it will return back
				break;
			else if (itemArray[j].dData == key)
				return j;
		}
		return -1;
	}

	public int insertItem(DataItem newItem) {

		numItems++; // when you want to add new number
		long newKey = newItem.dData;

		for (int j = ORDER - 2; j >= 0; j--) { // number that new insert will start on the right side
			if (itemArray[j] == null) // examine items
				continue; // go left one cell
			else { // if not null value
				long itsKey = itemArray[j].dData; // get its key
				if (newKey < itsKey) // if it's bigger
					itemArray[j + 1] = itemArray[j]; // shift it right
				else {
					itemArray[j + 1] = newItem; // insert new item
					return j + 1; // return index to new item
				}
			}
		}
		itemArray[0] = newItem; // insert new item
		return 0;
	}

	public DataItem removeItem() { // remove largest item

		DataItem temp = itemArray[numItems - 1]; // save item
		itemArray[numItems - 1] = null; // disconnect it
		numItems--; // one less item
		return temp; // return item
	}

	public void displayNode() { // format"39/42/71"
		for (int j = 0; j < numItems; j++)
			itemArray[j].displayItem(); // "/42"
		System.out.println("/"); // final"/s"
	}

	public int findDisplayNode(long value) // keep the value from findRecDisplayTree in class Tree234
	{
		int a = 0;
		for (int j = 0; j < numItems; j++) {
			a = itemArray[j].findDisplayItem(value); // sent the value in class DataItem
			if (a == 1)
				break; // if it true (a=1) sent the value back to findRecDisplayTree in Tree234
		}
		return a;
	}
}