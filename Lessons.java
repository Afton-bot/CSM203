class Lessons {
    private Subject learned;
    private int score;

    public Lessons(Subject learned, int score) {
        this.learned = learned;
        this.score = score;
    }

    public Subject getLearned() {
        return learned;
    }

    public int getScore() {
        return score;
    }

    public float calculateGPA() {
        if (score >= 90) return 4.0f;
        else if (score >= 80) return 3.0f;
        else if (score >= 70) return 2.0f;
        else if (score >= 60) return 1.0f;
        else return 0.0f;
    }

    public boolean hasFailed() {
        return score < 60;
    }
}
