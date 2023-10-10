public class Playground {
    /*
    This file is not used for grading, but it must compile.
     */
    public static void main(String[] args) {
        String dateString = "2 day and 12 ";
        int hours = DateService.dateStringToNumberOfHour(dateString);
        System.out.println("String " + dateString + " converted to " + hours);
    }
}
