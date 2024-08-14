/**
 * The Student class represents a student with their ID, name, and academic module information.
 */
public class Student  {
    //Declare properties of the class
    private String stuid;
    private String name;
    private Module module;

    /**
     * Constructs a Student object with the given student ID, name, and academic module.
     *
     * @param studid Student ID.
     * @param name   Student name.
     * @param module Academic module associated with the student.
     */
    public Student(String studid, String name,Module module) {
        //initialize class constructor

        this.stuid = studid;
        this.name = name;
        this.module=module;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid){
        this.stuid=stuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
