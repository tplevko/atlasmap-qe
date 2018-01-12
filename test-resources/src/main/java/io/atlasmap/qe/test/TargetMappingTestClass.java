package io.atlasmap.qe.test;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by mmelko on 26/10/2017.
 **/
public class TargetMappingTestClass implements MappingTestClassConverter, Serializable {

    private String targetString;
    private String targetCombineString;
    private int targetInteger;
    private long targetLong;
    private float targetFloat;
    private double targetDouble;
    private Date targetDate;
    private String targetAnotherString;
    private boolean targetBoolean;
    private short targetShort;
    private byte targetByte;
    private char targetChar;
    private ObjectVariable targetObjectVariable;

    public TargetMappingTestClass() {
        this.targetString = "targetString";
        this.targetCombineString = "targetCombineString";
        this.targetInteger = 5;
        this.targetLong = 4L;
        this.targetFloat = 3f;
        this.targetDouble = 2d;
        this.targetDate = new Date(0);
        this.targetAnotherString = "targetAnotherString";
        this.targetBoolean = false;
        this.targetShort = 1;
        this.targetByte = Byte.MIN_VALUE;
        this.targetChar = 'x';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TargetMappingTestClass that = (TargetMappingTestClass) o;
        return targetInteger == that.targetInteger &&
                targetLong == that.targetLong &&
                Float.compare(that.targetFloat, targetFloat) == 0 &&
                Double.compare(that.targetDouble, targetDouble) == 0 &&
                targetBoolean == that.targetBoolean &&
                targetShort == that.targetShort &&
                targetByte == that.targetByte &&
                targetChar == that.targetChar &&
                Objects.equals(targetString, that.targetString) &&
                Objects.equals(targetCombineString, that.targetCombineString) &&
                Objects.equals(targetDate, that.targetDate) &&
                Objects.equals(targetAnotherString, that.targetAnotherString)
                &&
                Objects.equals(targetObjectVariable, that.targetObjectVariable);
    }

    @Override
    public String toString() {
        return "TargetMappingTestClass{" +
                "targetString='" + targetString + '\'' +
                "| targetCombineString='" + targetCombineString + '\'' +
                "| targetInteger=" + targetInteger +
                "| targetLong=" + targetLong +
                "| targetFloat=" + targetFloat +
                "| targetDouble=" + targetDouble +
                "| targetDate=" + targetDate +
                "| targetAnotherString='" + targetAnotherString + '\'' +
                "| targetBoolean=" + targetBoolean +
                "| targetShort=" + targetShort +
                "| targetByte=" + targetByte +
                "| targetChar=" + targetChar +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(targetString, targetCombineString, targetInteger, targetLong, targetFloat, targetDouble, targetDate, targetAnotherString, targetBoolean, targetShort, targetByte, targetChar);
    }

