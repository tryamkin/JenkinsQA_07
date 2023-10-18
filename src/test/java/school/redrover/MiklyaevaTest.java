package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MiklyaevaTest {
  @Test
  public void addingProductToTheCartTest() throws InterruptedException {
    WebDriver driver = new ChromeDriver();
    try {
      // Зайти на сайт
      driver.get("https://www.vivi-cosmetics.ru/");

      //Найти элемент Сухая кожа и кликнуть
      WebElement drySkin = driver.findElement(By.linkText("Сухая кожа"));
      String value = drySkin.getText();
      Assert.assertEquals(value, "Сухая кожа");
      drySkin.click();

      // При открытии раздела сухая кожа, проверить, что на странице отображается название "Сухая кожа"
      WebElement drySkinSection = driver.findElement(By.linkText("Сухая кожа"));
      String value1 = drySkinSection.getText();
      Assert.assertEquals(value1, "Сухая кожа");

      // Найти определенный крем, сравнить он ли, если он, то кликнуть
      WebElement cream = driver.findElement(By.linkText("Крем для лица с коллагеном увлажняющий ENOUGH Ultra X10 Collagen Pro Marine Cream 50 мл"));
      String value2 = cream.getText();
      Assert.assertEquals(value2, "Крем для лица с коллагеном увлажняющий ENOUGH Ultra X10 Collagen Pro Marine Cream 50 мл");
      cream.click();

      Thread.sleep(2000);

      //Проверить на странице, что открылся профиль того самого крема
      WebElement listCream = driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[2]/form/div[3]/h1"));
      String value3 = listCream.getText();
      Assert.assertEquals(value3, "Крем для лица с коллагеном увлажняющий ENOUGH Ultra X10 Collagen Pro Marine Cream 50 мл");

      //Нажать на кнопку добавить в карзину
      WebElement buttonAddToCart = driver.findElement(By.xpath("//*[@id=\"product-detail-buy-area\"]/div/div/button"));
      buttonAddToCart.click();

      //Подождать чтобы товар загрузил количество в корзине
      Thread.sleep(5000);

      //Нажать  на кнопку "перейти" (в корзину)
      WebElement goToBasket = driver.findElement(By.xpath("//*[@id=\"product-detail-buy-area\"]/div/div/div/a/span[2]"));
      String value4 = goToBasket.getText();
      Assert.assertEquals(value4, "Перейти");
      goToBasket.click();

      Thread.sleep(3000);

      //Перейти на страницу корзины, проверить что отображается страница с называнием "Корзина"
      WebElement listBasket = driver.findElement(By.className("heading"));
      String value5 = listBasket.getText();
      Assert.assertEquals(value5, "Корзина");

      //Проверить что в корзине отображается тот самый крем
      WebElement displayOfGoods = driver.findElement(By.xpath("/html/body/div[2]/main/div/div/form/div[1]/div/a"));
      String value6 = displayOfGoods.getText();
      Assert.assertEquals(value6, "Крем для лица с коллагеном увлажняющий ENOUGH Ultra X10 Collagen Pro Marine Cream 50 мл");

      Thread.sleep(3000);

      //Проверить что на странице корзины отображается количество товара = 1
      WebElement countGoods = driver.findElement(By.xpath("/html/body/div[2]/main/div/div/form/div[1]/div/div[3]/div/input"));
      String value7 = countGoods.getAttribute("value");
      Assert.assertEquals(value7, "1");
    }
    finally {
      driver.quit();
    }
  }
  @Test
  public void fillingOutTheRegistrationForm() {
    WebDriver driver = new ChromeDriver();
    try { //Зайти на сайт
      driver.get("https://www.vivi-cosmetics.ru/");

      //Найти раздел "Личный кабинет" и кликнуть
      WebElement personalAccount = driver.findElement(By.linkText("Личный кабинет"));
      String value = personalAccount.getText();
      Assert.assertEquals(value, "Личный кабинет");
      personalAccount.click();

      //Найти на странице кнопку зарегистрироваться
      WebElement buttonRegistration = driver.findElement(By.linkText("Зарегистрироваться"));
      String value2 = buttonRegistration.getText();
      Assert.assertEquals(value2, "Зарегистрироваться");
      buttonRegistration.click();

      //Проверить, что открылась страница регистрации
      WebElement listRegistration = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div/div/div[1]/h1"));
      String value3 = listRegistration.getText();
      Assert.assertEquals(value3, "Регистрация");

      //Заполнить поля для регистрации
      WebElement surname = driver.findElement(By.id("client_surname"));
      surname.sendKeys("Кристина");

      WebElement name = driver.findElement(By.id("client_name"));
      name.sendKeys("Петрова");

      WebElement telephone = driver.findElement(By.id("client_phone"));
      telephone.sendKeys("79514682923");

      WebElement email = driver.findElement(By.id("client_email"));
      email.sendKeys("miklyaeva99@mail.ru");

      WebElement password = driver.findElement(By.id("client_password"));
      password.sendKeys("1234567890");

      WebElement repeatPassword = driver.findElement(By.id("client_password_confirmation"));
      repeatPassword.sendKeys("1234567890");
    }
    finally {
      driver.quit();
    }
  }
}
