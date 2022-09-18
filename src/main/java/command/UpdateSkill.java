package command;

import services.SkillRepository;
import view.View;

import java.sql.SQLException;

public class UpdateSkill implements Command {
    public static final String UPDATE_SKILL = "update skill";
    private final View view;
    SkillRepository skillRepository = new SkillRepository();

    public UpdateSkill(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_SKILL);
    }

    @Override
    public void execute() {
        String columnName;
        String newValue;
        int id;
        while (true) {
            try {
                view.write("Please, enter columnName to update: name or skill_level ");
                columnName = view.read();
                view.write("Please, enter new value: ");
                newValue = view.read();
                view.write("Please, enter skill_id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            skillRepository.updateSkill(columnName, newValue, id);
            view.write("Skill with id " + id + " successfully updated");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
