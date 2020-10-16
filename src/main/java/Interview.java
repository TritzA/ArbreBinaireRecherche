public class Interview<ValueType extends Comparable<? super ValueType> > {
    // 2D array of any shape which contains elements sorted from left to right, top to bottom
    private final ValueType[][] matrix;

    public Interview(ValueType[][] matrix) {
        this.matrix = matrix.clone();
    }

    /** TODO Worst case : O ( max(log n, log m) )
     *
     * Verifies if the value is contained within the 2D array
     * @param value value to verify
     * @return if value is in matrix
     */
    public boolean contains(ValueType value) {
        return false;
    }
}
