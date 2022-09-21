package utils;

import services.CompanyService;

import java.sql.SQLException;

public class CheckCompanies {

    CompanyService companyService = new CompanyService();

    public boolean IsCompanyIdExists(Integer id) throws SQLException {
        for (int i = 0; i < companyService.companiesList().size(); i++) {
            if(companyService.companiesList().get(i).getCompanyId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
