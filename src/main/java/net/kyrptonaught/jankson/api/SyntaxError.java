package net.kyrptonaught.jankson.api;

@SuppressWarnings("serial")
public class SyntaxError extends Exception {
    int startLine = -1;
    int startColumn = -1;

    int line = -1;
    int column = -1;

    public SyntaxError(String message) {
        super(message);
    }

    public String getCompleteMessage() {
        StringBuilder message = new StringBuilder();
        if (startLine != -1 && startColumn != -1) {
            message.append("Started at line ");
            message.append(startLine + 1);
            message.append(", column ");
            message.append(startColumn + 1);
            message.append("; ");
        }

        if (line != -1 && column != -1) {
            message.append("Errored at line ");
            message.append(line + 1);
            message.append(", column ");
            message.append(column + 1);
            message.append("; ");
        }

        message.append(super.getMessage());

        return message.toString();
    }

    public String getLineMessage() {
        StringBuilder message = new StringBuilder();
        boolean hasStart = (startLine != -1 && startColumn != -1);
        boolean hasEnd = (line != -1 && column != -1);

        if (hasStart) {
            message.append("Started at line ");
            message.append(startLine + 1);
            message.append(", column ");
            message.append(startColumn + 1);
        }

        if (hasStart && hasEnd) {
            message.append("; ");
        }

        if (hasEnd) {
            message.append("Errored at line ");
            message.append(line + 1);
            message.append(", column ");
            message.append(column + 1);
        }

        return message.toString();
    }

    public void setStartParsing(int line, int column) {
        this.startLine = line;
        this.startColumn = column;
    }

    public void setEndParsing(int line, int column) {
        this.line = line;
        this.column = column;
    }

}
