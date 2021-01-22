package com.example.scraper;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

@RestController
public class SenateController {

    private static List<Senator> getSenators(List<WebElement> lst) {
        List<Senator> senators = new ArrayList<>();
        for (WebElement row: lst) {
            List<WebElement> components = row.findElements(By.tagName("td"));
            String name = components.get(0).findElement(By.tagName("a")).getText();
            name = name.substring(0, name.indexOf(" ("));
            String state = components.get(1).getText();
            String party = components.get(2).getText();
            String classNum = components.get(3).getText();
            senators.add(new Senator(name, state, party, classNum));
        }
        return senators;
    }

    @GetMapping("/senate")
    public Senator[] senate() {
        ChromeOptions options = new ChromeOptions();

        // Add arguments, method uses varargs
        // options from https://developers.google.com/web/updates/2017/04/headless-chrome
        options.addArguments("--headless",
            "--disable-gpu");
        WebDriver driver = new ChromeDriver(options);

        driver.navigate().to("https://www.senate.gov/senators/index.htm");

        WebElement wrapperDiv = driver.findElement(By.id("listOfSenators_wrapper"));

        WebElement btns = wrapperDiv.findElement(By.className("dt-buttons"));

        WebElement showBtn = btns.findElement(By.className("buttons-collection"));

        showBtn.click();

        WebElement changeDiv = btns.findElement(By.className("dt-button-collection"));

        // Select 100
        List<WebElement> opts = changeDiv.findElements(By.tagName("button"));
        opts.get(2).click();

        WebElement tableBody = wrapperDiv.findElement(By.tagName("tbody"));

        List<WebElement> senatorRows = tableBody.findElements(By.tagName("tr"));

        List<Senator> senators = getSenators(senatorRows);

        Senator[] result = new Senator[senators.size()];
        return senators.toArray(result);
    }
}