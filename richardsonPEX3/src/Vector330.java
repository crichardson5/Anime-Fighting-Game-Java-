import java.util.regex.Pattern;

public class Vector330 {

    private double vectorX; //x value of the vector
    private double vectorY; //y value of the vector

    private static final double EPS = 1.0E-09; //sets the epsilon for difference toleration

    /**
     * initializes x and y if the no x or y values are given
     */
    public Vector330() {
        vectorX = 0;
        vectorY = 0;
    }

    public Vector330(double x, double y) {
        vectorX = x;
        vectorY = y;
    }

    public Vector330(int x, int y) {
        vectorX = (double) x;
        vectorY = (double) y;
    }

    public Vector330(long x, long y) {
        vectorX = (double) x;
        vectorY = (double) y;
    }

    public double getVectorX() {
        return vectorX;
    }

    public double getVectorY() {
        return vectorY;
    }

    public int getVectorXint() {
        return (int) vectorX;
    }

    public int getVectorYint() {
        return (int) vectorY;
    }

    public long getVectorXlong() {
        return (long) vectorX;
    }

    public long getVectorYlong() {
        return (long) vectorY;
    }

    public void setVectorX(double x) {
        vectorX = x;
    }

    public void setVectorY(double y) {
        vectorY = y;
    }

    public void setVectorX(int x) {
        vectorX = (double) x;
    }

    public void setVectorY(int y) {
        vectorY = (double) y;
    }

    public void setVectorX(long x) {
        vectorX = (double) x;
    }

    public void setVectorY(long y) {
        vectorY = (double) y;
    }


    /**
     * parseVector - takes a vector from a specified user input and makes it a Vector330 class vector
     *
     * @param s - the scanner to read input from
     * @return - the vector generated from the user's input
     * @throws java.lang.Exception
     */
    public Vector330 parseVector(java.util.Scanner s) throws java.lang.Exception {

        Pattern originalPattern = s.delimiter();

        s.useDelimiter("[" + originalPattern + ",]");

        Vector330 newVector = new Vector330();

        if (s.hasNext("<")) {

            s.next("<");

            if (s.hasNextDouble()) {

                newVector.setVectorX(s.nextDouble());

                s.useDelimiter(originalPattern);

                if (s.hasNext(",")) {

                    s.next(",");

                    if (s.hasNextDouble()) {

                        newVector.setVectorY(s.nextDouble());

                        if (s.hasNext(">")) {

                            s.next(">");
                        } else {

                            throw new Exception(("Please close your vector with a >\n"));

                        }
                    } else {

                        throw new Exception("Please enter a second number in the vector\n");
                    }
                } else {

                    throw new Exception("Please enter a comma after the first number\n");

                }
            } else {
                throw new Exception("Please enter a number after the <\n");
            }


        } else {
            throw new Exception("missing '<' for vector\n");
        }

        return newVector;
    }

    /**
     * add - adds current vector to another vector passed to the method
     *
     * @param vector - another vector to be passed in
     * @return - the sum of the two vectors
     */
    public Vector330 add(Vector330 vector) {
        //creates a new vector to store the sum
        Vector330 newVector = new Vector330();

        //adds the two x coordinates of the addend vectors and sets their sum as the x coordinate of the new vector
        double newX = vectorX + vector.vectorX;
        newVector.setVectorX(newX);

        //adds the two y coordinates of the addend vectors and sets their sum as the y coordinate of the new vector
        double newY = vectorY + vector.vectorY;
        newVector.setVectorY(newY);

        //returns the sum of the two vectors
        return newVector;
    }

    /**
     * equals - checks to see if current vector and another vector are equal
     *
     * @param vector - another vector to be passed in
     * @return - true or false based on the equality of the vectors
     */
    public boolean equals(Vector330 vector) {
        //sets a boolean to be returned
        boolean equal = false;

        //breaks down into handling x and y separately
        boolean xEqual = false;
        boolean yEqual = false;

        //checks for equality in the x
        double xDifference = java.lang.Math.abs(vector.vectorX - vectorX);
        if (xDifference < 15) {
            xEqual = true;
        }

        //checks for equality in the y
        double yDifference = java.lang.Math.abs(vector.vectorY - vectorY);
        if (yDifference < 15) {
            yEqual = true;
        }

        //checks to see if the whole vector is equal
        if (xEqual && yEqual) {
            equal = true;
        }

        return equal;
    }

    public double direction(Vector330 v2) {
        //calculates the angle of the vector
        double angle = Math.atan2(vectorY, vectorX);

        return angle;
    }

    public double dotProduct(Vector330 vector) {
        //calculates the first term of the dot product
        double dotProdX = vectorX * vector.vectorX;

        //calculates the second term of the dot product
        double dotProdY = vectorY * vector.vectorY;

        //calculates the dot product
        double dotProd = dotProdX + dotProdY;

        return dotProd;
    }

    public double magnitude() {
        double magnitude;

        //magnitude is square root of each term squared
        //squares x term
        double xSquare = vectorX * vectorX;

        //squares y term
        double ySquare = vectorY * vectorY;

        //takes the square root of their sum
        magnitude = java.lang.Math.sqrt(xSquare + ySquare);

        //returns the magnitude
        return magnitude;
    }

    public double distanceBetween(Vector330 v){
        double x1 = this.vectorX;
        double x2 = v.vectorX;

        double y1 = this.vectorY;
        double y2 = v.vectorY;

        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }

    public Vector330 normalize() {
        Vector330 normVector = new Vector330();
        //gets the magnitude of the vector
        double magnitude = magnitude();

        if (magnitude <= EPS) {
            return normVector;
        }

        //gets the normalized x
        double normX = vectorX * (1 / magnitude);
        normVector.setVectorX(normX);

        //gets the normalized y
        double normY = vectorY * (1 / magnitude);
        normVector.setVectorY(normY);

        //returns the normalized vector
        return normVector;
    }

    public Vector330 scale(double multiplier) {
        //creates a new vector to store the result
        Vector330 scaledVector = new Vector330();

        //calculates the x of the scaled vector
        double newX = multiplier * vectorX;
        scaledVector.setVectorX(newX);

        //calculates the y of the scaled vector
        double newY = multiplier * vectorY;
        scaledVector.setVectorY(newY);

        //returns the scaled vector
        return scaledVector;
    }

    public Vector330 subtract(Vector330 vector) {
        //creates a new vector to store the difference
        Vector330 differenceVector = new Vector330();

        //subtracts the two x coordinates of the given vectors and sets their difference as the x coordinate of the new vector
        double newX = vectorX - vector.vectorX;
        differenceVector.setVectorX(newX);

        //subtracts the two y coordinates of the given vectors and sets their difference as the y coordinate of the new vector
        double newY = vectorY - vector.vectorY;
        differenceVector.setVectorY(newY);

        //returns the difference of the two vectors
        return differenceVector;
    }

    public java.lang.String toString() {
        //makes the vector into a string
        java.lang.String vectorString = "< " + vectorX + ", " + vectorY + " >";

        //returns the string
        return vectorString;
    }
}
