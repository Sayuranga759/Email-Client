import java.io.Serializable;

public abstract class Recipient {

    private String name;
    private String email;
    static int countOfRecipients = 0;

    public Recipient(String name, String email) {
        this.name = name;
        this.email = email;
        countOfRecipients ++;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public abstract void writeFile();
}
