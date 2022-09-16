package command;

import repository.DeveloperRepository;
import view.View;

import java.sql.SQLException;

public class GetDevsBySkillName implements Command {
    public static final String GET_DEVS_BY_SKILL_NAME = "devs by skill name";
    private final View view;
    DeveloperRepository developerRepository = new DeveloperRepository();

    public GetDevsBySkillName(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_DEVS_BY_SKILL_NAME);
    }

    @Override
    public void execute() {
        String name;
        view.write("Please, enter skill name like Java, C++, C#, JS: ");
        while (true) {
            name = view.read();
            if (name.equals("Java") || name.equals("C++") || name.equals("C#") || name.equals("JS")) {
                try {
                    view.write("For skill name " + name + " list of developers is:\n" + developerRepository.developersBySkillName(name));
                } catch (
                        SQLException e) {
                    e.getStackTrace();
                }
            } else {
                view.write("Invalid value. Please enter Java, C++, C# or JS");
            }
            break;
        }
    }
}
