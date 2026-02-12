import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Registration2 {
    private List<Student> studentList;
    private List<Subject> subjectList;
    private List<Major> majorList;
    public int failingAllCount = 0;

    public Registration2() {
        studentList = new ArrayList<>();
        subjectList = new ArrayList<>();
        majorList = new ArrayList<>();
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public void addSubject(Subject subject) {
        subjectList.add(subject);
    }

    public void addMajor(Major major) {
        majorList.add(major);
    }

    public Subject findSubjectByCode(String subjectCode) {
        for (Subject subject : subjectList) {
            if (subject.getSubjectCode().equals(subjectCode)) {
                return subject;
            }
        }
        return null;
    }

    public void readSubjects(String fileName) throws IOException {
        try (BufferedReader input = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = input.readLine()) != null) {
                String[] values = line.split("/");
                if (values.length == 3) {
                    Subject subject = new Subject(values[0], values[1], Float.parseFloat(values[2]));
                    addSubject(subject);
                }
            }
        }
    }

    public void readMajors(String fileName) throws IOException {
        try (BufferedReader input = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = input.readLine()) != null) {
                String[] values = line.split("/");
                if (values.length == 2) {
                    Major major = new Major(values[0], values[1]);
                    addMajor(major);
                }
            }
        }
    }

    public void readExams(String fileName) throws IOException {
        try (BufferedReader input = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = input.readLine()) != null) {
                String[] values = line.split("/");
                if (values.length >= 3) {
                    String studentCode = values[0];
                    String subjectCode = values[1];
                    int score = Integer.parseInt(values[2]);
                    Subject subject = findSubjectByCode(subjectCode);
                    if (subject != null) {
                        Student student = findOrCreateStudent(studentCode);
                        student.addLesson(new Lessons(subject, score));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Exams file: " + e.getMessage());
            throw e;
        }
    }

    public Student findOrCreateStudent(String studentCode) {
        for (Student student : studentList) {
            if (student.getStudentCode().equals(studentCode)) {
                return student;
            }
        }
        Student newStudent = new Student(studentCode);
        addStudent(newStudent);
        return newStudent;
    }

    public void displaySubjects() {
        System.out.println("Subject List:");
        for (Subject subject : subjectList) {
            System.out.println(subject.getSubjectCode() + " - " + subject.getSubjectName() + " (" + subject.getCredit() + " credits)");
        }
        System.out.println("------------------------------------------------");
    }

    public void displayMajors() {
        System.out.println("Major List:");
        for (Major major : majorList) {
            System.out.println(major.getMajorCode() + " - " + major.getMajorName());
        }
        System.out.println("------------------------------------------------");
    }

    public String displayAverageGPAAndFailingStudents() {
        StringBuilder result = new StringBuilder();
        failingAllCount = 0; // reset counter

        if (studentList.isEmpty()) {
            return "No students available to calculate GPA.\n";
        }

        float totalGPA = 0.0f;
        for (Student student : studentList) {
            totalGPA += student.getGPA();
        }

        float averageGPA = totalGPA / studentList.size();
        result.append("Average GPA of all students: ")
              .append(String.format("%.2f", averageGPA)).append("\n");

        for (Student student : studentList) {
            result.append("Student Code: ").append(student.getStudentCode())
                  .append(" ---- GPA: ").append(String.format("%.2f", student.getGPA()))
                  .append("\n");
        }
        result.append("------------------------------------------------\n");

        result.append("Students with 3 or more failing grades:\n");
        boolean hasFailingStudents = false;

        for (Student student : studentList) {
            if (student.getFailedSubjectsCount() >= 3) {
                hasFailingStudents = true;
                failingAllCount++;

                result.append("Student Code: ").append(student.getStudentCode()).append("\n")
                      .append("GPA: ").append(String.format("%.2f", student.getGPA())).append("\n")
                      .append("Failed Subjects Count: ").append(student.getFailedSubjectsCount()).append("\n");

                for (Lessons lesson : student.getLessons()) {
                    if (lesson.hasFailed()) {
                        result.append("Failed Subject: ").append(lesson.getLearned().getSubjectName())
                              .append(" | Score: ").append(lesson.getScore()).append("\n");
                    }
                }
                result.append("------------------------------------------------\n");
            }
        }

        if (!hasFailingStudents) {
            result.append("No students with 3 or more failing grades.\n");
        }

        return result.toString();
    }

    public void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            System.out.println("Writing data to file: " + fileName);
            writer.write(content);
            writer.flush();
            System.out.println("Data successfully written to the file.");
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found. The specified file or directory does not exist: " + fileName);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

   
    public static void main(String[] args) {
        try {
            Registration2 registration = new Registration2();

            registration.readMajors("C:/Users/anarg/Desktop/Assignment/Professions.txt");
            registration.readSubjects("C:/Users/anarg/Desktop/Assignment/Subjects.txt");
            registration.readExams("C:/Users/anarg/Desktop/Assignment/Exams.txt");

            registration.displaySubjects();
            registration.displayMajors();

            String result = registration.displayAverageGPAAndFailingStudents();
            System.out.println(result);

            File outputFile = new File("C:/Users/anarg/Desktop/Assignment/Result.txt");
            outputFile.getParentFile().mkdirs(); // create folder if missing
            registration.writeToFile(outputFile.getAbsolutePath(), result);

            System.out.println("All failed student count: " + registration.failingAllCount);
               } 
            catch (IOException e) {
            e.printStackTrace();
            }
        }
}