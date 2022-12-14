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
        view.write(String.format("Enter %s to create company", CreateCompany.CREATE_COMPANY));
        view.write(String.format("Enter %s to create customer", CreateCustomer.CREATE_CUSTOMER));
        view.write(String.format("Enter %s to create developer", CreateDeveloper.CREATE_DEVELOPER));
        view.write(String.format("Enter %s to create project", CreateProject.CREATE_PROJECT));
        view.write(String.format("Enter %s to create skill", CreateSkill.CREATE_SKILL));
        view.write(String.format("Enter %s to view all companies", GetCompanies.GET_COMPANIES));
        view.write(String.format("Enter %s to view company by id", GetCompanyById.GET_COMPANY_BY_ID));
        view.write(String.format("Enter %s to view all customers", GetCustomers.GET_CUSTOMERS));
        view.write(String.format("Enter %s to view customer by id", GetCustomerById.GET_CUSTOMER_BY_ID));
        view.write(String.format("Enter %s to view all developers", GetDevs.GET_DEVS));
        view.write(String.format("Enter %s to view developer by id", GetDevById.GET_DEVS_BY_ID));
        view.write(String.format("Enter %s to view developers by project", GetDevsByProject.GET_DEVS_BY_PROJECT));
        view.write(String.format("Enter %s to view developers by skill name", GetDevsBySkillName.GET_DEVS_BY_SKILL_NAME));
        view.write(String.format("Enter %s to view developers by skill level", GetDevsBySkillLevel.GET_DEVS_BY_SKILL_LEVEL));
        view.write(String.format("Enter %s to view projects list", GetProjects.GET_PROJECTS));
        view.write(String.format("Enter %s to view salary by project", GetSalary.GET_SALARY));
        view.write(String.format("Enter %s to view all skills", GetSkills.GET_SKILLS));
        view.write(String.format("Enter %s to view skill by id", GetSkillById.GET_SKILL_BY_ID));
        view.write(String.format("Enter %s to update developer", UpdateDeveloper.UPDATE_DEVELOPER));
        view.write(String.format("Enter %s to update project", UpdateProject.UPDATE_PROJECT));
        view.write(String.format("Enter %s to update customer", UpdateCustomer.UPDATE_CUSTOMER));
        view.write(String.format("Enter %s to update company", UpdateCompany.UPDATE_COMPANY));
        view.write(String.format("Enter %s to update skill", UpdateSkill.UPDATE_SKILL));
        view.write(String.format("Enter %s to delete developer", DeleteDeveloper.DELETE_DEVELOPER));
        view.write(String.format("Enter %s to delete project", DeleteProject.DELETE_PROJECT));
        view.write(String.format("Enter %s to delete company", DeleteCompany.DELETE_COMPANY));
        view.write(String.format("Enter %s to delete customer", DeleteCustomer.DELETE_CUSTOMER));
        view.write(String.format("Enter %s to delete skill", DeleteSkill.DELETE_SKILL));
        view.write(String.format("Enter %s to exit program", Exit.EXIT));
    }
}
