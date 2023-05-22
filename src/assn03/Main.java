package assn03;

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList<Integer>();
        list.add(1);
        list.add(3);
        list.add(5);


        LinkedList list2 = new LinkedList();
        list2.add(2);
        list2.add(4);
        list2.add(6);


        System.out.println(list);
        System.out.println(list2);
        list.merge(list2);
        System.out.println(list);
    }
}
