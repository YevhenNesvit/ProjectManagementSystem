package command;

import services.SkillService;
import utils.CheckSkills;
import view.View;

import java.sql.SQLException;

public class UpdateSkill implements Command {
    public static final String UPDATE_SKILL = "update skill";
    private final View view;
    SkillService skillService = new SkillService();
    CheckSkills checkSkills = new CheckSkills();

    public UpdateSkill(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_SKILL);
    }

    @Override
    public void execute() {
        String name;
        String skillLevel;
        int id;
        try {
            while (true) {
                try {
                    view.write("Please, enter name to update: ");
                    name = view.read();
                    view.write("Please, enter skill_level to update: ");
                    skillLevel = view.read();
                    view.write("Please, enter skill_id: ");
                    id = Integer.parseInt(view.read());
                    if (checkSkills.IsSkillIdExists(id)) {
                        skillService.updateDeveloper(name, skillLevel, id);
                        view.write("Skill with id " + id + " successfully updated");
                        break;
                    } else {
                        System.out.println("Skill id doesn't exists");
                    }
                } catch (NumberFormatException e) {
                    view.write("Invalid value. Use digits");
                }
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
