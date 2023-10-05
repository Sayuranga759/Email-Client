import java.io.FileWriter;
import java.io.IOException;

public class OfficialRecipient extends Recipient {

    private String designation;

    public OfficialRecipient(String name, String email, String designation) {
        super(name, email);
        this.designation = designation;
    }

    @Override
    public void writeFile() {
        try {
            FileWriter writer = new FileWriter("clientList.txt", true);
            writer.write("Official: " + super.getName() + "," +  super.getEmail() + "," + designation);
            writer.write("\n");
            writer.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public String getDesignation() {
        return designation;
    }
}
