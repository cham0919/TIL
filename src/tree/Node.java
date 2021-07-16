package tree;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private Node parent;
    private ArrayList<Node> childList;
    private boolean isIfRoot;
    private String data;

    public Node(String data, boolean isIfRoot){
        parent = null;
        childList = new ArrayList<Node>();
        this.data = data;
        this.isIfRoot = isIfRoot; // root 노드 여부
    }

    // 부모 노드와 연결해주는 메서드
    public void addParent(Node parent){
        if(this.parent!=null){
            this.parent=null;
        }
        this.parent=parent;
    }

    // 자식 노드와 연결해주는 메서드
    public Node addChild(Node child){
        this.childList.add(child);
        child.addParent(this);
        return child;
    }

    // 해당 노드의 값을 return
    public String getData(){ return this.data; }

    // 부모 노드의 값을 return
    public Node getParentTree(){ return this.parent; }

    // 자식 노드의 값을 return
    public List<Node> getChildTree() { return this.childList; }

    public boolean isIfRoot() {
        return isIfRoot;
    }

}
