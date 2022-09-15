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
        view.write(String.format("Enter %s to view developers by project", GetDevelopers.GET_DEVELOPERS));
        view.write(String.format("Enter %s to exit program", Exit.EXIT));
    }
}
