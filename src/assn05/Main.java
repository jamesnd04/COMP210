package assn05;


public class Main {

    public static void main(String[] args) {
        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working

        SimpleEmergencyRoom Test1 = new SimpleEmergencyRoom();
        Test1.addPatient(2,4);
        Test1.addPatient(3,5);
        Test1.addPatient(5,10);
        System.out.println(Test1.dequeue());
        System.out.println(Test1.getPatients().get(2));

        */



       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */

        MaxBinHeapER Test2 = new MaxBinHeapER();
        Test2.enqueue(1,45);
        Test2.enqueue(10,23);
        Test2.enqueue(40,39);
        Test2.enqueue(50,9);
        Test2.enqueue(60,15);
        Test2.enqueue(90,53);
        Test2.enqueue(80,52);
        System.out.println(Test2.getMax());




        //Part 3

        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }

        double[] check = compareRuntimes();
        for(int i = 0; i < check.length; i++){
            System.out.println(i + ": " + check[i]);
        }

    }

    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }
    
    public static double[] compareRuntimes() {
    	// Array which you will populate as part of Part 4
    	double[] results = new double[4];
    	
        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);
        for(int i = 0; i < 100000; i++) {
            simplePQ.dequeue();
        }
        results[0] = System.nanoTime();
        results[1] = System.nanoTime()/ 100000;
        // Code for (1) Here


        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);
        for(int i = 0; i < 100000; i++) {
            binHeap.dequeue();
        }
        results[2] = System.nanoTime();
        results[3] = System.nanoTime() / 100000;

        // Code for (2) Here

        return results;
    }



}



