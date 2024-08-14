/**
 * The Module class represents the academic modules for a student,
 * storing marks for three subjects and calculating average marks and grades.
 */
public class Module {
    private double s1;
    private double s2;
    private double s3;
    public double avg;
    public  String grade;

    /**
     * Constructs a Module object with the given marks for three subjects.
     *
     * @param s1 Marks for subject 1.
     * @param s2 Marks for subject 2.
     * @param s3 Marks for subject 3.
     */
    public Module(double s1, double s2, double s3) {

        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.avg=calculateAvg();
        this.grade=calculateGrade();

    }

    public double getS1() {
        return s1;
    }

    public void setS1(double s1) {
        this.s1 = s1;
    }

    public double getS2() {
        return s2;
    }

    public void setS2(double s2) {
        this.s2 = s2;
    }

    public double getS3() {
        return s3;
    }

    public void setS3(double s3) {
        this.s3 = s3;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public  double calculateAvg() {

        return (this.s1 + this.s2 + this.s3) / 3.0;
    }

    /**
     * Calculates the grade based on the average marks.
     *
     * @return Calculated grade ("Distinction", "Merit", "Pass", "Fail").
     */
    public String calculateGrade() {
        return this.avg >= 80 ? "Distinction" : this.avg >= 70 ? "Merit" : this.avg >= 40 ? "Pass" : "Fail";

    }
}
