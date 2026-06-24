package spring.solid.srp;

public class JournalSaver {
    public void print(Journal journal) {
        System.out.println(journal.getText());
    }

    public void saveToFile(Journal journal, String filename) {
        System.out.println(filename);
    }

}

