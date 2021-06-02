
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

//Moci Xu, 18043870,
// Hao Zhou, 19046061,
// Assignment 2, 159.234
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Info(); //show ours details
        Scanner scanner = null;
        scanner = new Scanner(new BufferedReader(new FileReader("library.txt")));
        String read_line;
        if(!scanner.hasNextLine()) {
            System.out.println("Can't find the File!!!");
            System.exit(0);
        }
        //create objects of Books, Journals, and Movies in ArrayList
        ArrayList<Books> Books_list = new ArrayList<Books>();
        ArrayList<Journals> Journals_list = new ArrayList<Journals>();
        ArrayList<Movies> Movies_list = new ArrayList<Movies>();
        ArrayList<Details>Details_list = new ArrayList<Details>();
        try {
            while (scanner.hasNextLine()) {
                //read lines from the library.txt
                String line;
                line = scanner.nextLine();
                //split lines in different pars
                int i;
                String[] temp = line.split(",");
                //System.out.println(line); // for debug scanner when reading file
                String type = temp[0];
                //check the type and insert to the object
                if(type.equals("Book")) {
                    int id = Integer.parseInt(temp[1]);
                    int year = Integer.parseInt(temp[3]);
                    int number_of_pages = Integer.parseInt(temp[5]);
                    Books_list.add( new Books(temp[0], id, temp[2], year, temp[4], number_of_pages));
                    Details_list.add(new Books(temp[0], id, temp[2], year, temp[4], number_of_pages));
                }
                if(type.equals("Movie")){
                    int id = Integer.parseInt(temp[1]);
                    int year = Integer.parseInt(temp[3]);
                    Movies_list.add( new Movies(temp[0], id, temp[2], year, temp[4]));
                    Details_list.add(new Movies(temp[0], id, temp[2], year, temp[4]));
                }
                if(type.equals("Journal")){
                    int id = Integer.parseInt(temp[1]);
                    int year = Integer.parseInt(temp[3]);
                    int volume = Integer.parseInt(temp[4]);
                    int number = Integer.parseInt(temp[5]);
                    Journals_list.add( new Journals(temp[0], id, temp[2], year, volume, number));
                    Details_list.add( new Journals(temp[0], id, temp[2], year, volume, number));
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            scanner.close();
        }
        for(Movies m: Movies_list){
            m.show_details();
        }
        for(Books b: Books_list) {
            b.show_details();
        }
        for(Journals j:Journals_list){
            j.show_details();
        }

        Scanner scanner_key = null;
        boolean continue_choice = true;
        boolean borrow;
        boolean rate;
        first:
        while (continue_choice) {

            System.out.println("Enter 'q' to quit, enter 'i' to search by ID, or enter any other key to search by phrase in title");
            scanner_key = new Scanner(System.in);
            String choice = scanner_key.nextLine();
            if (choice.equals("q") || choice.equals("Q")) { // enter 'q' ---quit
                continue_choice = false;
                System.exit(0);
            } else if (choice.equals("i") || choice.equals("I")) { //enter 'i' ---search by ID
                second:
                while (true) {
                    System.out.println("Enter ID to start search, or enter 'b' to go back to choose search method");
                    scanner_key = new Scanner(System.in);
                    choice = scanner_key.nextLine();
                    int choice_id = 0;
                    if (choice.equals("b") || choice.equals("B")) {
                        continue first;  //back to the start of choose
                    } ////////////did not check if enter neither 'b' or num
                    else if (choice.matches("\\d+")) {
                        choice_id = Integer.parseInt(choice);
                        boolean found;
                        found = false;
                        for (Details d : Details_list) {
                            if (d.id == choice_id) {
                                found = true;
                                System.out.println("----------------------------------------------");
                                d.show_details();
                                System.out.println("Enter 'i' to search other items by ID, or enter any other key to select this item");
                                choice = scanner_key.nextLine();
                                if (choice.equals("i") || choice.equals("I")) {
                                    continue second;
                                } else {
                                    System.out.println("Selected item is");
                                    System.out.println("----------------------------------------------");
                                    d.show_details();

                                    borrow = false;
                                    fourth:
                                    while (!borrow) {
                                        System.out.println("Enter 'b' to borrow the item, enter 'a' to rate the item, or enter any other key to restart");
                                        choice = scanner_key.nextLine();
                                        if (choice.equals("b") || choice.equals("B")) {
                                            System.out.println("The item's due date is " + d.due_date());
                                            d.setStatus("on loan");
                                            System.out.println("Selected item is");
                                            System.out.println("----------------------------------------------");
                                            d.show_details();
                                            borrow = true;


                                            rate = true;
                                            while (rate) {
                                                System.out.println("Enter 'r' to return the item, enter 'a' to rate the item, or enter any other key to restart");
                                                choice = scanner_key.nextLine();
                                                if (choice.equals("a") || choice.equals("A")) {
                                                    System.out.println("Please enter your rating (0-10)");
                                                    float current_rating = 0;
                                                    scanner_key = new Scanner(System.in);
                                                    int temp_rating = Integer.parseInt(scanner_key.nextLine());
                                                    current_rating = d.calculate(temp_rating);
                                                    System.out.println("The item's new average rating is " + current_rating);
                                                    System.out.println("Selected item is");
                                                    System.out.println("----------------------------------------------");
                                                    d.show_details();
                                                    continue;
                                                }
                                                if (choice.equals("r") || choice.equals("R")) {
                                                    System.out.println("This item is returned");
                                                    d.setStatus("available");
                                                    System.out.println("Selected item is");
                                                    System.out.println("----------------------------------------------");
                                                    d.show_details();
                                                    rate = false;
                                                    borrow = false;
                                                    continue fourth;
                                                } else {
                                                    continue first;
                                                }
                                            }

                                        }

                                        if (choice.equals("a") || choice.equals("A")) {
                                            System.out.println("Please enter your rating (0-10)");
                                            float current_rating = 0;
                                            scanner_key = new Scanner(System.in);
                                            int temp_rating = Integer.parseInt(scanner_key.nextLine());
                                            current_rating = d.calculate(temp_rating);
                                            System.out.println("The item's new average rating is " + current_rating);
                                            //b.addReview();
                                            System.out.println("Selected item is");
                                            d.show_details();
                                            //continue fourth;
                                        } else {
                                            continue first;
                                        }
                                    }
                                }

                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("Item is not found.");

                        }
                    } else {
                        System.out.println("Invalid input.");
                        //continue first;
                    }
                }

            } else { //here to search with phrase
                System.out.println("Enter phrase in title to start search, or 'b' to go back to choose search method");
                choice = scanner_key.nextLine();
                if(choice.equals("b")||choice.equals("B")) continue first;
                boolean match;
                match = false;
                int n = 0;
                ArrayList<Details>Match_list = new ArrayList<Details>();
                int choice_num;
                for (Details d : Details_list) {
                    if (d.title.contains(choice)) {  //if contain the same phrases
                        n++;
                        match = true;
                        System.out.println("*" + n + "----------------------------------------------");
                        d.show_details();
                        Match_list.add(n-1, d);
                    }
                }

                if(match) {
                    System.out.println("Enter item number to select item, or enter any other key to continue searching");
                    choice = scanner_key.nextLine();
                    choice_num = Integer.parseInt(choice);
                    if((choice_num>0) &&(choice_num<=n)){
                        System.out.println("Selected item is");
                        System.out.println("----------------------------------------------");
                        Match_list.get(choice_num - 1).show_details();

                            borrow = false;
                            third:
                            while (!borrow) {
                                System.out.println("Enter 'b' to borrow the item, enter 'a' to rate the item, or enter any other key to restart");
                                choice = scanner_key.nextLine();
                                if (choice.equals("b") || choice.equals("B")) {
                                    System.out.println("The item's due date is " + Match_list.get(choice_num - 1).due_date());
                                    Match_list.get(choice_num - 1).setStatus("on loan");
                                    System.out.println("Selected item is");
                                    System.out.println("----------------------------------------------");
                                    Match_list.get(choice_num - 1).show_details();
                                    borrow = true;


                                    rate = true;
                                    while (rate) {
                                        System.out.println("Enter 'r' to return the item, enter 'a' to rate the item, or enter any other key to restart");
                                        choice = scanner_key.nextLine();
                                        if (choice.equals("a") || choice.equals("A")) {
                                            System.out.println("Please enter your rating (0-10)");
                                            float current_rating = 0;
                                            scanner_key = new Scanner(System.in);
                                            int temp_rating = Integer.parseInt(scanner_key.nextLine());
                                            current_rating = Match_list.get(choice_num - 1).calculate(temp_rating);
                                            System.out.println("The item's new average rating is " + current_rating);
                                            System.out.println("Selected item is");
                                            System.out.println("----------------------------------------------");
                                            Match_list.get(choice_num - 1).show_details();
                                            continue;
                                        }
                                        if (choice.equals("r") || choice.equals("R")) {
                                            System.out.println("This item is returned");
                                            Match_list.get(choice_num - 1).setStatus("available");
                                            System.out.println("Selected item is");
                                            System.out.println("----------------------------------------------");
                                            Match_list.get(choice_num - 1).show_details();
                                            rate = false;
                                            borrow = false;
                                            continue third;
                                        } else {
                                            Match_list.clear();
                                            continue first;
                                        }
                                    }
                                }


                                if (choice.equals("a") || choice.equals("A")) {
                                    System.out.println("Please enter your rating (0-10)");
                                    float current_rating = 0;
                                    scanner_key = new Scanner(System.in);
                                    int temp_rating = Integer.parseInt(scanner_key.nextLine());
                                    current_rating = Match_list.get(choice_num - 1).calculate(temp_rating);
                                    System.out.println("The item's new average rating is " + current_rating);
                                    //b.addReview();
                                    System.out.println("Selected item is");
                                    System.out.println("----------------------------------------------");
                                    Match_list.get(choice_num - 1).show_details();
                                    //continue fourth;
                                } else {
                                    continue first;
                                }
                            }



                    }else{
                        System.out.println("The number is not in range.Choose again.");
                        continue;
                    }
                }



                if(!match){
                    System.out.println("Item is not found.");
                }
            }
        }
        scanner_key.close();
    }

    private static void Info() {
        System.out.println("-----------------------------------");
        System.out.println("              159.234              ");
        System.out.println("   Assignment 2 Semester 1 2020    ");
        System.out.println("Submitted by: Moci Xu; ID: 18043870");
        System.out.println("             Hao Zhou; ID: 19046061");
        System.out.println("           Haoge Chen; ID: 19027676");
        System.out.println("              Huan li; ID: 19035498");
        System.out.println("-----------------------------------");
    }
}

