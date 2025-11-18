import dataStructures.ArrayLinearList;
import java.util.Scanner;

public class MyArrayLinearList extends ArrayLinearList {
    public  MyArrayLinearList(int initialCapacity){
        super(initialCapacity);
    }
    public  MyArrayLinearList(){
        super(10);
    }
    public  MyArrayLinearList(MyArrayLinearList mylist){
        super(mylist.size);
        for(int i = 0; i < mylist.size(); i++)
        this.add(i, mylist.element[i]);
    }
    public  MyArrayLinearList reversed(){
        MyArrayLinearList temp = new MyArrayLinearList(this.size());
        int j=0;
        for(int i = this.size() -1; i >=0; i--)
            temp.add(j++, this.element[i]);
            return temp;
    }
    public int Max(){
        int m = (int)this.get(0);
        for(int i = 0; i < this.size(); i++)
            if(m<(int)this.get(i))
                m = (int)this.get(i);
                return m;
    }
    public int Min(){
        int n = (int)this.get(0);
        for(int i = 0; i < this.size(); i++)
            if(n>(int)this.get(i))
                n = (int)this.get(i);
                return n;
    }
    public int Sum(){
        int sum = 0;
        for (int i = 0; i < this.size(); i++) {
            sum += (int) this.get(i);
        }
        return sum;
    }
       
    public double Average(){
        int sum = Sum();
        return (double) sum / this.size();
    }
    public int RemoveOdd() {
        int ro = 0;
        for (int i = 0; i < this.size(); i++) {
            if (((int) this.get(i)) % 2 != 0) {
                this.remove(i);
                i--;
                ro++;
            } 
        }
        return ro;
    }
    public void replace(int index, Object element) {
    this.remove(index);
    this.add(index, element);
    }

    public void Sort() {
    for (int i=0; i<this.size(); i++){
        for (int j=i+1; j<this.size(); j++){
            if((int) this.get(i) > (int) this.get(j)){
                int sr = (int) this.get(i);
                replace(i,this.get(j));
                replace(j, sr);
            }
        }
    }
           
}

    public static void main(String[] args) {
        MyArrayLinearList l1 = new MyArrayLinearList();
        l1.add(0, Integer.valueOf(1));
        l1.add(0, Integer.valueOf(3));
        l1.add(0, Integer.valueOf(5));
        l1.add(0, Integer.valueOf(5));
        l1.add(0, Integer.valueOf(8));
        l1.add(0, Integer.valueOf(6));
        l1.add(0, Integer.valueOf(7));

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Print list");
            System.out.println("2. Find maximum");
            System.out.println("3. Find minimum");
            System.out.println("4. Summarize");
            System.out.println("5. Calculate average");
            System.out.println("6. Remove odd numbers");
            System.out.println("7. Sort list");
            System.out.println("8. Exit");

            int a = scanner.nextInt();

            switch (a) {
                case 1:
                    System.out.println("l1: " + l1.toString());
                    break;
                case 2:
                    System.out.println("maximum: " + l1.Max());
                    break;
                case 3:
                    System.out.println("minimum: " + l1.Min());
                    break;
                case 4:
                    System.out.println("Summarize: " + l1.Sum());
                    break;
                case 5:
                    System.out.println("Average: " + l1.Average());
                    break;
                case 6:
                    System.out.println("Removed numbers " + l1.RemoveOdd());
                    break;
                case 7:
                    l1.Sort();
                    System.out.println("Sort: " + l1.toString());
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");

            }
        }
    }
}