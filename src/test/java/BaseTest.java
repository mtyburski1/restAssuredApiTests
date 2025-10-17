
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup() {
        System.out.println("Running API tests with TestNG + REST Assured");
    }
}
