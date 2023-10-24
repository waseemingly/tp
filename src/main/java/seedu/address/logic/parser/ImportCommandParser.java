package seedu.address.logic.parser;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Date;
import seedu.address.model.commons.Name;
import seedu.address.model.person.Role;
import seedu.address.model.developer.Salary;
import seedu.address.model.person.*;
import seedu.address.model.project.Project;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_FILE;

public class ImportCommandParser implements Parser<ImportCommand>{

    @Override
    public ImportCommand parse(String fileName) throws ParseException {
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            fileName=fileName.trim();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = "";
            String splitBy = ",";
            boolean isValid = checkColumnNames(br.readLine());
            if(!isValid){
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
            }
            ArrayList<Person> toAddList = new ArrayList<>();
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] employee = line.split(splitBy);    // use comma as separator
                Name name = ParserUtil.parseName(employee[0]);
                Phone phone = ParserUtil.parsePhone(employee[1]);
                Email email = ParserUtil.parseEmail(employee[2]);
                Address address = ParserUtil.parseAddress(employee[3]);
                Date dateJoined = ParserUtil.parseDateJoined(employee[4]);
                Role role = ParserUtil.parseRole(employee[7]);
                Salary salary = ParserUtil.parseSalary(employee[8]);
                ArrayList<String> projects = new ArrayList<>();
                for(int i=9;i< employee.length;i++) {
                    projects.add(employee[i]);
                }
                Set<Project> projectList = ParserUtil.parseProjects(projects);

                Person person = new Person(name, phone, email, address, dateJoined,role, salary,projectList);
                toAddList.add(person);

            }
            return new ImportCommand(toAddList);
        } catch (FileNotFoundException ex) {
            throw new ParseException(MESSAGE_INVALID_FILE);
        } catch (IOException e) {
            throw new ParseException("Error reading line from file " + fileName);
        }
    }
    private boolean checkColumnNames(String line) {
        String[] columnNames = line.split(",");
        if (!columnNames[0].contains("Name")) {
            return false;
        }
        if (!columnNames[1].contains("Contact Number")) {
            return false;
        }
        if (!columnNames[2].contains("Email")) {
            return false;
        }
        if (!columnNames[3].contains("Address")) {
            return false;
        }
        if (!columnNames[4].contains("Date Joined")) {
            return false;
        }
        if (!columnNames[5].contains("Role")) {
            return false;
        }
        if (!columnNames[6].contains("Salary")) {
            return false;
        }
        if (!columnNames[7].contains("Username")) {
            return false;
        }
        if (!columnNames[8].contains("Password")) {
            return false;
        }
        if (!columnNames[9].contains("Projects")) {
            return false;
        }
        return true;
    }
}
