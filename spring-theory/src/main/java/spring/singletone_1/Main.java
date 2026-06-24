package spring.singletone_1;

public class Main {
    public static void main(String[] args) {
        TicketMachine ticketMachine1 = TicketMachine.getInstance();
        TicketMachine ticketMachine2 = TicketMachine.getInstance();

        ticketMachine1.issue();
        ticketMachine1.issue();
        ticketMachine2.issue();
        ticketMachine2.issue();
        ticketMachine2.issue();


        System.out.println(ticketMachine2 == ticketMachine2);
        System.out.println(ticketMachine1.getLastNumber());
        System.out.println(ticketMachine2.getLastNumber());

        Settings setting1 = Settings.getInstance();
        Settings setting2 = Settings.getInstance();

        setting1.setTheme("light");
        setting2.setTheme("dark");

        System.out.println(Settings.getInstance().getTheme());
    }
}
