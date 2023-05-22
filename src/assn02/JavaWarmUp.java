
package assn02;

import java.util.ArrayList;
import java.util.Scanner;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class JavaWarmUp{
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static int phoneQuantity = 0;
    static int laptopQuantity = 0;
    static int smart_watchQuantity = 0;

    static double sumPhoneFee = 0.0;

    static double sumLaptopFee = 0.0;
    static double sumWatchFee = 0.0;

    static double phoneTime = 0.0;
    static double laptopTime = 0.0;
    static double watchTime = 0.0;

    static double costPhones = 0.0;
    static double costLaptops = 0.0;
    static double costWatches = 0.0;

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int numItems = scan.nextInt();

        ArrayList<Item> data = new ArrayList<Item>();
        ArrayList<String> information = new ArrayList<String>();

        for (int i = 0; i <= numItems; i++){
            information.add(scan.nextLine());
        }
        information.remove(0); // done to remove the empty value

        for (int i = 0; i < information.size(); i++){
            String[] result = information.get(i).split(" ");
            String inDate = result[0];
            String inTime = result[1];
            String inName = result[2];
            double inFee = parseDouble(result[3]);
            int inQuantity = parseInt(result[4]);
            double inTot = parseDouble(result[5]);
            int inCost = parseInt(result[6]);
            Item newObject = new Item(inDate, inTime, inName, inFee, inQuantity, inTot, inCost);
            data.add(newObject);
        }
        aggregate(data);
        System.out.println(printHAF(data));
        System.out.println(printLAF(data));
        System.out.println(reportPhone());
        System.out.println(reportLaptop());
        System.out.println(reportWatch());



    }


    public static void aggregate(ArrayList<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i)._name.equals("phone")) {
                phoneQuantity += items.get(i).getQuantity();
                sumPhoneFee += (items.get(i).getFee() * items.get(i).getQuantity());
                costPhones += items.get(i).getCost();
                phoneTime += items.get(i).getTotTime();
            } else if (items.get(i)._name.equals("laptop")) {
                laptopQuantity += items.get(i).getQuantity();
                sumLaptopFee += (items.get(i).getFee() * items.get(i).getQuantity());
                costLaptops += items.get(i).getCost();
                laptopTime += items.get(i).getTotTime();
            } else if (items.get(i)._name.equals("smart_watch")) {
                smart_watchQuantity += items.get(i).getQuantity();
                sumWatchFee += (items.get(i).getFee() * items.get(i).getQuantity());
                costWatches += items.get(i).getCost();
                watchTime += items.get(i).getTotTime();
            }
        }
    }
    public static int getMaxPriceIndex(ArrayList<Item> items){
        int index = 0;

        for (int i = 0; i < items.size(); i++){
            if (items.get(i)._fee > items.get(index)._fee){
                index = i;
            }
            if (items.get(i)._fee == items.get(index)._fee){
                index = i;
            }
        }
        return index;
    }
    public static int getMinPriceIndex(ArrayList<Item> items){
        int index = 0;

        for (int i = 0; i < items.size(); i++){
            if (items.get(i)._fee < items.get(index)._fee){
                index = i;
            }
            if (items.get(i)._fee == items.get(index)._fee){
                index = i;
            }
        }
        return index;
    }

    public static String printHAF(ArrayList<Item> list){
        int index = 0;
        index = getMaxPriceIndex(list);
        return "Highest per unit assembling fee:" + "\n" +
                "\t" + "When: " + list.get(index)._date + " " + list.get(index)._time + "\n" +
                "\t" + "Category: " + list.get(index)._name + "\n" +
                "\t" + "Price: " + list.get(index)._fee;

    }
    public static String printLAF(ArrayList<Item> list){
        int index = 0;
        index = getMinPriceIndex(list);
        return "Lowest per unit assembling fee:" + "\n" +
                "\t" + "When: " + list.get(index)._date + " " + list.get(index)._time + "\n" +
                "\t" + "Category: " + list.get(index)._name + "\n" +
                "\t" + "Price: " + list.get(index)._fee;

    }



    public static double avgAssemFee(double value, int count){
        return Math.round((value / count) * 100) / 100.00;
    }
    public static double avgNetProfit(double charged, double time, double cost, int quantity){
    double netProfit = ((charged - cost - (time * 16)) / quantity);
    return Math.round(netProfit * 100) / 100.00;

    }

    public static String reportPhone(){
        return "Statistic of phone" + "\n" +
                "\t" + "Quantity: " + phoneQuantity + "\n" +
                "\t"+ "Average Assembling fee: " + String.format("%.2f",avgAssemFee(sumPhoneFee,phoneQuantity)) + "\n" +
                "\t" + "Average Net Profit: " + avgNetProfit(sumPhoneFee, phoneTime, costPhones, phoneQuantity);
    }
    public static String reportLaptop(){
        return "Statistic of laptop" + "\n" +
                "\t" + "Quantity: " + laptopQuantity + "\n" +
                "\t"+ "Average Assembling fee: " + avgAssemFee(sumLaptopFee,laptopQuantity) + "\n" +
                "\t" + "Average Net Profit: " + avgNetProfit(sumLaptopFee, laptopTime, costLaptops, laptopQuantity);
    }
    public static String reportWatch(){
        return "Statistic of smart_watch" + "\n" +
                "\t" + "Quantity: " + smart_watchQuantity + "\n" +
                "\t"+ "Average Assembling fee: " + String.format("%.2f", avgAssemFee(sumWatchFee,smart_watchQuantity)) + "\n" +
                "\t" + "Average Net Profit: " + avgNetProfit(sumWatchFee, watchTime, costWatches, smart_watchQuantity);
    }
    public static class Item{
        private String _date;
        private String _time;
        private String _name;
        private double _fee;
        private int _quantity;
        private double _tot_time;
        private int _cost;

        // constructor
        public Item(String inDate, String inTime, String inName, double inFee, int inQuantity, double inTotTime, int inCost){
            setDate(inDate);
            setTime(inTime);
            setName(inName);
            setFee(inFee);
            setQuantity(inQuantity);
            setTotTime(inTotTime);
            setCost(inCost);
        }
        public String toString(){
            return "Item: " + _name + "\n" + "Date: " + _date + "\n"
                    + "Time assembled: " + _time + "\n"
                    + "Fee: " + _fee + "\n"
                    + "Quantity: " + _quantity + "\n"
                    + "Total time to assemble: " + _tot_time + "\n"
                    + "Cost: " + _cost;
        }

        public void setDate(String inDate){
            _date = inDate;
        }
        public void setTime(String inTime){
            _time = inTime;
        }
        public void setName(String inName){
            _name = inName;
        }
        public void setFee(double inFee){
            _fee = inFee;
        }
        public void setQuantity(int inQuantity){
            _quantity = inQuantity;
        }
        public void setTotTime(double inTotTime){
            _tot_time = inTotTime;
        }
        public void setCost(int inCost){
            _cost = inCost;
        }
        // getters
        public String getDate(){
            return _date;
        }
        public String getTime(){
            return _time;
        }
        public String getName(){
            return _name;
        }
        public double getFee(){
            return _fee;
        }
        public int getQuantity(){
            return _quantity;
        }
        public double getTotTime(){
            return _tot_time;
        }
        public int getCost(){
            return _cost;
        }

    }

}
