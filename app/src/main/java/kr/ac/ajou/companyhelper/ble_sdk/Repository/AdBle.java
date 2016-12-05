package kr.ac.ajou.companyhelper.ble_sdk.Repository;

/**
 * Created by huy on 15. 2. 17..
 */
public class AdBle {
    public static enum LeType {
        MANUFACTUR_DATA(-1),
        NAME(9),
        UUID16(3);


        private int type;

        LeType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public static LeType toLeType(byte type) {
            for (LeType leType : LeType.values()) {
                if (leType.getType() == (int) type) return leType;
            }
            return null;
        }
    }

    private int length;
    private LeType type;
    private byte[] data;

    public AdBle(int length, LeType type, byte[] data) {
        this.length = length;
        this.type = type;
        this.data = data;
    }

    public LeType getType() {
        return type;
    }

    public String toString() {
        return bytesToHex(data);
    }

    public byte[] getData() {
        return data;
    }

    private String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}