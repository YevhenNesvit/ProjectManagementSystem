package command;

import services.SkillService;
import view.View;

import java.sql.SQLException;

public class CreateSkill implements Command {
    public static final String CREATE_SKILL = "create skill";
    private final View view;
    SkillService skillService = new SkillService();

    public CreateSkill(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(CREATE_SKILL);
    }

    @Override
    public void execute() {
        int id;
        String name;
        String skill_level;
        while (true) {
            try {
                view.write("Please, enter skill_id of new skill: ");
                id = Integer.parseInt(view.read());
                view.write("Please, enter name of new skill: ");
                name = view.read();
                view.write("Please, enter skill_level of new skill: ");
                skill_level = view.read();
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            skillService.createSkill(id, name, skill_level);
            view.write("Skill with id " + id + " successfully created");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
