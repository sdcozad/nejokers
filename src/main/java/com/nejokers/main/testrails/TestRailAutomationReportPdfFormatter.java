package com.nejokers.main.testrails;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestRailAutomationReportPdfFormatter {

    private static String BaseDir = "/Jenkins/TestRails/Reports";

    public static void main(String[] args) {

        String host = "pop.gmail.com";
        String port = "995";
        String userName = "qaautocri@gmail.com";
        String password = "QaAutoCri!";

        String saveDirectory = BaseDir + "/EMAIL/Process";

        String smtphost = "smtp.gmail.com";
        String smtpport = "587";

        //message info
        String[] mailTo = {
            "qaautocri@gmail.com"};

        String subject = "Demo-" + dateNow();
        String message = "Automation Report Summary for " + dateNow();

        // attachments
        String[] attachFiles = new String[1];
        attachFiles[0] = BaseDir + "/EMAIL/Output/Demo-" + dateNow() + ".pdf";

        downloadFile(host, port, userName, password, saveDirectory);

        unzipFile();

        filePdf();

        sendAttachment(userName, password, smtphost, smtpport, mailTo, subject, message, attachFiles);

    }

    private static void downloadFile(String host, String port, String userName, String password, String saveDirectory) {
        DownloadEmailAttachements downlad = new DownloadEmailAttachements();
        downlad.setSaveDirectory(saveDirectory);
        downlad.downloadEmailAttachments(host, port, userName, password);
    }

    private static void sendAttachment(String userName, String password, String smtphost, String smtpport, String[] mailTo,
            String subject, String message, String[] attachFiles) {
        SendEmailWithAttachments email = new SendEmailWithAttachments();

        try {
            email.sendEmailWithAttachments(smtphost,
                smtpport,
                userName,
                password,
                mailTo,
                subject,
                message,
                attachFiles);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }

    private static void unzipFile() {

        String destDirectory = BaseDir + "/EMAIL/Unzip";
        File processFolder = new File(BaseDir + "/EMAIL/Process");
        File[] listOfFiles = processFolder.listFiles();

        String zipFilePath = BaseDir + "/EMAIL/Process/" + listOfFiles[0].getName();

        UnzipUtility unzipper = new UnzipUtility();

        try {
            unzipper.unzip(zipFilePath, destDirectory);
            //Delete the file from processing.
            //listOfFiles[0].delete();
            if (listOfFiles.length > 0)
                purgeDirectory(processFolder);
        } catch (Exception ex) {
            // some errors occurred
            ex.printStackTrace();
        }
    }

    private static void filePdf() {
        String command =
            "/usr/local/bin/wkhtmltopdf " + BaseDir + "/EMAIL/Unzip/index.html " + BaseDir
                + "/EMAIL/Output/Demo-" + dateNow() + ".pdf";

        //Delete the old report
        File outputFolder = new File(BaseDir + "/EMAIL/Output");
        File[] listOfFiles = outputFolder.listFiles();
        if (listOfFiles.length > 0)
            purgeDirectory(outputFolder);

        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(command);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Read the output

        BufferedReader reader =
            new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            proc.waitFor();
            //Delete the old Output unzipped files now that the pdf file is created.
            File unzipFolder = new File(BaseDir + "/EMAIL/Unzip");
            File[] listFiles = outputFolder.listFiles();
            if (listFiles.length > 0)
                purgeDirectory(unzipFolder);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    static String dateNow() {

        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String DateToStr = format.format(curDate);
        return DateToStr;
    }

    static void purgeDirectory(File dir) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory())
                purgeDirectory(file);
            file.delete();
        }
    }


}
