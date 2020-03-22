import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.ServerSocket;

public class AppiumServer {

    private AppiumDriverLocalService appiumService;  //запуск сервиса через команд. строку (start/stop)
    private AppiumServiceBuilder appiumServiceBuilder; //конфигур. 'appiumService' нужными параметрами (ip/port/capability)

    public void startAppiumService(final DesiredCapabilities capabilities) {  //start Service
        appiumServiceBuilder = new AppiumServiceBuilder();  //settings
        appiumServiceBuilder.withIPAddress("127.0.0.1");
        appiumServiceBuilder.usingPort(4723);
        appiumServiceBuilder.withCapabilities(capabilities);
        appiumServiceBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE); //log
        appiumServiceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "debug"); //log
        appiumService = AppiumDriverLocalService.buildService(appiumServiceBuilder); //передали appiumServiceBuilder в класс
        appiumService.start();
    }

    public void shutDownAppiumService() {
        appiumService.stop();
    }

    public boolean isRunning(final int appiumPort) {  // доп. проверки
        boolean isRunning = false;  //default
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(appiumPort); // class. позволяет устанавливать соединение по сети (доступность)
        } catch (final IOException e) {
            isRunning = true;  // appium is running
        }
        finally {          // close all conection
            serverSocket = null;
        }
        return isRunning;
    }

}
