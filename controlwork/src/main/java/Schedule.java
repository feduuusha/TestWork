import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Schedule {
   List<String> listOfStrokes = new ArrayList<>();
   Map<String, List<Program>> programs = new HashMap<>();
   List<Program> programList = new ArrayList<>();
    public Schedule(String pathname) throws FileNotFoundException {
        Scanner in = new Scanner(new File(pathname));
        while (in.hasNextLine()) {
            listOfStrokes.add(in.nextLine());
        }
        readFile();
    }

    private void readFile() {
        String currentChannel = "";
        for (int i = 0; i < listOfStrokes.size() - 1; ++i) {
            String line = listOfStrokes.get(i);
            if (line.charAt(0) == '#') {
                currentChannel = line.substring(1);
            } else {
                String title = listOfStrokes.get(i+1);
                Program program = new Program(currentChannel, title, new BroadcastsTime(line));
                if (programs.containsKey(currentChannel)) {
                    programs.get(currentChannel).add(program);
                } else {
                    List<Program> list = new ArrayList<>();
                    list.add(program);
                    programs.put(currentChannel, list);
                }
                programList.add(program);
                i++;
            }
        }
    }

    public List<Program> allProgramsToList() {
        return programList;
    }

    public Map<String, List<Program>> allProgramsToMap() {
        return programs;
    }

    public String currentTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
}
