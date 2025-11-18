import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentCode;
    private float GPA;
    private List<Lessons> lessons;
    private Major major; // added major

    public Student(String studentCode) {
        this.studentCode = studentCode;
        this.lessons = new ArrayList<>();
        this.GPA = 0.0f;
    }

    public String getStudentCode() { return studentCode; }

    public List<Lessons> getLessons() { return lessons; }

    public float getGPA() { return GPA; }

    public Major getMajor() { return major; } 
    public void setMajor(Major major) { this.major = major; } 

    public void addLesson(Lessons lesson) {
        lessons.add(lesson);
        recalculateGPA();
    }

    private void recalculateGPA() {
        float totalGPA = 0.0f;
        for (Lessons lesson : lessons) {
            totalGPA += lesson.calculateGPA();
        }
        this.GPA = lessons.isEmpty() ? 0.0f : totalGPA / lessons.size();
    }

    public int getFailedSubjectsCount() {
        int count = 0;
        for (Lessons lesson : lessons) {
            if (lesson.hasFailed()) count++;
        }
        return count;
    }

    public Lessons getLessonBySubject(Subject subject) {
        for (Lessons lesson : lessons) {
            if (lesson.getLearned().equals(subject)) return lesson;
        }
        return null;
    }
}
