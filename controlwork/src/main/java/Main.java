import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Schedule schedule;
        try {
            schedule = new Schedule("controlwork/src/main/resources/schedule.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Указанного файла не существует");
            return;
        }
        Map<String, List<Program>> programs = schedule.allProgramsToMap();
        List<Program> allPrograms = schedule.allProgramsToList();
        allPrograms.sort((o1, o2) -> o1.time.compareTo(o2.time));
        for (Program program : allPrograms) {
            System.out.println(program + " ");
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("Все программы которые идут сейчас:");
        BroadcastsTime currentTime = new BroadcastsTime(schedule.currentTime());
        for (String channel : programs.keySet()) {
            List<Program> list = programs.get(channel);
            for (int i = 0; i < list.size() - 1; ++i) {
                Program fisrtProgram = list.get(i);
                Program secondProgram = list.get(i+1);
                if (currentTime.between(fisrtProgram.time, secondProgram.time)) {
                    System.out.println(fisrtProgram);
                }
            }
        }
        Scanner console = new Scanner(System.in);
        System.out.println("----------------------------------------------------------");
        System.out.println("Введите название программы, которую хотите найти:");
        String input = console.nextLine();
        System.out.println("Все программы, которые содержат в названии: " + input);
        for (Program program : allPrograms) {
            if (program.title.contains(input)) System.out.println(program);
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("Введите название канала, программы которого хотите найти:");
        String channel = console.nextLine();
        if (programs.containsKey(channel)) {
            List<Program> programList = programs.get(channel);
            System.out.println("Все программы этого канала, которые идут сейчас: ");
            for (int i = 0; i < programList.size() - 1; ++i) {
                if (currentTime.between(programList.get(i).time, programList.get(i+1).time)) {
                    System.out.println(programList.get(i));
                }
            }
        } else
            System.out.println("Такого канала нет в телепрограмме :(");
        System.out.println("----------------------------------------------------------");
        System.out.println("Введите название канала, программы которого хотите найти:");
        channel = console.nextLine();
        System.out.println("Введите время начала: (в формате HH:MM)");
        BroadcastsTime firstTime = new BroadcastsTime(console.nextLine());
        System.out.println("Введите время конца: (в формате HH:MM)");
        BroadcastsTime secondTime = new BroadcastsTime(console.nextLine());
        if (programs.containsKey(channel)) {
            List<Program> programList = programs.get(channel);
            System.out.println("Все программы этого канала, которые идут в этот промежуток времени: ");
            for (Program program : programList) {
                if (program.time.between(firstTime, secondTime))
                    System.out.println(program);

            }
        } else
            System.out.println("Такого канала нет в телепрограмме :(");
    }
}
