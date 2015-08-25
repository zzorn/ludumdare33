package ld33.zenominator.utils.boundary;

import com.badlogic.gdx.math.Vector3;

/**
 * For collisions between spheres and other geometry.
 */
public interface Boundary {

    /**
     * @param center center of spehere to collide.
     * @param radius radius of sphere.
     * @param correctedPositionDeltaOut vector to write collision correction vector to and return.
     * @return a vector that moves the sphere out of collision, or a zero vector if there are no collisions.
     */
    Vector3 collideSphere(Vector3 center, float radius, Vector3 correctedPositionDeltaOut);

}
