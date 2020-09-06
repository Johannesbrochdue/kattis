public class Person implements Comparable<Person> {

    String[] classInfo;
    String name;

    public Person(String name, String[] classInfo) {
        this.classInfo = classInfo;
        this.name = name;
        System.out.println(classInfo);
    }

    @Override
    public int compareTo(Person p) {
        int i = 0;
        int j = 0;

        int lengthOther = p.classInfo.length;
        int lengthThis = classInfo.length;

        while (i < p.classInfo.length || j < this.classInfo.length) {
            if (getClassValue(p.classInfo[i]) > getClassValue(this.classInfo[j])) {
                return -1;
            } else if (getClassValue(p.classInfo[i]) < getClassValue(this.classInfo[j])) {
                return 1;
            } else {
                i++;
                j++;
            }
        }
        return name.compareTo(p.name);
    }
    
    private static int getClassValue(String classType) {
        if (classType == "upper") {
            return 2;
        } else if (classType == "middle") {
            return 1;
        } else if (classType == "lower") {
            return 0;
        }
        return -1;
    }
}