
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Movies extends Details {
    private String director;

    public Movies(String type,int id, String title, int year, String director){
        super(type, id, title, year);
        this.director = director;
        max_days_borrow = 7;
        //initialize
        average_rating = 0;
        num_review = 0;
        status = "available";
    }

    public void show_details() {
        System.out.println("Type: " +get_type());
        System.out.println("Title: " + title);
        System.out.println("ID: " + get_id());
        System.out.println("Year: " + year);
        System.out.println("Average rating: " + average_rating);
        System.out.println("Number of reviews: " + num_review);
        System.out.println("Status: " + status);
        if(status.equals("on loan")){
            System.out.println("Due date: "+ due_date());
        }
        System.out.println("Director: " + director);
        System.out.println("Max number of days for borrowing: " + max_days_borrow);
        System.out.println("----------------------------------------------");
    }
@Override
    public String due_date(){
        SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,7);
        return sdFormat.format(calendar.getTime());
    }


}
