package net.kyrptonaught.jankson.impl;

import net.kyrptonaught.jankson.Jankson;
import net.kyrptonaught.jankson.JsonPrimitive;
import net.kyrptonaught.jankson.api.SyntaxError;

import java.util.Locale;

public class NumberParserContext implements ParserContext<JsonPrimitive> {
    private String numberString = "";
    private boolean complete = false;
    private final String acceptedChars = "0123456789.+-eExabcdefInityNn";

    public NumberParserContext(int firstCodePoint) {
        numberString += (char) firstCodePoint;
    }

    @Override
    public boolean consume(int codePoint, Jankson loader) throws SyntaxError {
        if (complete) return false;

        if (acceptedChars.indexOf(codePoint) != -1) {
            numberString += (char) codePoint;
            return true;
        } else {
            complete = true;
            return false;
        }
    }

    @Override
    public void eof() throws SyntaxError {
        complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    @Override
    public JsonPrimitive getResult() throws SyntaxError {
        //parse special values
        String lc = numberString.toLowerCase(Locale.ROOT);
        if (lc.equals("infinity") || lc.equals("+infinity")) {
            return JsonPrimitive.of(Double.POSITIVE_INFINITY);
        } else if (lc.equals("-infinity")) {
            return JsonPrimitive.of(Double.NEGATIVE_INFINITY);
        } else if (lc.equals("nan")) {
            return JsonPrimitive.of(Double.NaN);
        }

        //Fallback to the number parsers
        if (numberString.startsWith(".")) numberString = '0' + numberString;
        if (numberString.endsWith(".")) numberString = numberString + '0';
        if (numberString.startsWith("0x")) {
            numberString = numberString.substring(2);
            try {
                Long l = Long.parseUnsignedLong(numberString, 16);
                return JsonPrimitive.of(l);
            } catch (NumberFormatException nfe) {
                throw new SyntaxError("Tried to parse '" + numberString + "' as a hexadecimal number, but it appears to be invalid.");
            }
        }
        if (numberString.startsWith("-0x")) {
            numberString = numberString.substring(3);
            try {
                Long l = -Long.parseUnsignedLong(numberString, 16);
                return JsonPrimitive.of(l);
            } catch (NumberFormatException nfe) {
                throw new SyntaxError("Tried to parse '" + numberString + "' as a hexadecimal number, but it appears to be invalid.");
            }
        }


        if (numberString.indexOf('.') != -1) {
            //Return as a Double
            try {
                Double d = Double.valueOf(numberString);
                return JsonPrimitive.of(d);
            } catch (NumberFormatException ex) {
                throw new SyntaxError("Tried to parse '" + numberString + "' as a floating-point number, but it appears to be invalid.");
            }
        } else {
            //Return as a Long
            try {
                Long l = Long.valueOf(numberString);
                return JsonPrimitive.of(l);
            } catch (NumberFormatException ex) {
                throw new SyntaxError("Tried to parse '" + numberString + "' as an integer, but it appears to be invalid.");
            }
        }
    }

}
