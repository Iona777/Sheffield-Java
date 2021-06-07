package HelperClasses;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigHelper
{
        Properties configFile;

        public String getProperty(String key)
        {
            try
            {
                Path target = Paths.get("src/test/java");
                String abspath = target.toAbsolutePath().toString();
                String fileName = abspath+ "/resources/config.properties";
                FileReader reader = new FileReader(fileName);
                Properties prop=new Properties();
                prop.load(reader);

                return prop.getProperty(key);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return "Exception encountered";

        }
}
