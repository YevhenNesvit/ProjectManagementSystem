package command;

import services.DeveloperService;
import view.View;

import java.sql.SQLException;

public class GetDevsBySkillLevel implements Command {
    public static final String GET_DEVS_BY_SKILL_LEVEL = "devs by skill level";
    private final View view;
    DeveloperService developerService = new DeveloperService();

    public GetDevsBySkillLevel(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_DEVS_BY_SKILL_LEVEL);
    }

    @Override
    public void execute() {
        String level;
        view.write("Please, enter skill level like Junior, Middle, Senior: ");
        while (true) {
            level = view.read();
            if (level.equals("Junior") || level.equals("Middle") || level.equals("Senior")) {
                try {
                    view.write("For skill level " + level + " list of developers is:\n" + developerService.developersBySkillLevel(level));
                } catch (
                        SQLException e) {
                    e.getStackTrace();
                }
                break;
            } else {
                view.write("Invalid value. Please enter Junior, Middle or Senior");
            }
        }
    }
}
