package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Stores all type of task
 */
public class Task {
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    protected String description;
    protected boolean isCompleted;

    /**
     * Empty constructor
     */
    public Task(){

    }

    /** Constructor to create task object
     * @param title
     */
    public Task(String title) {
        this.description = title;
        this.isCompleted = false;
    }

    /**Constructor to create task object for retrieval of task from data file
     * @param title
     * @param b
     */
    public Task(String title, boolean b) {
        this.description = title;
        this.isCompleted = b;
    }

    /**
     * Set task to completed
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    /** Return cross if task is completed
     * @return
     */
    public String getStatus() {
        return (this.isCompleted ? "\u2713" : "\u2718");
    }

    /** Return description of task
     * @return
     */
    public String getTaskName() {
        return this.description;
    }

    /** Return customized representation of task to add to data file
     * @return
     */
    public String changeFormat() {
        return "," + this.isCompleted + "," + this.getTaskName();
    }

    /** Return the string version of task from data file to task objects
     * @param input
     * @return
     */
    public Task changeToTaskFormat(String input) {

        assert input!= null:"Task identifier from data file should be specified";
        if (input.charAt(0) == 'T') {
            String[] tasks = input.split(",");
            return new ToDo(tasks[2], Boolean.parseBoolean(tasks[1]));
        } else if (input.charAt(0) == 'D') {
            String[] tasks = input.split(",");

            LocalDate date = LocalDate.parse(tasks[3], dateFormatter);
            LocalTime startTime = LocalTime.parse(tasks[4], timeFormatter);

            return new Deadline(tasks[2], Boolean.parseBoolean(tasks[1]), date, startTime);
        } else if (input.charAt(0) == 'E') {
            String[] tasks = input.split(",");

            LocalDate date = LocalDate.parse(tasks[3], dateFormatter);
            LocalTime startTime = LocalTime.parse(tasks[4], timeFormatter);
            LocalTime endTime = LocalTime.parse(tasks[5], timeFormatter);

            return new Event(tasks[2], Boolean.parseBoolean(tasks[1]), date, startTime, endTime);
        } else {
            return new Task();
        }
    }

    /** Check for duplicate of task
     * @return boolean
     */

    public boolean isSameTask(Task t){
        return this.getTaskName().equals(t.getTaskName());
    }

    /** Print customized representation of task to user
     * @return String
     */
    @Override
    public String toString() {
        return this.getTaskName() + "[" + this.getStatus() + "] ";
    }
}
