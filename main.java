
package main;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void print(String message, int mode) {
        if (mode == 0) {
            System.out.println(message);
        } else {
            System.out.print(message);
        }
    }
    
    public static String input(String message) {
        Scanner myScannnerOBJ = new Scanner(System.in);
        print(message, 1);
        String myInput = myScannnerOBJ.nextLine();
        return myInput;
    }
    
    public static void main(String[] args) {
        HashMap<String, String> User =  new HashMap<String, String>();
        HashMap<String, Float> Pizza =  new HashMap<String, Float>();
        HashMap<String, Integer> PizzaCount =  new HashMap<String, Integer>();
        
	String[] size_name = {"Small", "Medium", "Large"};
	float[] size_cost = {(float)3.25, (float)5.5, (float)7.14};
        int Topping = 0;
        int cost = 0;
	float[] topping  = {(float)0.75, (float)1.35, (float)2, (float)2.5};
        
        boolean Discount = false;
        boolean Delivery = false;
        String name, address, phone_number;
        while (true) {
            name = input("Enter your name: ");
            if (name.length() > 1 && name.length() < 69) {
                break;
            } else {
                print("Name length need to be more than 1 and less than 69",0);
            }
        }
        
        while (true) {
            address = input("Enter your address: ");
            if (address.length() > 1 && address.length() < 69) {
                break;
            } else {
                print("Address length need to be more than 1 and less than 69",0);
            }
        }
        

        boolean NotPhoneNumber = false;
        int phone_number_check_count = 0;
        Outer:
        while (true) {
            phone_number = input("Enter your phone number: ");
            if (phone_number.length() == 11) {
                char[] phone_numberValToChar = phone_number.toCharArray();
                Inner:
                for (int i = 0; i < phone_number.length(); i++ ) { 
                    char character = phone_numberValToChar[i];
                    int icv = (int) character;
                    if (icv >= 48 && icv <= 57) {
                        phone_number_check_count++;
                    } else {
                        NotPhoneNumber = true;
                        print("Make sure its a phone number.", 0);
                        break Inner;
                    }
                }  
                if (phone_number_check_count >= 10) {
                    break Outer;
                }
            } else {
                print("Needs to be 11 characters long.", 0);
            }
  
        }
        
        User.put("Name", name);
        User.put("Address", address);
        User.put("Phone Number", phone_number);
        int quantity;
        while (true) {
            try {
                quantity  = Integer.parseInt(input("How many pizza do you want: "));
                if (quantity > 0 && quantity < 7) {
                    break;
                } else {
                    print("Try again..\n You Can Only Buy Between 1 to 6.", 0);
                }
            } catch (Exception e) {
                print("Needs to be an integer.", 0);
            }
        }
        
        
        for (int i = 0; i < quantity; i++) {
            int size = Integer.parseInt(input("Enter size number for pizza number (" + Integer.toString(i+1) + "):\n1) Small = £3.25\n2) Medium = £5.50\n3) Large = £7.15\n>>> "));
            if (size > 0 && size < 4) {
                if (Pizza.containsKey(size_name[size-1])) {
                    Pizza.put(size_name[size-1], Pizza.get(size_name[size-1]) + size_cost[size-1]);
                    PizzaCount.put(size_name[size-1], PizzaCount.get(size_name[size-1]) + 1);
                } else {
                    Pizza.put(size_name[size-1], size_cost[size-1]);
                    PizzaCount.put(size_name[size-1], 1);
                }
                cost += size_cost[size-1];
            } else {
                i--;
                print("Try again..", 0);
            }
        }
        
        OUTER:
        while (true) {
            String wantTopping = input("The current number of toppings you have is (" + Integer.toString(Topping) + "). \nDo you want toppings: Yes or No\n>>> ").toLowerCase();

            switch (wantTopping) {
                case "yes":
                    Topping++;
                    if (Topping < 4) {
                        cost += topping[Topping-1];
                    } else {
                        cost += topping[3];
                    }       break;
                case "no":
                    break OUTER;
                default:
                    print("Try again.." + wantTopping, 0);
                    break;
            }
            
        }

        String want_delivery;
        while (true) {
            Boolean stop = false;
            want_delivery = input("Want us to deliver: Yes OR No\n>>> ").toLowerCase();
            switch (want_delivery) {
                case "no":
                    stop = true;
                    break;
                case "yes":
                    stop = true;
                    Delivery = true;
                    break;
                default:
                    print("Try again..", 0);
            }
            if (stop) {
                break;
            }
        }
        String[] uList = {"Name", "Address", "Phone Number"};
        for (String i : uList) {
            print(i + ": " + User.get(i), 0);
        }
        
        
        for (String i : size_name) {
            try {
                int p1 = PizzaCount.get(i);
                float p2 = Pizza.get(i);
                print(i + " x " + Integer.toString(p1) +  " costs £" + Float.toString(p2), 0);
            } catch (Exception e) {
                continue;
            }
        }
        

        
        print("Topping: " + Integer.toString(Topping) + " toppings",0);
        if (Delivery) {
            cost += 2.5;
            print("Delivery: £" + Float.toString((float)2.5),0);
        }
        
        if (cost > 20) {
            Discount = true;
            print("Total Cost Before Discount: £" + Float.toString(cost),0);
            cost = cost - (cost/10);
        }
        print("Discount: " + Boolean.toString(Discount),0);
        print("Total cost: £" +  Float.toString(cost), 0);
    }
    
}



