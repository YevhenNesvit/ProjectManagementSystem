package command;

import services.SkillService;
import view.View;

import java.sql.SQLException;

public class GetSkillById implements Command {
    public static final String GET_SKILL_BY_ID = "skill by id";
    private final View view;
    SkillService skillService = new SkillService();
    public GetSkillById(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_SKILL_BY_ID);
    }

    @Override
    public void execute() {
        int id;
        while (true) {
            try {
                view.write("Please, enter skill id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            view.write("For id " + id + " skill is:\n" + skillService.skillById(id));
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
