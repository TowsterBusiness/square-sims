package me.towster.utils;

public class VectorMath {
    public static void rotate(float[] a, float pitch, float yaw, float roll) {
        yaw = (float) Math.toRadians(yaw);
        float magnitude = distance(a[0], a[2]);
        float angle = (float) Math.atan2(a[2], a[0]) + yaw;
        a[0] = (float) Math.cos(angle) * magnitude;
        a[2] = (float) Math.sin(angle) * magnitude;

        pitch = (float) Math.toRadians(pitch);
        magnitude = distance(a[1], a[2]);
        angle = (float) Math.atan2(a[2], a[1]) + pitch;
        a[1] = (float) Math.cos(angle) * magnitude;
        a[2] = (float) Math.sin(angle) * magnitude;

        roll = (float) Math.toRadians(roll);
        magnitude = distance(a[0], a[1]);
        angle = (float) Math.atan2(a[1], a[0]) + roll;
        a[0] = (float) Math.cos(angle) * magnitude;
        a[1] = (float) Math.sin(angle) * magnitude;


    }

    public static void scale(float[] a, float x, float y, float z) {
        a[0] *= x;
        a[1] *= y;
        a[2] *= z;
    }

    public static float distance(float a, float b) {
        return (float) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    public static void normalize(float[] vector) {
        float length = (float) Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1] + vector[2] * vector[2]);
        vector[0] /= length;
        vector[1] /= length;
        vector[2] /= length;
    }

    public static float[] crossProduct(float[] a, float[] b) {
        return new float[]{
                a[1] * b[2] - a[2] * b[1],
                a[2] * b[0] - a[0] * b[2],
                a[0] * b[1] - a[1] * b[0]
        };
    }

    public static float[] vectorAddition(float[] a, float[] b) {
        int listLength = Math.max(a.length, b.length);

        float[] result = new float[listLength];

        for (int i = 0; i < a.length; i++) {
            result[i] = a[i];
        }

        for (int i = 0; i < b.length; i++) {
            result[i] += b[i];
        }

        return result;
    }

    public static void rotate(float[] floats) {
    }
}
