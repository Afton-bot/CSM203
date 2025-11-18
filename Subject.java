class Subject {
    private String subjectCode;
    private String subjectName;
    private float credit;

    public Subject(String subjectCode, String subjectName, float credit) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credit = credit;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public float getCredit() {
        return credit;
    }
}