    @Override
    public void setAndConvertValue(String field, Object value) throws ParseException {

        switch (field) {
            case "targetCombineString": {
                this.setTargetCombineString(value.toString());
                break;
            }
            case "targetString": {
                this.setTargetString(value.toString());
                break;
            }
            case "targetInteger": {
                if (value instanceof Character) {
                    this.setTargetInteger(Integer.valueOf((Character) value));
                } else {
                    this.setTargetInteger(NumberFormat.getInstance().parse(value.toString()).intValue());
                }
                break;
            }
            case "targetLong": {
                if (value instanceof Character) {
                    this.setTargetLong(Long.valueOf((Character) value));
                } else {
                    this.setTargetLong((NumberFormat.getInstance().parse(value.toString())).longValue());
                }
                break;
            }
            case "targetFloat": {
                if (value instanceof Character) {
                    this.setTargetFloat(Float.valueOf((Character) value));
                } else {
                    this.setTargetFloat((NumberFormat.getInstance().parse(value.toString())).floatValue());
                }
                break;
            }
            case "targetDouble": {
                if (value instanceof Character) {
                    this.setTargetDouble(Double.valueOf((Character) value));
                } else {
                    this.setTargetDouble((NumberFormat.getInstance().parse(value.toString())).doubleValue());
                }
                break;
            }
            case "targetShort": {
                if (value instanceof Character) {
                    this.setTargetShort((short) ((Character) value).charValue());
                } else {
                    this.setTargetShort((NumberFormat.getInstance().parse(value.toString())).shortValue());
                }
                break;
            }
            case "targetByte": {
                try {
                    this.setTargetByte((NumberFormat.getInstance().parse(value.toString()).byteValue()));
                } catch (Exception e) {
                    this.setTargetChar(value.toString().toCharArray()[0]);
                }

                break;
            }

            case "targetChar": {
                try {
                    this.setTargetChar(Character.valueOf((char) NumberFormat.getInstance().parse(value.toString()).intValue()));
                } catch (Exception e) {
                    this.setTargetChar(value.toString().toCharArray()[0]);
                }

                break;
            }

            case "targetBoolean": {
                if (value instanceof Number) {
                    final int temp = ((Number) value).intValue();
                    if (temp == 0) {
                        this.setTargetBoolean(false);
                    } else {
                        this.setTargetBoolean(true);
                    }
                } else {
                    this.setTargetBoolean(Boolean.parseBoolean(value.toString()));
                }
                break;
            }
            case "targetDate": {
                if (value instanceof Date) {
                    this.setTargetDate((Date) value);
                } else if (value instanceof Number) {
                    this.setTargetDate(new Date(((Number) value).longValue()));
                } else {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    this.setTargetDate(format.parse(value.toString()));
                }
                break;
            }
            case "targetAnotherString": {
                this.setTargetAnotherString(value.toString());
            }
        }
    }

    @Override
    public Object getValue(String field) {
        switch (field) {
            case "targetCombineString":
                return getTargetCombineString();
            case "targetString":
                return getTargetString();
            case "targetInteger":
                return getTargetInteger();
            case "targetLong":
                return getTargetLong();
            case "targetFloat":
                return getTargetFloat();
            case "targetDouble":
                return getTargetDouble();
            case "targetDate":
                return getTargetDate();
            case "targetShort":
                return getTargetShort();
            case "targetByte":
                return getTargetByte();
            case "targetChar":
                return getTargetChar();
            case "targetBoolean":
                return isTargetBoolean();
            case "targetAnotherString":
                return getTargetAnotherString();
        }
        return null;
    }

    public String getTargetString() {
        return targetString;
    }

    public void setTargetString(String targetString) {
        this.targetString = targetString;
    }

    public String getTargetCombineString() {
        return targetCombineString;
    }

    public void setTargetCombineString(String targetCombineString) {
        this.targetCombineString = targetCombineString;
    }

    public int getTargetInteger() {
        return targetInteger;
    }

    public void setTargetInteger(int targetInteger) {
        this.targetInteger = targetInteger;
    }

    public long getTargetLong() {
        return targetLong;
    }

    public void setTargetLong(long targetLong) {
        this.targetLong = targetLong;
    }

    public float getTargetFloat() {
        return targetFloat;
    }

    public void setTargetFloat(float targetFloat) {
        this.targetFloat = targetFloat;
    }

    public double getTargetDouble() {
        return targetDouble;
    }

    public void setTargetDouble(double targetDouble) {
        this.targetDouble = targetDouble;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public String getTargetAnotherString() {
        return targetAnotherString;
    }

    public void setTargetAnotherString(String targetAnotherString) {
        this.targetAnotherString = targetAnotherString;
    }

    public boolean isTargetBoolean() {
        return targetBoolean;
    }

    public void setTargetBoolean(boolean targetBoolean) {
        this.targetBoolean = targetBoolean;
    }

    public short getTargetShort() {
        return targetShort;
    }

    public void setTargetShort(short targetShort) {
        this.targetShort = targetShort;
    }

    public byte getTargetByte() {
        return targetByte;
    }

    public void setTargetByte(byte targetByte) {
        this.targetByte = targetByte;
    }

    public char getTargetChar() {
        return targetChar;
    }

    public void setTargetChar(char targetChar) {
        this.targetChar = targetChar;
    }

    public ObjectVariable getTargetObjectVariable() {
        return targetObjectVariable;
    }

    public void setTargetObjectVariable(ObjectVariable targetObjectVariable) {
        this.targetObjectVariable = targetObjectVariable;
    }
}