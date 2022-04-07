package Regression;
 
 
import java.io.IOException;
 
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
 
public class Listeners extends Base implements ITestListener{
 
    ExtentReports extent = Base.getreport();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);

    }
 
    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        extentTest.get().pass(MarkupHelper.createLabel(result.getName()+"Test Case Passed", ExtentColor.GREEN));



    }
    @Override
    public void onTestFailure(ITestResult result) {
        // capture Screenshot

        WebDriver driver= null;
        String methodname= result.getMethod().getMethodName();
        try {
        driver=    (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            extentTest.get().addScreenCaptureFromPath(getScreenShot(methodname,driver) , result.getMethod().getMethodName());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        extentTest.get().fail(MarkupHelper.createLabel(result.getName()+"Test Case Failed", ExtentColor.RED));
        extentTest.get().fail(result.getThrowable());

        }


 
    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        extentTest.get().skip(MarkupHelper.createLabel(result.getName()+"Test Case Skipped", ExtentColor.YELLOW));
        extentTest.get().skip(result.getThrowable());

    }
 
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }
 
    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }
 
    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
         extent.flush();

    }
 
}