public class Tree234 {
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

      while (true) {
         if (curNode.isFull()) // check node or not full
         {
            split(curNode); // split it
            curNode = curNode.getParent();// this is back up cur.Node

            curNode = getNextChild(curNode, dValue);
         } // end if(node is full)

         else if (curNode.isLeaf()) // if node is leaf
            break; // break and go to insert
         // node is not full,not a leaf; so to lower level
         else
            curNode = getNextChild(curNode, dValue);
      }

      curNode.insertItem(tempItem); // insert new of DataItem
   }

   public void split(functionNode thisNode) {

      DataItem itemB, itemC;
      functionNode parent, child2, child3;
      int itemIndex;

      itemC = thisNode.removeItem(); // remove items from node
      itemB = thisNode.removeItem(); // this node
      child2 = thisNode.disconnectChild(2); // remove child from
      child3 = thisNode.disconnectChild(3); // this node

      functionNode newRight = new functionNode(); // make new node

      if (thisNode == root) // if this is the root
      {
         root = new functionNode(); // new root
         parent = root; // root is our parent
         root.connectChild(0, thisNode); // connect to parent
      } else // this node not the root
         parent = thisNode.getParent(); // get parent
      // deal with parent
      itemIndex = parent.insertItem(itemB); // item B to parent
      int n = parent.getNumItems(); // total items is what

      for (int j = n - 1; j > itemIndex; j--) // move parent's
      { // connections
         functionNode temp = parent.disconnectChild(j);// one child
         parent.connectChild(j + 1, temp); // to the right
      } // connect newRight to parent

      parent.connectChild(itemIndex + 1, newRight);

      // deal with newRight
      newRight.insertItem(itemC); // item C to newRight
      newRight.connectChild(0, child2); // connect to 0 and 1 on new right
      newRight.connectChild(1, child3);
   }

   // gets appropriate child of node during search for value
   public functionNode getNextChild(functionNode theNode, long theValue) { // should be able to do this w/o a loop,since
                                                                           // we should know
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