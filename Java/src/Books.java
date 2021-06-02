
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Books extends Details {
    private String author;
    private int number_of_pages;


    public Books(String type,int id, String title, int year, String author, int number_of_pages ){
        super(type, id, title, year);
        this.author = author;
        this.number_of_pages = number_of_pages;
        max_days_borrow = 28;
        //initialize
        all_rating = 0;
        average_rating = 0;
        num_review = 0;
        status = "available";
    }



    public void show_details(){
        System.out.println("Type: "+ get_type());
        System.out.println("Title: "+ title);
        System.out.println("ID: "+ get_id());
        System.out.println("Year: "+ year);
        System.out.println("Average rating: "+ average_rating);
        System.out.println("Number of reviews: "+ num_review);
        System.out.println("Status: " + status);
        if(status.equals("on loan")){
            System.out.println("Due date: "+ due_date());
        }
        System.out.println("Author: "+ author);
        System.out.println("Number of pages: "+ number_of_pages);
        System.out.println("Max number of days for borrowing: "+ max_days_borrow);
        System.out.println("----------------------------------------------");
    }



    @Override
    public String due_date() {
        SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,28);
        return sdFormat.format(calendar.getTime());
    }


}
