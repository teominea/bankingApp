package org.poo.cb;


public class CommandListUser extends Command {
    String email;

    BankObserver observer;

    public CommandListUser(String email) {
        this.email = email;
    }

    public void attach(BankObserver observer) {
        this.observer = observer;
    }

    public void notifyObserver() {
        observer.update();
    }

    @Override
    public void execute(String s1, String s2) {
        observer = new BankObserver(this);
        if (BankingApp.getInstance(s1, s2).users.get(email) == null) {
            setMessage("User with email " + email + " doesn't exist");
            return;
        }
        User user = BankingApp.getInstance(s1, s2).users.get(email);

            String[] friends = user.getFriends().keySet().toArray(new String[0]);
            String jsonString = "{"
                    + "\"email\":\"" + email + "\","
                    + "\"firstname\":\"" + user.getName() + "\","
                    + "\"lastname\":\"" + user.getSurname() + "\","
                    + "\"address\":\"" + user.getAddress() + "\","
                    + "\"friends\":[";
            if (friends.length > 0) {
                for (int i = 0; i < friends.length - 1; i++) {
                    jsonString += "\"" + friends[i] + "\",";
                }
                jsonString += "\"" + friends[friends.length - 1] + "\"]}";
            } else {
                jsonString += "]}";
            }

        setMessage(jsonString);

    }
}
