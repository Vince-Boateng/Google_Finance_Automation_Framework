package com.google.finance.pages;

import com.google.finance.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class GoogleFinancePage extends BasePage {

  //  private Logger log = LogManager.getLogger(GoogleFinancePage.class);
  final Logger log = LoggerFactory.getLogger(GoogleFinancePage.class.getName());


    public GoogleFinancePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//div[@class='COaKTb']")
    private List<WebElement> allGoogleFinancePageStockSymbolsList;

    @FindBy(xpath ="//ul[@class='sbnBtf']/li//div[@class='COaKTb']")
    private List<WebElement> youMayBeInterestedInStockSymbolsList;



    public List<String> getPageStockSymbolsInTheYouMayBeInterestedInSection(){
        List<String> stockSymbolsList = new ArrayList<>();
        try {
            for (WebElement eachStock : youMayBeInterestedInStockSymbolsList) {
                if (!stockSymbolsList.contains(eachStock.getText())) {
                    stockSymbolsList.add(eachStock.getText());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("The list of stock symbols in the \'You may be interested in info\' section on the Google Finance page is as follows: "+ stockSymbolsList);
        return stockSymbolsList;
    }


}
