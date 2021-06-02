
public abstract class Details {
    protected String type;
    protected int id;
    protected String title;
    protected int year;
    protected float all_rating;
    protected float average_rating;
    protected int num_review;
    protected String status ;
    //private boolean status;
    protected int max_days_borrow;

    public Details (String type,int id, String title, int year){
        this.type = type;
        this.id = id;
        this. title = title;
        this. year = year;
    }

    public String get_type() {
        return this.type;
    }



    public int get_id() {
        return this.id;
    }


    //calculate the average_rating


    public float calculate(float rate){
        all_rating += rate;
        num_review++;
        average_rating = all_rating / num_review;
        return average_rating;
    }


    public void setStatus(String s){
        this.status = s;
    }

    public abstract String due_date();

    public abstract void show_details();

}

   /* public Details(String type, int id, String title, int year, float average_rating, int num_review,String status,int max_days_borrow){
        this.type = type;
        this.id = id;
        this.title = title;
        this.year = year;
        this.average_rating = average_rating;
        this num_review = num_review;
        this.status = status;
        this.max_days_borrow = max_days_borrow;

    }

    /*public String getType(){return type;}

    public int getId(){return id;}

    public String getTitle() {return title;}

    public int getYear(){return year;}

    public float getAverage_rating() {
        return average_rating;
    }

    public int getNum_review() {
        return num_review;
    }

    public String getStatus(){return status;}

    public int getMax_days_borrow() {
        return max_days_borrow;
    }*/



