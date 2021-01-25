package duke.command;

import duke.TaskList.TaskList;
import duke.UI.UI;
import duke.data.DataStorage;
import duke.exception.DukeException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AddCommand extends Command {

    public AddCommand(String input, TaskList taskList) {
        super(input,taskList);
    }

    public TaskList execute(TaskList tasklist, UI ui, DataStorage storage, String type,
                            LocalDate dueDate, LocalTime startTime, LocalTime endTime) throws DukeException {
        switch (type) {
        case ("todo"):
            tasklist.addToDo(this.input);
            break;
        case ("deadlines"):
            System.out.println("hehe " + this.input);
            tasklist.addDeadline(this.input, dueDate, startTime);
           break;
        case ("events"):
            tasklist.addEvent(this.input, dueDate, startTime, endTime);
          break;
       }

        ArrayList<Task> taskArrayList = tasklist.getTaskListArray();
        int curr_size = taskArrayList.size();

        ui.displayAddedTaskMessage(tasklist.getTask(curr_size-1), curr_size);
       storage.save(tasklist.getTaskListArray());

       return tasklist;
    }

}
