// 200532K - G.G.R.S. Rathnawardana

//import libraries
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Scanner;

public class Email_Client {

    static ArrayList<Recipient> recipientsList = new ArrayList<>();       //List of Recipients in the Email Client.
    static ArrayList<Greeting> toWishList = new ArrayList<>();            //List of Recipients with having dates of their B'days(Office Friends + Personal).
    static ArrayList<SendEmail> wishedList = new ArrayList<>();           //List of SendEmail objects that created when sending Emails.
    static HashMap<LocalDate, ArrayList<SendEmail>> emailHistory = new HashMap<>();   //Hashmap showing sent Emails according to their sent dates.


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Operator operator = new Operator();
        //Deserializing the serialized emailHistory object.
        operator.deserialize();

        //Traversing the clientList.txt file, when the Email_Client is started.
        operator.fileReader();

        //Checking whether the wishes have already sent for the Recipients who have B'days on the Current Date. If not check who have B'days today.
        operator.birthDayChecker();

        //Starting the Email Client.
        while (true) {

            System.out.println("""
                    Enter option type:
                    1 - Adding a new recipient
                    2 - Sending an email
                    3 - Printing out all the recipients who have birthdays
                    4 - Printing out details of all the emails sent
                    5 - Printing out the number of recipient objects in the application
                    6 - Close the Email Client""");

            int option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.println("Adding a new recipient.");
                    scanner.nextLine();
                    String[] details = scanner.nextLine().split(" ");
                    Objects.requireNonNull(RecipientCreator.addRecipient(details[0], details[1].split(","))).writeFile();
                    System.out.println("New Recipient successfully added\n");
                }
                case 2 -> {
                    System.out.println("Sending an email.");
                    System.out.println("Input format - email, subject, content");
                    //String readInput
                    scanner.nextLine();
                    String[] input = scanner.nextLine().split(",");
                    SendEmail email = new SendEmail(input[0], input[1], input[2]);
                    wishedList.add(email);
                }
                case 3 -> {
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print recipients who have birthdays on the given date
                    System.out.println("Input format - yyyy/MM/dd (ex: 2018/09/17)");
                    String inputDate = scanner.next();
                    //Converting user given String date to LocalDate object.
                    LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    PrintDetails.printBirthDayList(date, operator);
                }
                case 4 -> {
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print the details of all the emails sent on the input date
                    //Adding sent Emails on current date to the emailHistory HashMap.
                    emailHistory.put(LocalDate.now(), wishedList);
                    System.out.println("Printing out details of all the emails sent on the input date.");
                    System.out.println("Input format - yyyy/MM/dd (ex: 2018/09/17)");
                    String givenDate = scanner.next();
                    PrintDetails.printEmailInfo(LocalDate.parse(givenDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                }
                case 5 -> {
                    // code to print the number of recipient objects in the application
                    System.out.println("Printing out the number of recipient objects in the application");
                    System.out.println(Recipient.countOfRecipients);
                    System.out.println("\n");
                }
                case 6 -> {
                    if (emailHistory.containsKey(LocalDate.now())) {
                        wishedList.addAll(emailHistory.get(LocalDate.now()));
                    }
                    emailHistory.put(LocalDate.now(), wishedList);
                    operator.serialize();                                 //Saving created emailHistory object on current date in Hard Disk.
                    System.out.println("Email Client Ended.");
                    return;
                }
            }
        }
    }

}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)