import java.util.ArrayList;
import java.util.Scanner;

public class classy {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);        
        ArrayList<Person> personList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            int cases = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < cases; i++) {
                int people = Integer.parseInt(scanner.nextLine()); 
                    for (int j = 0; j < people; j++) {
                        String personInfo = scanner.nextLine();
                        String[] nameThenClass = personInfo.split(":");
                        String name = nameThenClass[0];
                        String[] classInfo = nameThenClass[1].split("-");
                        String[] lastClass = classInfo[classInfo.length - 1].split(" ");
                        String[] constructorVal = new String[classInfo.length];
                        for (int k = 0; k < classInfo.length - 1; k++) {
                            constructorVal[k] = classInfo[k];
                        }
                        constructorVal[classInfo.length] = lastClass[0];
                        Person p = new Person(name, constructorVal);
                    }
                }
            }
        }
}

