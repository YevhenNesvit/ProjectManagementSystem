package command;

import view.View;

public class Help implements Command {
    private static final String HELP = "help";
    private final View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(HELP);
    }

    @Override
    public void execute() {
        view.write(String.format("Enter %s to see all commands", Help.HELP));
        view.write(String.format("Enter %s to view salary by project", GetSalary.GET_SALARY));
        view.write(String.format("Enter %s to view developers by project", GetDevsByProject.GET_DEVS_BY_PROJECT));
        view.write(String.format("Enter %s to view developers by skill name", GetDevsBySkillName.GET_DEVS_BY_SKILL_NAME));
        view.write(String.format("Enter %s to view developers by skill level", GetDevsBySkillLevel.GET_DEVS_BY_SKILL_LEVEL));
        view.write(String.format("Enter %s to view projects list", GetProjects.GET_PROJECTS));
        view.write(String.format("Enter %s to update developer", UpdateDeveloper.UPDATE_DEVELOPER));
        view.write(String.format("Enter %s to update project", UpdateProject.UPDATE_PROJECT));
        view.write(String.format("Enter %s to update customer", UpdateCustomer.UPDATE_CUSTOMER));
        view.write(String.format("Enter %s to exit program", Exit.EXIT));
    }
}
