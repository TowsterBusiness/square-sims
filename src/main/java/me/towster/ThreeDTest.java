/*
 * All Calculations are derived from https://www.desmos.com/calculator/6ocjywflgo
 * I am not smart enough to figure this out.
 *
 *
 *
 *
 *
 *
 * */

package me.towster;

import me.towster.utils.*;
import org.joml.Vector2d;

import java.util.*;

import static me.towster.utils.InverseKinematics.inverseKinematics;
import static me.towster.utils.VectorMath.crossProduct;
import static org.lwjgl.opengl.GL11.*;

public class ThreeDTest extends Scene {

    // Camera parameters
    private float cameraX = 1.0f, cameraY = 2.0f, cameraZ = 3.0f;
    private float cameraPitch = 0.0f, cameraYaw = 0.0f;

    List<Cube> blocks = new ArrayList<>();

    public ThreeDTest() {
        for (int i = 0; i < 30; i++ ){
            Cube block = new Cube((float) Math.random() * 3f - 1.5f, 0.5f,(float) Math.random() * 3f - 1.5f);
            block.setScale(0.3f, 0.15f, 0.15f);
            block.setRotation(0, (float) Math.random() * 360, 0);
            blocks.add(block);
        }
    }

    @Override
    public void init() {
        glEnable(GL_DEPTH_TEST);


        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);

        float[] lightPosition = {0f, 1.0f, 0f, 0.0f}; // Light position (far away from the scene)
        glLightfv(GL_LIGHT0, GL_POSITION, lightPosition);
        float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};  // White light
        glLightfv(GL_LIGHT0, GL_DIFFUSE, lightDiffuse);

        glEnable(GL_COLOR_MATERIAL);
        glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE);
    }

    @Override
    public void update(float dt)  {

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        setupPerspective(45.0f, (float) 800 / (float) 600, 0.1f, 100.0f);

        // Set up the camera view
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        gluLookAt(cameraX, cameraY, cameraZ, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);

        for (Cube block : blocks) {
            block.draw();
        }

    }

    private void gluLookAt(float eyeX, float eyeY, float eyeZ,
                           float centerX, float centerY, float centerZ,
                           float upX, float upY, float upZ) {
        // Calculate the forward, right, and up vectors
        float[] forward = { centerX - eyeX, centerY - eyeY, centerZ - eyeZ };
        VectorMath.normalize(forward);

        float[] up = { upX, upY, upZ };
        float[] side = VectorMath.crossProduct(forward, up);
        VectorMath.normalize(side);

        up = VectorMath.crossProduct(side, forward);

        // Set the matrix
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        float[] matrix = {
                side[0], up[0], -forward[0], 0,
                side[1], up[1], -forward[1], 0,
                side[2], up[2], -forward[2], 0,
                0, 0, 0, 1
        };

        glMultMatrixf(matrix);
        glTranslatef(-eyeX, -eyeY, -eyeZ);
    }



    private void setupPerspective(float fov, float aspectRatio, float near, float far) {
        float top = (float) (Math.tan(Math.toRadians(fov / 2)) * near);
        float bottom = -top;
        float right = top * aspectRatio;
        float left = -right;

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glFrustum(left, right, bottom, top, near, far);
        glMatrixMode(GL_MODELVIEW);
    }
}
