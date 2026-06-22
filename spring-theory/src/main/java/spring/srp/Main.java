package spring.srp;

public class Main {
    public static void main(String[] args) {
        Journal journal = new Journal();

        journal.add("오늘 자바 공부함");
        journal.add("SRP 단일책임원칙도 공부함");

        JournalSaver journalSaver = new JournalSaver();

        journalSaver.print(journal);

        journalSaver.saveToFile(journal, "journal.txt");
    }
}
