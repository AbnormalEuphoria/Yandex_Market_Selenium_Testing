package ru.yandexMarket;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.NoSuchElementException;



import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class FirstTest {

    @Test
    public void firstTest() throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        // Браузер на полный экран
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Actions move = new Actions(driver);

        // Перейти на страницу маркета и нажать на каталог
        driver.get("https://market.yandex.ru/");
        WebElement catalog = driver.findElement(By.cssSelector(".V9Xu6.button-focus-ring._1KI8u.Lfy7z._3iB1w._35Vhw"));
        catalog.click();

        // Перейти на вкладку смартфоны
        WebElement smartphones = driver.findElement(By.xpath("//a[contains(text(),'Смартфоны')]"));
        smartphones.click();

        // Выбор производителей
        WebElement apple = driver.findElement(By.xpath("(//span[@class='_1Mp5C'])[1]"));
        apple.click();

        WebElement xiaomi = driver.findElement(By.xpath("(//span[@class='_1Mp5C'])[2]"));
        xiaomi.click();

        WebElement samsung = driver.findElement(By.xpath("(//span[@class='_1Mp5C'])[3]"));
        samsung.click();

        WebElement realme = driver.findElement(By.xpath("(//span[@class='_1Mp5C'])[4]"));
        realme.click();

        WebElement honor = driver.findElement(By.xpath("(//span[@class='_1Mp5C'])[5]"));
        honor.click();

        // Ограничение на цену и размер экрана
        WebElement max_price = driver.findElement(By.xpath("//span[@data-auto='filter-range-max']//input[@class='_3qxDp _1R_cW']"));
        max_price.sendKeys("20000");

        WebElement min_inches = driver.findElement(By.xpath("(//input[@class='_3qxDp _37_h4'])[2]"));
        min_inches.sendKeys("3");

        // Списковый вид просмотра
        WebElement list_selector = driver.findElement(By.xpath("//input[@value='list']"));
        list_selector.click();

        // Пауза для прогрузки
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Поиск и вывод первого элемента
        WebElement first_element = driver.findElement(By.xpath("(//span[@class='_1E10J _2o124 _1zh3_'])[1]"));
        String first_smartphone_name = first_element.getText();
        System.out.println(first_smartphone_name);


        // Сортировка по рейтингу
        WebElement rating_sort = driver.findElement(By.xpath("(//button[contains(text(),'по рейтингу')])[1]"));
        rating_sort.click();

        // Переход в конец страницы
        WebElement next_page = driver.findElement(By.xpath("//span[@class='_3e9Bd']"));
        move.moveToElement(next_page).build().perform();

        // Найти элемент "Показать еще"
        WebElement show_more = driver.findElement(By.xpath("//button[@data-auto='pager-more']"));



        // Пауза для прогрузки
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //WebElement second_look_for_smartphone = driver.findElement(By.linkText(first_smartphone_name));
        //second_look_for_smartphone.click();

        boolean found = false;

        //Если смартфон не найдет, то нажать на "Показать еще"
        while (found == false){

            try {
                //Поиск нужного элемента на странице по названию и переход на него
                WebElement second_look_for_smartphone = driver.findElement(By.linkText(first_smartphone_name));
                move.moveToElement(second_look_for_smartphone).click().build().perform();

                found = true;

            } catch (NoSuchElementException e) {
                //Переход на новую страницу (с прогрузкой всех элементов), если мы не находим нужный
                Thread.sleep(1000);
                move.moveToElement(show_more).click().build().perform();
                Thread.sleep(1000);
                move.moveToElement(show_more).build().perform();
                found = false;

            }
            if (found) {
                break;
            }
        }
        //Переключение на открытую вкладку смартфона
        List<String> change_tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(change_tabs.get(1));

        WebElement found_rating = driver.findElement(By.cssSelector("div[class='_10-ix'] div span[class='ybvaC']"));
        String result = found_rating.getText();

        System.out.println("Рейтинг:" + result);

        driver.quit();




    }

}
