package logic;

import java.io.Serializable;

public class CharFrequency implements Comparable<CharFrequency>, Serializable {
    private Character c;
    private Integer frequency;

    public CharFrequency(Character c) { // primeria frecuencia de una letra
        this.c = c;
        frequency = 1;
    }

    public CharFrequency(Integer frequency) {
        this.c = null;
        this.frequency = frequency;
    }

    public Character getLetter() {
        return c;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public boolean increaseFrequency(char c) { 
        return this.c == c ? ++frequency != 0 : false;
    }

    @Override
    public String toString() {
        return /*frequency + "|" + */(c == null ? "??" : (c == ' ' ? "' '" : "" + c)); // simplificar esto
    }

    @Override
    public int compareTo(CharFrequency o) {
        int r = 0;
        if (this.frequency != o.frequency) {
            r = this.frequency > o.frequency ? 1 : -1;
        } else if (this.c == null) {
            r = -1;
        } else if (o.c == null) {
            r = 1;
        } else
            r = (this.c > o.c ? 1 : -1); 
        return r;
    }

}
