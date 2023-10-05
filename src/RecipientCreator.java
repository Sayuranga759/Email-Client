public class RecipientCreator {
    public static Recipient addRecipient(String type, String[] info) {

        switch (type) {
            case "Official:" -> {
                return new OfficialRecipient(info[0], info[1], info[2]);
            }

            case "Office_friend:" -> {
                Recipient officeFriend = new OfficeFriend(info[0], info[1], info[2], info[3]);
                Email_Client.toWishList.add((Greeting) officeFriend);
                return officeFriend;
            }

            case "Personal:" -> {
                Recipient personalRecipient = new PersonalRecipient(info[0], info[1], info[2], info[3]);
                Email_Client.toWishList.add((Greeting) personalRecipient);
                return personalRecipient;
            }
        }
        return null;
    }
}
