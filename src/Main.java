import java.io.*;
import java.util.regex.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("input.txt");
            Scanner scanner = new Scanner(inputFile);
            File outputFile = new File("output.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("end")) {
                    continue; // Skip lines with "end"
                }

                // Check if the line represents a problem number
                if (Character.isDigit(line.charAt(0))&&line.length()<=2) {
                    // Extract the problem number
                    int problemNumber = Integer.parseInt(line.trim()); // Extract the number after "Problem#"

                    // Handle different problem numbers
                    switch (problemNumber) {
                        case 1:
                            writer.write("1 ");
                            writer.newLine();
                            p1_validateEmail(scanner, writer);
                            break;
                        case 2:
                            writer.write("2 ");
                            writer.newLine();
                            p2_validatePhoneNumber(scanner, writer);
                            break;
                        case 3:
                            writer.write("3 ");
                            writer.newLine();
                            p3_validateDate(scanner, writer);
                            break;
                        case 4:
                            writer.write("4 ");
                            writer.newLine();
                            p4_validateIPAddress(scanner, writer);
                            break;
                        case 5:
                            writer.write("5 ");
                            writer.newLine();
                            p5_validateCppVariables(scanner, writer);
                            break;
                        case 6:
                            writer.write("6");
                            writer.newLine();
                            p6_validateTripleBString(scanner, writer);
                            break;
                        case 7:
                            writer.write("7 ");
                            writer.newLine();
                            p7_extractOddABStrings(scanner, writer);
                            break;
                        case 8:
                            writer.write("8 ");
                            writer.newLine();
                            p8_extractWordsMultipleOfThree(scanner, writer);
                            break;
                        case 9:
                            writer.write("9 ");
                            writer.newLine();
                            p9_extractURLs(scanner, writer);
                            break;
                        case 10:
                            writer.write("10 ");
                            writer.newLine();
                            p10_validateMathExpression(scanner, writer);
                            break;
                        default:
                            break;
                    }
                }
            }

            scanner.close();
           // writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void p1_validateEmail(Scanner scanner, BufferedWriter writer) throws IOException {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("end")) break;
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches())
                writer.write("valid email\n");
            else
                writer.write("invalid email\n");
        }
        writer.write("x\n");
        writer.flush();
    }

    private static void p2_validatePhoneNumber(Scanner scanner, BufferedWriter writer) throws IOException {
        Pattern pattern = Pattern.compile("^(\\d{10}|\\d{3}[-\\s]?\\d{3}[-\\s]?\\d{4}|\\(\\d{3}\\)[-\\s]?\\d{3}[-\\s]?\\d{4})$");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("end")) break;
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches())
                writer.write("valid phone number\n");
            else
                writer.write("invalid phone number\n");
        }
        writer.write("x\n");
        writer.flush();
    }

    private static void p3_validateDate(Scanner scanner, BufferedWriter writer) throws IOException {
        Pattern pattern = Pattern.compile("^(\\d{4}/(0?[1-9]|1[0-2])/(0?[1-9]|[12]\\d|3[01])|\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12]\\d|3[01])|(0?[1-9]|[12]\\d|3[01])/(0?[1-9]|1[0-2])/\\d{4}|(0?[1-9]|[12]\\d|3[01])-(0?[1-9]|1[0-2])-\\d{4})$");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("end")) break;
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches())
                writer.write("valid date\n");
            else
                writer.write("invalid date\n");
        }
        writer.write("x\n");
        writer.flush();
    }

    private static void p4_validateIPAddress(Scanner scanner, BufferedWriter writer) throws IOException {
        Pattern pattern = Pattern.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("end")) break;
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches())
                writer.write("valid IP address\n");
            else
                writer.write("invalid IP address\n");
        }
        writer.write("x\n");
        writer.flush();
    }

    private static void p5_validateCppVariables(Scanner scanner, BufferedWriter writer) throws IOException {
        Pattern pattern = Pattern.compile("^[_a-zA-Z][_a-zA-Z0-9]*$");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("end")) break;
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches())
                writer.write("valid C++ variable name\n");
            else
                writer.write("invalid C++ variable name\n");
        }
        writer.write("x\n");
        writer.flush();
    }

    private static void p6_validateTripleBString(Scanner scanner, BufferedWriter writer) throws IOException {
        Pattern pattern = Pattern.compile("(?i).*bbb.*"); // Updated pattern to specifically check for three consecutive occurrences of "b" (case insensitive)
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("end")) break;
            Matcher matcher = pattern.matcher(line);
            if (matcher.find())
                writer.write("invalid string, has 3 consecutive b’s\n");
            else
                writer.write("valid string\n");
        }
        writer.write("x\n");
        writer.flush();
    }


    private static void p7_extractOddABStrings(Scanner scanner, BufferedWriter writer) throws IOException {
        Pattern pattern = Pattern.compile("(ba(aa)*|ba(aa)*ba(aa)*b)|b(bb)*ab(bb)*ab(bb)*b");
        int count; // Move count outside the loop
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("end")) break;
            Matcher matcher = pattern.matcher(line);
            count = 0; // Reset count for each line
            while (matcher.find()) {
                count++;
                writer.write("*" + matcher.group() + "*\n");
                writer.write("number of matched substrings: " + count + "\n");
                writer.write("matched substring: " + matcher.group() + "\n");
                writer.write("start index: " + matcher.start() + ", end index: " + matcher.end() + "\n");
            }
        }
        writer.write("x\n");
        writer.flush();
    }

    private static void p8_extractWordsMultipleOfThree(Scanner scanner, BufferedWriter writer) throws IOException {
        writer.write("8\n");
        Pattern pattern = Pattern.compile("\\b\\w{3}\\b");
        ArrayList<String> matchedWords = new ArrayList<>();
        ArrayList<String> unmatchedWords = new ArrayList<>();
        int cnt = 0;
        String[] words;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            writer.write(line);
            writer.newLine();
            if (line.equals("end"))
                break;
            Matcher matcher = pattern.matcher(line);
            boolean found = false;

            while (matcher.find()) {
                found = true;
                cnt++;
                matchedWords.add(matcher.group());
            }
            words = line.split("\\s+");
            if (!found) {
                unmatchedWords.add(line);
            }
            // Move this part inside the loop to maintain reference to 'line'
            if (found && cnt > 0) {
                String[] matchedArray = matchedWords.toArray(new String[0]);
                writer.write("number of matched words:" + cnt + "\n");
                for (String word : matchedArray) {
                    writer.write("Matched word: ");
                    writer.write(word + "\n");
                    writer.write("start index: " + line.indexOf(word) + ", end index: " + (line.indexOf(word) + word.length()) + "\n");
                }
            }

            if (!unmatchedWords.isEmpty()) {
                for (String word : unmatchedWords) {
                    writer.write(word + "\n");
                }
            } else {
                writer.write("*No word matches*\n");
            }
        }

        writer.write("x\n");
        writer.flush();
    }
    // extract url
    private static void p9_extractURLs(Scanner scanner, BufferedWriter writer) throws IOException {
        writer.write("9\n");
        String filePath = scanner.nextLine().trim(); // Read the file path and trim any leading or trailing spaces
        File file = new File(filePath);

        try {
            Scanner fileScanner = new Scanner(file);
            Pattern pattern = Pattern.compile("\\bhttps?://\\S+\\b\\/?");
            int lineNumber = 1;
            int urlCount = 0;

            // Counting the number of URLs in the file
            while (fileScanner.hasNextLine()) {
                String fileLine = fileScanner.nextLine();
                Matcher matcher = pattern.matcher(fileLine);
                while (matcher.find()) {
                    urlCount++;
                }
            }

            // Resetting the file scanner
            fileScanner.close();
            fileScanner = new Scanner(file);

            writer.write("" + file.getName() + "\n"); // Writing file name
            writer.write("Number of URLs: " + urlCount + "\n");
            writer.newLine();

            // Writing details of each URL
            while (fileScanner.hasNextLine()) {
                String fileLine = fileScanner.nextLine();
                Matcher matcher = pattern.matcher(fileLine);
                while (matcher.find()) {
                    writer.write("URL: " + matcher.group() + "\n");
                    writer.write("Line: " + lineNumber + "\n");
                    writer.write("start index: " + matcher.start() + ", end index: " + matcher.end() + "\n");
                }
                lineNumber++;
            }
            writer.write("x\n"); // Writing the closing "x"
            fileScanner.close();
        } catch (FileNotFoundException e) {
            writer.write("File not found: " + filePath + "\n");
            writer.write("x\n"); // Writing the closing "x" even in case of file not found
        }
    }

    private static void p10_validateMathExpression(Scanner scanner, BufferedWriter writer) throws IOException {
        writer.write("10\n");
        Pattern pattern = Pattern.compile("^-?[a-zA-Z0-9]+([-+/]-?[a-zA-Z0-9]+)=-?[a-zA-Z0-9]+([-+/]-?[a-zA-Z0-9]+)$");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("end")) break;
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches())
                writer.write("valid mathematical expression\n");
            else
                writer.write("invalid mathematical expression\n");
        }
        writer.write("x\n");
    }
}
