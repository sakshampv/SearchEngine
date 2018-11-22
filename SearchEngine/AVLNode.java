public class AVLNode
{
    Position position_entry = new Position();
    AVLNode left = null;
    AVLNode right = null;
    int height = 0;
    AVLNode parent = null;

    public AVLNode()
    {
       // left = new AVLNode();
        left = null;
       // right = new AVLNode();
        right = null;
       // parent = new AVLNode();
        parent = null;
        position_entry = new Position();
    }

    public AVLNode(Position key)
    {

       // left = new AVLNode();
        left = null;
      //  right = new AVLNode();
        right = null;
     //   parent = new AVLNode();
        parent = null;
        position_entry = new Position();
        position_entry = key;
      
    }

    public int getHeight()
    {   AVLNode node = this;
        if(node==null)
        {
            return -1;
        }
        if(node.left == null)
        {
            if(node.right == null)
            {
                return 0;
            }
            else
            {
                return node.right.height +1;
            }
        }
        else
        {
            if(node.right ==null)
            {
                return node.left.height +1;
            }
            else
            {
                if(node.right.height>node.left.height)
                {
                    return node.right.height+1;
                }
                else
                {
                    return node.left.height+1;
                }
            }
        }
    }


    int contains(int p)
    {
        AVLtree tree = new AVLtree(this);
        return tree.contains(p);
    }
}