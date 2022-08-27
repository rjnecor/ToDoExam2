Prerequisites:
-	Path environment variables setup for Maven and JDK

Additional notes:
- This runs in headless mode. just remove or comment the addArgument line in the framework.core.webDriverChrome to disable.
- Known issue. I wasn't able to find the cause but during debug the line WebDriver driver = new ChromeDriver(opts); launches two browsers, 
one with the "data:," url.

