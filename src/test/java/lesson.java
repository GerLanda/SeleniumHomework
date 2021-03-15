import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class lesson {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.avito.ru");

        driver.manage().window().maximize();
        System.out.println("Разрешение окна: - максимальное.");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.println("Получаем все значения в списки товаров");
        Select category = new Select(driver.findElement(By.xpath("//select[@id='category']")));
        category.getOptions().forEach(option->{
            System.out.println("Value = "+option.getAttribute("value")+";Text = "+option.getText());
        });
        category.selectByVisibleText("Оргтехника и расходники");
        System.out.println("Выбор в категории товаров - \"Оргтехника и расходники\"");

        WebElement productSearch = driver.findElement(By.xpath("//input[@id='search']"));
        productSearch.sendKeys("Принтер");
        System.out.println("В поисковой строке продукта ввод: \"Принтер\"");

        WebElement citySearch = driver.findElement(By.xpath("//div[@class='main-text-2PaZG']"));
        citySearch.click();
        System.out.println("Клик по выбору города");

        WebElement citySearchLine = driver.findElement(By.xpath("//input[@class='suggest-input-3p8yi']"));
        citySearchLine.sendKeys("Владивосток");
        System.out.println("В поисковой строке города ввод:\"Владивосток\"");

        WebElement citySelection = driver.findElement(By.xpath("//ul[@class='suggest-suggests-bMAdj']/li[@class='suggest-suggest-1wwEm text-text-1PdBw text-size-m-4mxHN']"));
        citySelection.click();
        System.out.println("Клик по предложенному значению");

        WebElement citySearchButton = driver.findElement(By.xpath("//button[@class='button-button-2Fo5k button-size-m-7jtw4 button-primary-1RhOG']"));
        citySearchButton.click();
        System.out.println("Клик по кнопке поиска объявлений в выбранном городе");

        WebElement element = driver.findElement(By.xpath("//div[@data-marker='delivery-filter/container']/label[@class='checkbox-checkbox-7igZ6 checkbox-size-s-yHrZq']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        System.out.println("Скрол вниз до чекбокса");

        if (!element.isSelected()) {
            element.click();
        }
        System.out.println("Устанавливаем чекбокс активным");

        Select sortPrice = new Select(driver.findElement(By.xpath("//div[@class='sort-select-3QxXG select-select-box-3LBfK select-size-s-2gvAy']/select[@class='select-select-3CHiM']")));
        sortPrice.getOptions().forEach(option->{
            System.out.println("Value = "+option.getAttribute("value")+";Text = "+option.getText());
        });
        sortPrice.selectByVisibleText("Дороже");
        System.out.println("Значение сортировки: \"Дороже\"");

        System.out.println("Получаем первые три принтера");

        List<WebElement> webElements = driver.findElements(By.xpath("//div[@class='items-items-38oUm']/div[@data-marker='item']"));
        for (int i = 0; i < 3; i++) {
            System.out.println("Название " + webElements.get(i).findElement(By.xpath(".//h3[@class='title-root-395AQ iva-item-title-1Rmmj title-list-1IIB_ title-root_maxHeight-3obWc text-text-1PdBw text-size-s-1PUdo text-bold-3R9dt']")).getText());
            System.out.println("Цена " + webElements.get(i).findElement(By.xpath(".//span[@class='price-text-1HrJ_ text-text-1PdBw text-size-s-1PUdo']")).getText());
        }
        driver.quit();

    }
}
