package me.towster.utils;

import static org.lwjgl.opengl.GL11.*;

public class Cube {
    int[][] indices = {
            // Front face
            {0, 1, 2, 3},
            // Back face
            {4, 5, 6, 7},
            // Left face
            {0, 1, 5, 4},
            // Right face
            {3, 2, 6, 7},
            // Top face
            {0, 3, 7, 4},
            // Bottom face
            {1, 2, 6, 5},
    };

    private float x, y, z, scaleX = 1f, scaleY = 1f, scaleZ = 1f, pitch = 0f, yaw = 0f, roll = 0f, r = 1f, g = 1f, b = 1f, alpha = 0f;

    public Cube(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw() {
        glColor3f(0.6f, 0.0f, 1.0f);

        float[][] vertices = {
                {-0.5f,  0.5f,  0.5f}, // Front top left
                {-0.5f, -0.5f,  0.5f}, // Front bottom left
                {0.5f, -0.5f,  0.5f}, // Front bottom right
                {0.5f,  0.5f,  0.5f}, // Front top right
                {-0.5f,  0.5f, -0.5f}, // Back top left
                {-0.5f, -0.5f, -0.5f}, // Back bottom left
                {0.5f, -0.5f, -0.5f}, // Back bottom right
                {0.5f,  0.5f, -0.5f}  // Back top right
        };



        for (float[] vertice : vertices) {
            VectorMath.scale(vertice, scaleX, scaleY, scaleZ);
            VectorMath.rotate(vertice, pitch, yaw, roll);
            vertice[0] += x;
            vertice[1] += y;
            vertice[2] += z;
        }

        glBegin(GL_QUADS);
        for (int i = 0; i < 6; i++) {
            // Draw the cube
            float[] normal = new float[3];
            for (int j = 0; j < 4; j++) {
                int index = indices[i][j];
                normal = VectorMath.vectorAddition(normal, vertices[index]);
            }
            VectorMath.normalize(normal);
            glNormal3fv(normal);
            for (int j = 0; j < 4; j++) {
                int index = indices[i][j];
                glVertex3f(vertices[index][0], vertices[index][1], vertices[index][2]);
            }
        }
        glEnd();

    }

    public void setRotation(float pitch, float yaw, float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public void setScale(float scaleX, float scaleY, float scaleZ) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
    }

    public void setPosition(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
