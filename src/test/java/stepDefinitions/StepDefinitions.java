package stepDefinitions;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.support.PageFactory;
import util.WebConnector;

public abstract class StepDefinitions {

    /*public StepDefinitions() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebConnector.getInstance().getBrowser()), this);
    }*/

    public StepDefinitions() {
        PageFactory.initElements(WebConnector.getInstance().getBrowser(), this);
    }

    public int totalColumnsOfData(DataTable table) {
        return table.cells().get(0).size();
    }

    public int totalRowsOfData(DataTable table) {
        return table.cells().size();
    }
}