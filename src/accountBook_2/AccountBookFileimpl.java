package accountBook_2;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AccountBookFileImpl implements AccountBook {

    private final String DIR = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "/accountbook";
    Scanner sc = new Scanner(System.in);


    public AccountBookFileImpl(){

        File folder = new File(DIR);


        System.out.println("저장 위치 : " + folder.getAbsolutePath());
        if(!folder.exists()){
            folder.mkdir();
        }
    }


    @Override
    public void addAccount(){
        String today = LocalDate.now().toString();
        File file = new File(DIR, today + ".txt");
        List<Item> items = loadFile(file);

        while(true) {
            System.out.print("항목 이름 > ");
            String name = sc.nextLine();
            System.out.print("금액 > ");

            int money =Integer.parseInt(sc.nextLine());

            Item item = new Item(name, money);

            items.add(item);

            System.out.print("더 추가할까요? (y/n) > ");
            String answer = sc.nextLine();
            if(answer.equals("n")){
                break;
            }
        }

        savaFile(file, items);
        System.out.println("저장 완료");

    }

    @Override
    public void showAccount(){
        File folder = new File(DIR);
        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("기록이 없습니다.");
            return;
        }

        System.out.println("== 기록된 날짜 ==");

        for(File file : files){
            if(file.getName().endsWith(".txt")){
                System.out.println(file.getName().replace(".txt", ""));
            }
        }

        System.out.println("조회할 날짜 입력 > ");

        String date = sc.nextLine();

        File target = new File(DIR, date+".txt");

        // 해당 날짜 없음
        if(!target.exists()){System.out.println("해당 날짜 기록이 없습니다.");
            return;
        }

        System.out.println();
        System.out.println("[" + date + "]");




        try(BufferedReader br = new BufferedReader(new FileReader(target))){
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void deleteAccount(){
        File folder = new File(DIR);

        File[] files = folder.listFiles();

        if(files == null || files.length == 0) {
            System.out.println("삭제할 기록이 없습니다.");
            return;
        }

        System.out.println("== 기록된 날짜 ==");

        for(File file : files){
            if(file.getName().endsWith(".txt")){
                System.out.println(file.getName().replace(".txt", ""));
            }
        }

        System.out.print("삭제할 날짜 입력 > ");

        String date = sc.nextLine();

        File target = new File(DIR, date + ".txt");
        System.out.println(target.getAbsolutePath());

        System.out.println(target.exists());

        System.out.println(target.isFile());
        if(!target.exists()) {
            System.out.println("해당 날짜 기록이 없습니다.");
            return;
        }
        if(target.delete()) {
            System.out.println("삭제되었습니다.");

        } else {

            System.out.println("삭제 실패");

        }

    }



    private void savaFile(File file, List<Item> items) {
        int total = 0;

        try (FileWriter fw = new FileWriter(file, false)) {
            for (Item item : items) {
                fw.write(item.toString() + '\n');
                total += item.getPrice();
            }

            fw.write("합계 : " + total + "원\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Item> loadFile(File file){
        List<Item> items = new ArrayList<>();

        if(!file.exists()){
            return items;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;

            while((line = br.readLine()) != null){
                if(line.startsWith("합계")){
                    continue;
                }

                String[] split = line.split(" : ");
                String name = split[0];

                int price = Integer.parseInt(split[1].replace("원",""));

                items.add(new Item(name, price));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return items;

    }
}