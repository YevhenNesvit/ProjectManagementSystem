package command;

import services.SkillService;
import view.View;

import java.sql.SQLException;

public class GetSkills implements Command {
    public static final String GET_SKILLS = "get skills";
    private final View view;
    SkillService skillService = new SkillService();

    public GetSkills(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_SKILLS);
    }

    @Override
    public void execute() {
        try {
            view.write("List of all skills is:\n" + skillService.skillsList());
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
