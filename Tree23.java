public class Tree23 {

    private functionNode root = new functionNode(); // make root node

    public int find(long key) {
        functionNode curNode = root;
        int childNumber;
        while (true) {
            if ((childNumber = curNode.findItem(key)) != -1)
                return childNumber; // found return to childNumber
            else if (curNode.isLeaf())
                return -1; // can't find it
            else // search deeper
                curNode = getNextChild(curNode, key);
        }
    }

    public void insert(long dValue) {
        functionNode curNode = root;
        DataItem tempItem = new DataItem(dValue);

        while (!curNode.isLeaf()) {
            curNode = getNextChild(curNode, dValue);
        }

        if (curNode.isFull()) {
            split(curNode, tempItem);
        } else {
            curNode.insertItem(tempItem);
        }
    }

    public functionNode split(functionNode node, DataItem newItem) {
        int maxItems = node.ORDER - 1;
        DataItem[] arr = new DataItem[maxItems + 1];
        int j = 0;
        boolean newItemInserted = false;
        for (int i = 0; i < maxItems; i++) {
            if (node.getItem(i).dData > newItem.dData && !newItemInserted) {
                arr[j++] = newItem;
                newItemInserted = true;
            }
            arr[j++] = node.getItem(i);
        }
        if (!newItemInserted) {
            arr[maxItems] = newItem;
        }

        // remove all items from node
        int middle = maxItems / 2;
        for (int i = 0; i < maxItems; i++) {
            node.removeItem();
        }

        // bottom half of arr into node
        for (int i = 0; i < middle; i++) {
            node.insertItem(arr[i]);
        }

        // if root, create new root
        if (node.getParent() == null) {
            functionNode newRoot = new functionNode();
            root = newRoot;
            root.connectChild(0, node);
        }

        // middle DataItem into parent. if parent is full recursive call to split
        functionNode parent = node.getParent();
        int numChildren = parent.getNumItems() + 1;
        functionNode rightParent = null;
        if (parent.isFull()) {
            rightParent = split(parent, arr[middle]);
        } else {
            parent.insertItem(arr[middle]);
        }

        // top half of arr into newRight
        functionNode newRight = new functionNode();
        for (int i = middle + 1; i < arr.length; i++) {
            newRight.insertItem(arr[i]);
        }

        // create ordered array of children including newRight
        functionNode[] arrNodes = new functionNode[numChildren + 1];
        j = 0;
        for (int i = 0; i < arrNodes.length; i++) {
            arrNodes[i] = parent.disconnectChild(j++);
            if (arrNodes[i] == node) {
                arrNodes[++i] = newRight;
            }
        }

        // connect children to parent (and rightParent)
        j = 0;
        for (int i = 0; i < arrNodes.length; i++) {
            if (i <= parent.getNumItems()) {
                parent.connectChild(i, arrNodes[i]);
            } else {
                rightParent.connectChild(j++, arrNodes[i]);
            }
        }
        return newRight;
    }

    // gets appropriate child of node during search for value
    public functionNode getNextChild(functionNode theNode, long theValue) { // should be able to do this w/o a
                                                                            // loop,since we should know
        // index correct child already
        int j;
        // assume node is not empty ,not full not a leaf
        int numItems = theNode.getNumItems();
        for (j = 0; j < numItems; j++) // for each item in node
        {
            // are we less?
            if (theValue < theNode.getItem(j).dData)
                return theNode.getChild(j); // return left child
        } // end for we're greater , so
        return theNode.getChild(j); // return right child
    }

    public void displayTree() {
        recDisplayTree(root, 0, 0); // sent the value in to recDisplayTree to found the level and child
    }

    public void findDisplayTree(long value) // find location that value locate
    {
        findRecDisplayTree(root, 0, 0, value);
    }

    private void findRecDisplayTree(functionNode thisNode, int level, int childNumber, long value) {
        if (thisNode.findDisplayNode(value) == 1) // call ourselves for each child of this node
        {
            System.out.print("level=" + level + " child=" + childNumber + " ");
        }

        else {
            int numItems = thisNode.getNumItems();
            for (int j = 0; j < numItems + 1; j++) {
                functionNode nextNode = thisNode.getChild(j);
                if (nextNode != null)
                    findRecDisplayTree(nextNode, level + 1, j, value);
                else
                    return;
            }
        }
    }

    private void recDisplayTree(functionNode thisNode, int level, int childNumber) // found the value and child
    {
        System.out.print("level=" + level + " child=" + childNumber + " ");
        thisNode.displayNode();

        int numItems = thisNode.getNumItems();
        for (int j = 0; j < numItems + 1; j++) {
            functionNode nextNode = thisNode.getChild(j);
            if (nextNode != null)
                recDisplayTree(nextNode, level + 1, j);
            else
                return;
        }
    }

}
