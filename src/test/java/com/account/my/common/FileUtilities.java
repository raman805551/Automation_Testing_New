package com.account.my.common;

import java.io.File;

public class FileUtilities {

    public static boolean verifyFile(String filePath)
    {
        try
        {
            if(filePath.isEmpty()) return false;
            File file = new File(filePath);
            if(file.exists()) return true;
            else return false;
        }
        catch (Exception e)
        {
            System.out.println("Exception occurred while searching for  file" +filePath+ "-->" + e.getMessage() );
            return false;

        }
    }
}
