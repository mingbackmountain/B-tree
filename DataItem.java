class DataItem {
	public long dData; // the value that we insert

	public DataItem(long dd) {
		dData = dd;
	}//

	public void displayItem() {
		System.out.print("/" + dData);
	}//display item, format"/30"by slash after number

	public int findDisplayItem(long value) // receive value from findDisplayNode in Node
	{
		int a = 0;
		if (value == dData) // know data from dData
		{
			a = 1; // if a=1 sent it back to class Node
		}
		return a;
	}
}