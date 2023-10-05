import java.io.FileWriter;
import java.io.IOException;

public class PersonalRecipient extends Recipient implements Greeting {

    private String nickName;
    private String birthDay;

    public PersonalRecipient(String name, String nickName, String email, String birthDay) {
        super(name, email);
        this.nickName = nickName;
        this.birthDay = birthDay;
    }
    @Override
    public void writeFile() {
        try {
            FileWriter writer = new FileWriter("clientList.txt", true);
            writer.write("Personal: " + super.getName() + "," + nickName + "," +  super.getEmail() + "," + birthDay);
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
        return "Hugs and love on your BirthDay. Ravindu Sayuranga";
    }
}
