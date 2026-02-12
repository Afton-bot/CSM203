import dataStructures.Chain;
import java.util.ArrayList;

public class MyChain extends Chain {
    private ArrayList<Object> elements; 
    public MyChain() {
        elements = new ArrayList<>();
    }
    public int size() {
        return elements.size();
    }
    public Object get(int index) {
        return elements.get(index);
    }
    public void add(int index, Object element) {
        elements.add(index, element);
    }
    public int indexOf(Object element) {
        return elements.indexOf(element);
    }
    public Object[] toArray() {
        Object[] x = new Object[this.size()];
        for (int i = 0; i < this.size(); i++) {
            x[i] = this.get(i);
        }
        return x;
    }
    public void addRange(Object[] elements) {
        for (int i = 0; i < elements.length; i++) {
            this.add(this.size(), elements[i]);
        }
    }
    public MyChain union(MyChain chain) {
        MyChain unionChain = new MyChain();
        for (int i = 0; i < size(); i++) {
            unionChain.add(unionChain.size(), get(i));
        }
        for (int i = 0; i < chain.size(); i++) {
            if (unionChain.indexOf(chain.get(i)) == -1) {
                unionChain.add(unionChain.size(), chain.get(i));
            }
        }
        return unionChain;
    }
    public MyChain intersection(MyChain chain) {
        MyChain intersectChain = new MyChain();
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < chain.size(); j++) {
                if (this.get(i).equals(chain.get(j))) {
                    intersectChain.add(intersectChain.size(), this.get(i));
                    break;
                }
            }
        }
        return intersectChain;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < this.size(); i++) {
            sb.append(this.get(i));
            if (i < this.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    public static void main(String[] args) {
        MyChain chain1 = new MyChain();
        chain1.add(0, 1);
        chain1.add(1, 2);
        chain1.add(2, 3);

        MyChain chain2 = new MyChain();
        chain2.add(0, 2);
        chain2.add(1, 3);
        chain2.add(2, 4);

        MyChain unionChain = chain1.union(chain2);
        System.out.println("Data 1:" + chain1);
        System.out.println("Data 2:" + chain2);
        System.out.println("Union: " + unionChain);

        MyChain intersectChain = chain1.intersection(chain2);
        System.out.println("Intersection: " + intersectChain);
    }
}