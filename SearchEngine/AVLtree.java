public class AVLtree
{
    AVLNode root = new AVLNode();
    public AVLtree()
    {
        root = new AVLNode();
    }

    public AVLtree(AVLNode x)
    {
                this.root = x;
    }
    public AVLtree(Position p)
    {
        AVLNode node = new AVLNode();
        node.position_entry = p;
        node.left = null;
        node.right = null;
        node.parent = null;
        node.height = 0;
       this.root = node;
    }

public int isEmpty()
{
    if(root ==  null)
    {
        return 1;
    }
    else
    {
        return 0;
    }

}

public int max(int a, int b)
{
    if(a>b)
    {
        return a;
    }
    return b;
}
   
public int contains(int index)
{
  //  System.out.println("clls for index "+index);
    if (isEmpty()==1)
    {
       return 0;
    } 
   else {
       if (root.position_entry.newWordIndex == index) {
           return 1;
       } 
       else if (index < root.position_entry.newWordIndex) {
           if(root.left == null)
           {
            return 0;
           }
           AVLtree x = new AVLtree(root.left);
           return x.contains(index);
       } 
       else {
        if(root.right == null)
        { 
         return 0;
        } 
          
           AVLtree x = new AVLtree(root.right);
           return x.contains(index);
       }
   }
   
}

public int isElement(Position p)
{
    return this.contains(p.newWordIndex);
}

int getBalance(AVLNode N) { 
    if (N == null) 
        return 0; 
        if(N.left==null)
        {
            if(N.right == null)
            {
                return 0;
            }
            else
            {
                return 0-N.right.height;
            }
        }
        else
        {
            if(N.right!=null)
            {
                return N.left.height - N.right.height;
            }
            else
            {
                return N.left.height;
            }
        }

   
} 


public void insert(Position x)
{
    if(root == null)
    {  root = new AVLNode();
        root.position_entry=x;
    }
    else{
        if(x.newWordIndex > root.position_entry.newWordIndex)
        {
            if(root.right == null)
            {  AVLtree ppp = new AVLtree(x);

                root.right = ppp.root;
            }
            else
            {   AVLtree nnn = new AVLtree(root.right);
                nnn.insert(x);
            }
            //System.out.println("Yaha");
            if(this.getBalance(root)<-1 || this.getBalance(root)>1)
            {   System.out.println("Yaha");
                rotate(root,x);
            }
        }
        else
        {
            if(root.left == null)
            {   AVLtree ppp =  new AVLtree(x);
                root.left = ppp.root;
            }
            else
            {    AVLtree rr = new AVLtree(root.left);
                rr.insert(x);
            }
            if(this.getBalance(root)<-1 || this.getBalance(root)>1)
            {  System.out.println("Yaha");
                rotate(root,x);
            }

        }
    }
}

public void rotate(AVLNode node, Position p)
{ 
    if(node.left!=null && node.left.contains(p.newWordIndex)!=0)
    {
        if(node.left.left!=null && node.left.left.contains(p.newWordIndex)!=0)
        {
            node = right_rotate(node);
        }
        if(node.right != null && node.left.right.contains(p.newWordIndex)!=0)
        {
             node.left = left_rotate(node.left);
             node = right_rotate(node);
        }
    }
    if(node.right!=null && node.right.contains(p.newWordIndex)!=0)
    {
        if(node.right.right!=null && node.right.right.contains(p.newWordIndex)!=0)
        {
            node = left_rotate(node);
        }
        if(node.left!= null && node.right.left.contains(p.newWordIndex)!=0)
        {
             node.right = right_rotate(node.right);
             node = left_rotate(node);
        }
    }


}

AVLNode left_rotate(AVLNode node)
{
    AVLNode lch = node.left;
         node.left = lch.right;
        lch.right = node;
        node.height = node.getHeight();
       lch.height = lch.getHeight();
        return lch;
}


AVLNode right_rotate(AVLNode node)
{
    AVLNode rch = node.right;
         node.right = rch.left;
        rch.left = node;
        node.height = node.getHeight();
       rch.height = rch.getHeight();
        return rch;
}

Position get(int i)
{
  MySet<Position> set = new MySet<Position>();
  set = inorder(this.root);
  Node<Position> curr = new Node<Position>();
  curr = set.ll.start;
 // i++;
  while(i-- != 0)
  {
     curr = curr.next;
  }

  return curr.data;
}

MySet<Position> inorder(AVLNode node)
{
  MySet<Position> set  = new MySet<Position>();
  if(node!=null)
  {
  if(node.left!=null)
  {
    MySet<Position> set2  = new MySet<Position>();
    set2=inorder(node.left);
    Node<Position> curr = new Node<Position>();
    curr = set2.ll.start;
    while(curr!= null)
    {
        set.addElement(curr.data);
        curr = curr.next;
    }

  }
  
  set.addElement(node.position_entry);
  if(node.right!=null)
  {
    MySet<Position> set3  = new MySet<Position>();
    set3=inorder(node.right);
    Node<Position> curr = new Node<Position>();
    curr = set3.ll.start;
    while(curr!= null)
    {
        set.addElement(curr.data);
        curr = curr.next;
    }
  }
  return set;

  }

  return set;
}



}