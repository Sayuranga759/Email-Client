import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Operator {
    public void birthDayChecker() {
        if (!Email_Client.emailHistory.containsKey(LocalDate.now())) {
            for (Greeting recipient : Email_Client.toWishList) {
                String currentDate = formatDate(LocalDate.now()).substring(5);
                if (recipient.getBirthDay().equals(currentDate)) {
                    //Sending Emails to wish for the B'days and add those created SendEmail objects to wishedList.
                    Email_Client.wishedList.add(new SendEmail(((Recipient) recipient).getEmail(), "Greetings for the B'day!", recipient.birthDayGreeting()));
                }
            }
        }
    }

    public void fileReader() {
        try {
            File clientList = new File("clientList.txt");
            Scanner readDetails = new Scanner(clientList);

            while (readDetails.hasNextLine()) {
                String dataLine = readDetails.nextLine();
                String[] splitData = dataLine.split(":");                                //Creating Recipients objects according to the file.
                Recipient recipient = RecipientCreator.addRecipient(splitData[0] + ":", splitData[1].strip().split(","));
                Email_Client.recipientsList.add(recipient);
            }
            readDetails.close();

        } catch (FileNotFoundException error) {
            System.out.println("An Error found in your file!");
            error.printStackTrace();
        }
    }

    public void serialize(){

        try {
            FileOutputStream dailyLog = new FileOutputStream("SentEmails.ser");
            ObjectOutputStream outputLog = new ObjectOutputStream(dailyLog);
            outputLog.writeObject(Email_Client.emailHistory);
            outputLog.close();
            dailyLog.close();

        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public void deserialize(){
        try
        {
            FileInputStream savedLog = new FileInputStream("SentEmails.ser");
            ObjectInputStream inputLog = new ObjectInputStream(savedLog);
            Email_Client.emailHistory = (HashMap<LocalDate, ArrayList<SendEmail>>) inputLog.readObject();
            if (Email_Client.emailHistory.containsKey(LocalDate.now()))
                Email_Client.wishedList = Email_Client.emailHistory.get(LocalDate.now());

            inputLog.close();
            savedLog.close();

        } catch (IOException | ClassNotFoundException error) {
            error.printStackTrace();
        }
    }

    public String formatDate(LocalDate date){                                  //formatting LocalDate to a String
        return date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
