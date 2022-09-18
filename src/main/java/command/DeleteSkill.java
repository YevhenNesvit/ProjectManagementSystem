package command;

import services.SkillService;
import view.View;

import java.sql.SQLException;

public class DeleteSkill implements Command {
    public static final String DELETE_SKILL = "delete skill";
    private final View view;
    SkillService skillService = new SkillService();

    public DeleteSkill(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_SKILL);
    }

    @Override
    public void execute() {
        int id;
        while (true) {
            try {
                view.write("Please, enter skill id to delete: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            skillService.deleteSkill(id);
            view.write("Skill with id " + id + " successfully deleted");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
