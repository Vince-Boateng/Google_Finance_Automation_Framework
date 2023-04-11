package com.google.finance.tests;

import com.google.finance.pages.GoogleFinancePage;
import com.google.finance.utilities.ExcelDataReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleFinancePageTests extends TestBase {

  //  private Logger log = LogManager.getLogger(GoogleFinancePageTests.class.getName());
  final Logger log = LoggerFactory.getLogger(GoogleFinancePageTests.class.getName());


    GoogleFinancePage googleFinancePage = new GoogleFinancePage();
    ExcelDataReader excelDataReader = new ExcelDataReader();

    private List<String> expectedStockSymbols = Arrays.asList("NFLX", "MSFT", "TSLA");

    //2. Verify the page is loaded by asserting the page title
    @Test(priority = 1)
    public void googleFinancePageTitleVerificationTest() {
     ArrayList<String> testData =  excelDataReader.getData("googleFinancePageTitleVerificationTest", "GoogleFinanceTestData");
      //  String expectedPageTitle = "Google Finance - Stock Market Prices, Real-time Quotes & Business News";
        String expectedPageTitle = testData.get(1);
        log.info("The expected title of the current page is: " + expectedPageTitle);
        String actualPageTitle = googleFinancePage.getTitle();
        log.info("The actual title of the current page is: " + actualPageTitle);
        Assert.assertEquals(actualPageTitle, expectedPageTitle, "The page title is incorrect.");
    }


    @Test(priority = 2)
    public void testGoogleFinanceStockSymbols() {
//3. Retrieve the stock symbols listed under the section "You may be interested in info"
        List<String> actualYouMayBeInterestedInstockSymbolsList = googleFinancePage.getPageStockSymbolsInTheYouMayBeInterestedInSection();

        log.info("The retrieved stock symbols listed under the section \'You may be interested in info\' are as follows: " + actualYouMayBeInterestedInstockSymbolsList);
        System.out.println("The retrieved stock symbols listed under the section \'You may be interested in info\' are as follows: " + actualYouMayBeInterestedInstockSymbolsList);


      /*
       4. Compare the stock symbols retrieved from (3) with expectedStockSymbols
       5. Print all stock symbols that are in (3) but not in expectedStockSymbols
       */
        List<String> stocksIn3ButNotInExpectedStockSymbols = new ArrayList<>();

        List<String> stocksInExpectedStockSymbolsButNotIn3 = new ArrayList<>();

        for (String each : actualYouMayBeInterestedInstockSymbolsList) {
            if (!expectedStockSymbols.contains(each)) {
                stocksIn3ButNotInExpectedStockSymbols.add(each);
            }
        }

        log.info("The Stock symbols that are in (3) but not in expectedStockSymbols are: " + stocksIn3ButNotInExpectedStockSymbols);
        System.out.println("The Stock symbols that are in (3) but not in expectedStockSymbols are: " + stocksIn3ButNotInExpectedStockSymbols);


        // 6. Print all stock symbols that are in expectedStockSymbols but not in (3)

        for (String each : expectedStockSymbols) {
            if (!actualYouMayBeInterestedInstockSymbolsList.contains(each)) {
                stocksInExpectedStockSymbolsButNotIn3.add(each);
            }
        }

        log.info("The Stock symbols that are in expectedStockSymbols but are not in (3) are: " + stocksInExpectedStockSymbolsButNotIn3);
        System.out.println("The Stock symbols that are in expectedStockSymbols but are not in (3) are: " + stocksInExpectedStockSymbolsButNotIn3);

    }

}
