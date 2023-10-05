import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class PrintDetails {
    public static void printBirthDayList(LocalDate inputDate, Operator operator){

        System.out.println("printing names who has birthdays in the given date:");

        for (Greeting rec : Email_Client.toWishList) {
            if (rec.getBirthDay().equals(operator.formatDate(inputDate).substring(5))) {
                System.out.println(((Recipient)rec).getName());
            }
        }
        System.out.println("\n");
    }

    public static void printEmailInfo(LocalDate inputDate){
        System.out.println("Printing the details of all the emails sent on the input date");

        for (Map.Entry<LocalDate, ArrayList<SendEmail>> entry : Email_Client.emailHistory.entrySet()) {
            if (entry.getKey().equals(inputDate)) {
                for (SendEmail email: entry.getValue()) {
                    System.out.println(email.getSendTo());
                    System.out.println(email.getSubject());
                    System.out.println("\n");
                }
                break;
            }

        }
    }
}
