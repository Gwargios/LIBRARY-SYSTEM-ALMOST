package Rating;

public class Rating {
    private String RaterID ;
    private int RateNum;
    private String Review ;

    public Rating(String RaterID, int RateNum, String Review){
        this.RaterID = RaterID;
        this.RateNum = RateNum;
        this.Review = Review ;
    }
    public String GetRaterID (){
        return RaterID;
    }
    public int GetRateNum (){
        return RateNum ;
    }
    public String Review (){
        return Review;
    }

}
