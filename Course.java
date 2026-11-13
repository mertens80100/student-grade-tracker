public class Course {
    private String name;
    private int credit;
    private double midterm;
    private double finalExam;

    public Course(String name, int credit, double midterm, double finalExam) {
        this.name = name;
        this.credit = credit;
        this.midterm = midterm;
        this.finalExam = finalExam;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public double getAverage() {
        return (midterm * 0.4) + (finalExam * 0.6);
    }

    public String getLetterGrade() {
        double avg = getAverage();
        if (avg >= 90) return "AA";
        else if (avg >= 85) return "BA";
        else if (avg >= 80) return "BB";
        else if (avg >= 75) return "CB";
        else if (avg >= 70) return "CC";
        else if (avg >= 60) return "DC";
        else if (avg >= 50) return "DD";
        else return "FF";
    }

    public double getGradePoint() {
        String letter = getLetterGrade();
        if (letter.equals("AA")) return 4.0;
        else if (letter.equals("BA")) return 3.5;
        else if (letter.equals("BB")) return 3.0;
        else if (letter.equals("CB")) return 2.5;
        else if (letter.equals("CC")) return 2.0;
        else if (letter.equals("DC")) return 1.5;
        else if (letter.equals("DD")) return 1.0;
        else return 0.0;
    }

    public String toFileLine() {
        return name + ";" + credit + ";" + midterm + ";" + finalExam;
    }

    @Override
    public String toString() {
        return name + " (" + credit + " cr) | Avg: " + String.format("%.1f", getAverage()) + " | Grade: " + getLetterGrade();
    }
}
