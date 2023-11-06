import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;

interface Command {
    void execute();
}

class AdditionCommand implements Command {
    private Worker worker;

    public AdditionCommand(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void execute() {
        worker.addContact();
    }
}

class DeleteCommand implements Command {
    private Worker worker;

    public DeleteCommand(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void execute() {
        worker.deleteContact();
    }
}

class QueryCommand implements Command {
    private Worker worker;

    public QueryCommand(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void execute() {
        worker.queryContact();
    }
}

class Worker {
    private AddressBook addressBook;
    private File dataFile;

    public Worker(AddressBook addressBook) {
        this.addressBook = addressBook;
        this.dataFile = new File("contacts.txt"); // 定义文件名
    }

    public void addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("要添加的联系人名字： ");
        String name = scanner.nextLine();
        System.out.print("电话号码： ");
        String phoneNumber = scanner.nextLine();

        try {
            // 打开文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile, true));

            // 写入联系人
            writer.write(name + " - " + phoneNumber);
            writer.newLine(); // 换行

            writer.close();
            System.out.println("联系人已添加： " + name + " - " + phoneNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("删除联系人的名字: ");
        String name = scanner.nextLine();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            File tempFile = new File("temp_contacts.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean contactDeleted = false;

            // 删除联系人
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(name + " - ")) {
                    contactDeleted = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();


            dataFile.delete();
            tempFile.renameTo(dataFile);

            if (contactDeleted) {
                System.out.println("联系人已删除: " + name);
            } else {
                System.out.println("联系人未找到");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void queryContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("查询联系人的名字: ");
        String name = scanner.nextLine();

        try {
            //查找指定联系人
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));

            String line;
            boolean contactFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith(name + " - ")) {
                    System.out.println("联系人号码：" + line);
                    contactFound = true;
                    break;
                }
            }

            reader.close();

            if (!contactFound) {
                System.out.println("联系人未找到");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class AddressBook {
    private Command command;

    public AddressBook(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}

public class AddressBookMain {
    public static void main(String[] args) {
        AddressBook addressBook;
        Worker worker = new Worker(new AddressBook(null));
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("请选择功能：");
            System.out.println("1. 添加联系人");
            System.out.println("2. 删除联系人");
            System.out.println("3. 查看联系人");
            System.out.println("4. 退出");

            choice = scanner.nextInt();
            Command selectedCommand = null;

            switch (choice) {
                case 1:
                    selectedCommand = new AdditionCommand(worker);
                    break;
                case 2:
                    selectedCommand = new DeleteCommand(worker);
                    break;
                case 3:
                    selectedCommand = new QueryCommand(worker);
                    break;
                case 4:
                    System.out.println("程序结束。");
                    break;
                default:
                    System.out.println("无效的选项");
                    break;
            }

            if (selectedCommand != null) {
                addressBook = new AddressBook(selectedCommand);
                addressBook.executeCommand();
            }
        }
    }
}



