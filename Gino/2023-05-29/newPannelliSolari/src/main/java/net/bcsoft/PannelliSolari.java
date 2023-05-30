package net.bcsoft;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class PannelliSolari
{
    public static void main(String[] args)
    {
        try
        {
            GestoreReport gestoreReport = new GestoreReport();

            String reportPath = args[1].endsWith(File.separator) ? args[1] : args[1] + File.separator;

            gestoreReport.produciReport(args[0], reportPath);
        }
        catch (SQLException | IOException e)
        {
            e.printStackTrace();
        }
    }
}