set HERE=D:\Projects\Automation\Workspace\CashbookPL
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_60
set PATH=%JAVA_HOME%\jre\bin;%JAVA_HOME%\bin;%PATH%
set SELENIUM_VERSION=2.52.0
set CHROME_VERSION=chrome-48.0.2564.109
set HUB_URL=http://172.25.32.36:4444/grid/register
set CHROME_DRIVER_LOC=D:/Projects/Automation/Workspace/Library/chromedriver.exe
start java -jar selenium-server-standalone-%SELENIUM_VERSION%.jar -role hub
start java -jar selenium-server-standalone-%SELENIUM_VERSION%.jar -role node -Dwebdriver.chrome.driver= %CHROME_DRIVER_LOC% -hub %HUB_URL% -port 5556