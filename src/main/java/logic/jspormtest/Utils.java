package logic.jspormtest;

public class Utils {
    public static double calculateCompoundInterest(double capital, double interest, double years, double compoundingPeriod){

        return capital * Math.pow((1+ (interest / compoundingPeriod)), compoundingPeriod * years);

    }
}