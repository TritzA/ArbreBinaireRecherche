public class Interview<ValueType extends Comparable<? super ValueType>> {
    // 2D array of any shape which contains elements sorted from left to right, top to bottom
    private final ValueType[][] matrix;

    public Interview(ValueType[][] matrix) {
        this.matrix = matrix.clone();
    }

    /**
     * TODO Worst case : O ( max(log n, log m) )
     * <p>
     * Verifies if the value is contained within the 2D array
     *
     * @param value value to verify
     * @return if value is in matrix
     */
    public boolean contains(ValueType value) {

        int line = binarySearchOnColumn(matrix, value);//find the line of value
        if (line == -1) {//if the value is not in the matrix
            return false;
        } else if (line == -2) {//if we find the value
            return true;
        }

        return binarySearchOnLine(matrix, value, line);//return if the value is on the line
    }

    //we find the line of the value with it
    private int binarySearchOnColumn(ValueType[][] mat, ValueType value) {
        int start = 0;
        int end = mat.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (mat[mid][0].equals(value)) {
                return -2;//if value is on the column [0]
            } else if (mat[mid][0].compareTo(value) < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    //we find the column of the value with it
    private boolean binarySearchOnLine(ValueType[][] mat, ValueType value, int i) {
        int start = 1;//the 0 case is already covered in the binarySearchOnColumn(matrix, value)
        int end = mat[i].length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (mat[i][mid].equals(value)) {
                return true;
            } else if (mat[i][mid].compareTo(value) < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
}
