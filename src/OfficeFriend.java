import java.io.FileWriter;
import java.io.IOException;

public class OfficeFriend extends OfficialRecipient implements Greeting {

    private String birthDay;

    public OfficeFriend(String name, String email, String designation, String birthDay) {
        super(name, email, designation);
        this.birthDay = birthDay;
    }

    public void writeFile() {                   //writing data do the clientList file
        try {
            FileWriter writer = new FileWriter("clientList.txt", true);
            writer.write("Office_friend: " + super.getName() + "," +  super.getEmail() + "," + super.getDesignation() + "," + birthDay);
            writer.write("\n");
            writer.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    @Override
    public String getBirthDay() {
        return birthDay.substring(5);
    }

    @Override
    public String birthDayGreeting() {
        return "Wish you a Happy Birthday. Ravindu Sayuranga";
    }
}
