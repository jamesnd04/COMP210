package assn07;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();
        boolean run = true;
        boolean getP = true;
        boolean validString = false;

        ArrayList<String> valid = new ArrayList<>();

        valid.add("New password");
        valid.add("Get password");
        valid.add("Delete account");
        valid.add("Check duplicate password");
        valid.add("Get accounts");
        valid.add("Generate random password");
        valid.add("Exit");

        String checking;
        System.out.println("Enter Master Password");
        checking = scanner.nextLine();
        while(getP) {
            if (passwordManager.checkMasterPassword(checking)) {
                getP = false;
            } else {
                System.out.println("Enter Master Password");
                checking = scanner.nextLine();
            }
        }
        while (run){
            validString = false;
            checking = scanner.nextLine();
            for (int i = 0; i < valid.size(); i++){
                if (checking.equals(valid.get(i))){
                    validString = true;
                }
            }
            while (!validString){
                System.out.println("Command not found");
                checking = scanner.nextLine();
                for (int i = 0; i < valid.size(); i++){
                    if (checking.equals(valid.get(i))){
                        validString = true;
                    }
                }
            }
            if(checking.equals("Exit")){run = false;}

            if(checking.equals("New password")){

                String x = scanner.nextLine();
                String y = scanner.nextLine();

                passwordManager.put(x,y);
                System.out.println("New password added");

            }
            if(checking.equals("Get password")){
                String x = scanner.nextLine();
                if(passwordManager.get(x) == null){
                    System.out.println("Account does not exist");
                }
                else{
                    System.out.println(passwordManager.get(x));
                }
            }

            if(checking.equals("Delete account")){
                String x = scanner.nextLine();
                if(passwordManager.remove(x) == null){
                    System.out.println("Account does not exist");
                }
                else{
                    System.out.println("Account deleted");
                }
            }
            if(checking.equals("Check duplicate password")){
                String x = scanner.nextLine();
                if(passwordManager.checkDuplicate(x).size() == 0){
                    System.out.println("No account uses that password");
                }
                else{
                    System.out.println("Websites using that password:");
                    for(int i = 0; i < passwordManager.checkDuplicate(x).size(); i++){
                    System.out.println(passwordManager.checkDuplicate(x).get(i));}

                }
            }

            if(checking.equals("Get accounts")){
                System.out.println("Your accounts:");
                String[] array = passwordManager.keySet().toArray(new String[passwordManager.keySet().size()]);
                for(int i = 0; i < passwordManager.keySet().size(); i++){
                    System.out.println(array[i]);
                }
            }

            if(checking.equals("Generate random password")){


                int rand = scanner.nextInt();
                System.out.println(passwordManager.generateRandomPassword(rand));

            }



        }
    }
}
